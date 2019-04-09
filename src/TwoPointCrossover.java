import java.util.Random;

public class TwoPointCrossover implements ICrossover {
    public int[][] cross(int i, int maxGenes, int[][] child_population, int[][] population, int individual1, int individual2) {
        int limit = new Random().nextInt() * maxGenes;
        int limit2 = new Random().nextInt() * maxGenes;
        if (limit > limit2) {
            int tmp = limit;
            limit = limit2;
            limit2 = tmp;
        }

        for (int j = 0; j < maxGenes; j++) {
            if (j < limit) {
                child_population[i][j] = population[individual1][j];
                child_population[i + 1][j] = population[individual2][j];
            } else if (j < limit2) {
                child_population[i][j] = population[individual2][j];
                child_population[i + 1][j] = population[individual1][j];
            } else {
                child_population[i][j] = population[individual1][j];
                child_population[i + 1][j] = population[individual2][j];
            }
        }

        return child_population;
    }
}
