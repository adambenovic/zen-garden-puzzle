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

        int width = input.get(0);
        int height = input.get(1);

        Garden garden  = new Garden(width, height);

        for (int i = 2; i < input.size(); i+=2) {
            garden.setStone(input.get(i), input.get(i + 1));
            garden.incStones();
        }

        return garden;
    }

//    public void writeToFile(ArrayList<Solution> solutions, String filename) {
//        if(solutions.isEmpty()) {
//            System.out.println("No solutions found.");
//            System.exit(Enum.EXIT_SOLUTION_NONE);
//        }
//
//        BufferedWriter writer = null;
//
//        try {
//            writer = new BufferedWriter(new FileWriter(filename));
//        }
//        catch (IOException e)
//        {
//            System.out.println( "Unable to open " + filename + " for writing.");
//            System.exit(Enum.EXIT_WRITE_EXCEPTION);
//        }
//
//        ArrayList<ArrayList<String>> lines = solutionsToStrings(solutions);
//
//        try {
//            for(int i = 0; i < lines.size(); i++) {
//                writer.write("Solution no." + (i+1) +":\n");
//                for (int j = 0; j < lines.get(i).size(); j++) {
//                    writer.write("Step no." + (j + 1) + ": " + lines.get(i).get(j) + "\n");
//                }
//                writer.write("Run time = " + solutions.get(i).getRunTime() + "ms\n\n");
//            }
//        } catch (IOException e)
//        {
//            System.out.println( "Unable to write to " + filename + ".");
//            System.exit(Enum.EXIT_WRITE_EXCEPTION);
//        }
//
//        try {
//            writer.close();
//        } catch (IOException e) {
//            System.out.println( "Unable to close " + filename + ".");
//            System.exit(Enum.EXIT_FILE_CLOSE);
//        }
//
//    }
//
//    private ArrayList<ArrayList<String>> solutionsToStrings(ArrayList<Solution> solutions) {
//        ArrayList<ArrayList<String>> result = new ArrayList<>();
//
//        for (Solution solution : solutions) {
//            ArrayList<String> stateString = new ArrayList<>();
//            for (Puzzle state : solution.getStates()) {
//                StringBuilder line = new StringBuilder();
//                line.append("(");
//                for(int i = 0; i < state.getHeight(); i++){
//                    line.append("(");
//                    for(int j = 0; j < state.getWidth(); j++) {
//                        if(j == state.getWidth() - 1) {
//                            if (state.getMap()[i][j] == 0)
//                                line.append("m");
//                            else
//                                line.append(state.getMap()[i][j]);
//                        }
//                        else {
//                            if (state.getMap()[i][j] == 0)
//                                line.append("m").append(" ");
//                            else
//                                line.append(state.getMap()[i][j]).append(" ");
//                        }
//                    }
//                    line.append(")");
//                }
//                line.append(")");
//                stateString.add(line.toString());
//            }
//            result.add(stateString);
//        }
//
//        return result;
//    }
}
