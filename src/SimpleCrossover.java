import java.util.Random;

public class SimpleCrossover implements ICrossover {
    public int[][] cross(int i, int maxGenes, int[][] child_population, int[][] population, int individual1, int individual2) {
        int limit = (int) (new Random().nextFloat() * maxGenes);

        for (int j = 0; j < maxGenes; j++) {
            if (j < limit) {
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
