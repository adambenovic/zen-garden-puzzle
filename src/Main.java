import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Main solver = new Main();

        solver.run(args[0], args[1], args[2]);
    }

    public void run(String inputFile, String solutionFile, String timeLimit)
    {
        int limit = Integer.parseInt(timeLimit);
        IO io = new IO();
        ArrayList<Garden> gardens = io.loadFromFile(inputFile);

//        for (Garden garden:gardens) {
//            System.out.println("Map:");
//            garden.printMap();
//        }

        ArrayList<Solution> solutions;
        EvolutionarySearch solver = new EvolutionarySearch();

        solutions = solver.solve(gardens, limit);
//        io.writeToFile(solutions, solutionFile);
    }
}
