import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class IO {
    public ArrayList<Garden> loadFromFile(String filename) {
        ArrayList<Garden> gardens = new ArrayList<>();
        String line ;
        Garden newGarden;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            try {
                line = reader.readLine();

                while(line != null)
                {
                    newGarden = this.processLine(line);
                    if(newGarden != null)
                        gardens.add(newGarden);
                    line = reader.readLine();
                }
            }
            catch (IOException e)
            {
                System.out.println( "Unable to read " + filename + ".");
                System.exit(Enum.EXIT_READ_EXCEPTION);
            }
        }
        catch ( FileNotFoundException e)
        {
            System.out.println( "File " + filename + " not found." );
            System.exit(Enum.EXIT_FILE_NOT_FOUND);
        }

        if(gardens.isEmpty()) {
            System.out.println("Invalid garden/s entered in the file " + filename + ".");
            System.exit(Enum.EXIT_FILE_INVALID_SCHEME);
        }

        return gardens;
    }

    private Garden processLine(String line) {
        Matcher matcher = Pattern.compile("([0-9]+)").matcher(line);
        ArrayList<Integer> input = new ArrayList<>();

        while (matcher.find())
            input.add(Integer.parseInt(matcher.group(0)));

        int height = input.get(1);
        int width = input.get(0);

        Garden garden  = new Garden(height, width);

        for (int i = 2; i < input.size(); i+=2) {
            garden.setStone(input.get(i), input.get(i + 1));
            garden.incStones();
        }

        garden.setToBeRaked();
        garden.computeMaxGenes();

        return garden;
    }

    public void writeToFile(ArrayList<Solution> solutions, String filename) {
        if(solutions.isEmpty()) {
            System.out.println("No solutions found.");
            System.exit(Enum.EXIT_SOLUTION_NONE);
        }

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(filename));
        }
        catch (IOException e)
        {
            System.out.println( "Unable to open " + filename + " for writing.");
            System.exit(Enum.EXIT_WRITE_EXCEPTION);
        }

        int i = 1;
        for (Solution solution: solutions) {
            try {
                writer.write("Solution no." + i +":\n");
                ArrayList<String> lines = mapToLines(solution.getMap(), solution.getHeight(), solution.getWidth());
                for (String line: lines)
                    writer.write(line + '\n');

                writer.write("Run time = " + solution.getRunTime() + "ms\n\n");
            } catch (IOException e)
            {
                System.out.println( "Unable to write to " + filename + ".");
                System.exit(Enum.EXIT_WRITE_EXCEPTION);
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            System.out.println( "Unable to close " + filename + ".");
            System.exit(Enum.EXIT_FILE_CLOSE);
        }
    }

    private ArrayList<String> mapToLines(int[][] map, int height, int width) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < width; j++) {
                line.append(String.format("%5d", map[i][j]));
            }
            result.add(line.toString());
        }

        return result;
    }
}
