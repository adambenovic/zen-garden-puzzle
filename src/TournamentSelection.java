import java.util.ArrayList;
import java.util.Random;

public class TournamentSelection implements ISelection {
    public int select(ArrayList<Solution> solutions, int sum) {
        int individual1 = (int)(new Random().nextFloat() * Enum.POPULATION_SIZE);
        int individual2 = (int)(new Random().nextFloat() * Enum.POPULATION_SIZE);
        int individual3 = (int)(new Random().nextFloat() * Enum.POPULATION_SIZE);

        int who = solutions.get(individual1).getFitness() > solutions.get(individual2).getFitness() ? individual1 : individual2;
        who = solutions.get(individual3).getFitness() > solutions.get(who).getFitness() ? individual3 : who;

        return who;
    }
}
