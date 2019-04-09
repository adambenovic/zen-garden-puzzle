import java.util.ArrayList;
import java.util.Random;

public class RouletteSelection implements ISelection {
    public int select(ArrayList<Solution> solutions, int sum) {
        float random = randUniformPositive() * sum;
        for (int i = 0; i < Enum.POPULATION_SIZE; i++) {
            random -= solutions.get(i).getFitness();
            if (random < 0) {
                return i;
            }
        }

        return solutions.size() -1;
    }

    private float randUniformPositive() {
        return new Random().nextFloat();
    }
}
