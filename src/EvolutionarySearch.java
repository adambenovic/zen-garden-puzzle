import java.util.ArrayList;

public class EvolutionarySearch {

    public ArrayList<Solution> solve(ArrayList<Garden> gardens, int limit) {
        ArrayList<Solution> solutions = new ArrayList<>();

        for (Garden garden : gardens) {
            int[] fitness = new int[Enum.POPULATION_SIZE];
            int[][] population = new int[Enum.POPULATION_SIZE][garden.getMaxGenes()];
            int generations = 0, maxPrevious;
            float mutationRate = Enum.MIN_MUTATION_RATE;

            long startTime = System.currentTimeMillis();

            for (int i = 0; i < Enum.POPULATION_SIZE; i++)
                population[i] = generateChromosome();




            //VYVOJ GENERACII
            while (generations++ < Enum.MAX_GENERATIONS) {
                int[][] child = new int[Enum.POPULATION_SIZE][garden.getMaxGenes()];
                int max, max_i, min, sum = 0;

                //POHRAB ZAHRADU A VYPOCITAJ FITNESS FUNKCIU
                for (int i = 0; i < Enum.POPULATION_SIZE; i++) {
                    fitness[i] = garden.rakeGarden(population[i], false, false);
                }
            }
        }


        return solutions;
    }

    public int[] generateChromosome() {

    }
}
