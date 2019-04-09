import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EvolutionarySearch {
    private ISelection selection;
    private ICrossover crossover;

    public EvolutionarySearch(ISelection selection, ICrossover crossover) {
        this.selection = selection;
        this.crossover = crossover;
    }

    public ArrayList<Solution> solve(ArrayList<Garden> gardens, int limit) {
        ArrayList<Solution> solutions = new ArrayList<>();

        for (Garden garden : gardens) {
            int[][] population = new int[Enum.POPULATION_SIZE][garden.getMaxGenes()];
            int generations = 0;
            float mutationRate = Enum.MIN_MUTATION_RATE;
            ArrayList<Solution> gardenSolutions = new ArrayList<>();
            boolean isSolution = false;
            int max = 0, maxPrev = 0, maxi = 0, min = 0, sum = 0;
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < Enum.POPULATION_SIZE; i++)
                population[i] = generateChromosome(garden.getMaxGenes());

            while (generations++ < Enum.MAX_GENERATIONS) {
                gardenSolutions = new ArrayList<>();
                int[][] childPopulation = new int[Enum.POPULATION_SIZE][garden.getMaxGenes()];

                for (int i = 0; i < Enum.POPULATION_SIZE; i++)
                    gardenSolutions.add(garden.rake(population[i]));

                max = gardenSolutions.get(0).getFitness();
                maxi = 0;
                min = max;
                sum = 0;

                for (int i = 0; i < Enum.POPULATION_SIZE; i++) {
                    int fitness = gardenSolutions.get(i).getFitness();
                    sum += fitness;
                    if (fitness > max) {
                        max = fitness;
                        maxi = i;
                    }
                    if (fitness < min)
                        min = fitness;
                }

                if (max == garden.getToBeRaked()) {
                    isSolution = true;
                    break;
                }

                if (mutationRate >= Enum.MAX_MUTATION_RATE || max != maxPrev){
                    maxPrev = max;
                    mutationRate = Enum.MIN_MUTATION_RATE;
                } else if (mutationRate < Enum.MAX_MUTATION_RATE )
                    mutationRate += 0.01f;

                for (int i = 0; i < Enum.POPULATION_SIZE; i++)
                    gardenSolutions.get(i).setFitness(gardenSolutions.get(i).getFitness() - min);

                for (int i = 0; i < Enum.POPULATION_SIZE; i += 2) {
                    int individual1 = selection.select(gardenSolutions, sum);
                    int individual2 = selection.select(gardenSolutions, sum);

                    if (new Random().nextFloat() < Enum.CROSSOVER_RATE) { 	// IDE SA KRIZIT
                        childPopulation = crossover.cross(i, garden.getMaxGenes(), childPopulation, population, individual1, individual2);

                        for (int j = 0; j < 2; j++) {
                            for (int k = 0; k < garden.getMaxGenes(); k++) {
                                if (new Random().nextFloat() < mutationRate) {
                                    int mutation = (int) (new Random().nextFloat() * (garden.getCircumference() - 1)) + 1;

                                    if (new Random().nextFloat() < 0.5f)
                                        mutation *= -1;

                                    int index = -1;

                                    for (int l = 0; l < garden.getMaxGenes(); l++)
                                        if (childPopulation[i + j][l] == mutation) {
                                            index = l;
                                            break;
                                        }

                                    if (index != -1) {
                                        int tmp = childPopulation[i + j][k];
                                        childPopulation[i + j][k] = childPopulation[i + j][index];
                                        childPopulation[i + j][index] = tmp;
                                    }
                                    else
                                        childPopulation[i + j][k] = mutation;
                                }
                            }
                        }
                    } else
                        for (int j = 0; j < garden.getMaxGenes(); j++) {
                            childPopulation[i][j] = population[individual1][j];
                            childPopulation[i + 1][j] = population[individual2][j];
                        }
                }

                for (int i = 0; i < garden.getMaxGenes(); i++)
                    childPopulation[0][i] = population[maxi][i];

                for (int i = 0; i < Enum.POPULATION_SIZE; i++)
                    System.arraycopy(childPopulation[i], 0, population[i], 0, garden.getMaxGenes());
            }

            long finishTime = System.currentTimeMillis();
            if (isSolution) {
                gardenSolutions.get(maxi).setRunTime(startTime, finishTime);
                solutions.add(gardenSolutions.get(maxi));
                System.out.println("Solution found. Run time: " + gardenSolutions.get(maxi).getRunTime() + " ms.");
                System.out.printf("Generation:%d Max:%4d Min:%4d Avg:%4d Mutation rate:%1.2f\n", generations, max, min, sum / Enum.POPULATION_SIZE, mutationRate);
                continue;
            }
            System.out.println("Solution not found. Run time: " + (finishTime - startTime) + " ms.");
        }

        return solutions;
    }


    public int[] generateChromosome(int maxGenes) {
        int[] chromosome = new int[maxGenes];
        List<Integer> range = new ArrayList<>();

        for (int i = 1; i <= maxGenes; i++)
            range.add(i);

        Collections.shuffle(range);

        for (int i = 0; i < maxGenes; i++) {
            if (Math.random() < 0.5f)
                chromosome[i] = range.get(i) * -1;
            else
                chromosome[i] = range.get(i);
        }

        return chromosome;
    }
}
