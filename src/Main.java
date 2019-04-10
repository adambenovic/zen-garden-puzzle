import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Main solver = new Main();

        solver.run(args[0], args[1], args[2], args[3]);
    }

    public void run(String inputFile, String solutionFile, String selectionMethod, String crossoverMethod)
    {
        IO io = new IO();
        ArrayList<Garden> gardens = io.loadFromFile(inputFile);
        ISelection selection = null;
        ICrossover crossover = null;

        switch (Integer.parseInt(selectionMethod)) {
            case 1:
                selection = new RouletteSelection();
                break;
            case 2:
                selection = new TournamentSelection();
                break;
            default:
                System.out.println("Wrong selection. Enter 1 for Roulette, 2 for Tournament");
        }

        switch (Integer.parseInt(crossoverMethod)) {
            case 1:
                crossover = new SimpleCrossover();
                break;
            case 2:
                crossover = new UniformCrossover();
                break;
            case 3:
                crossover = new TwoPointCrossover();
                break;
            default:
                System.out.println("Wrong crossover. Enter 1 for Simple, 2 for Uniform, 3 for TwoPoint");
        }

        ArrayList<Solution> solutions;
        EvolutionarySearch solver = new EvolutionarySearch(selection, crossover);

        solutions = solver.solve(gardens);
        io.writeToFile(solutions, solutionFile);
    }
}
