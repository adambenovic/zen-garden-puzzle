import java.util.Random;

public class UniformCrossover implements ICrossover {
    public int[][] cross(int i, int maxGenes, int[][] child_population, int[][] population, int individual1, int individual2) {
        for (int j = 0; j < maxGenes; j++) {
            if (new Random().nextFloat() < 0.5f) {
                child_population[i][j] = population[individual1][j];
                child_population[i + 1][j] = population[individual2][j];
            } else {
                child_population[i][j] = population[individual2][j];
                child_population[i + 1][j] = population[individual1][j];
            }
        }

        return child_population;
    }
}
