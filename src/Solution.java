import java.util.ArrayList;

public class Solution {
    private int[][] map;
    private int height;
    private int width;
    private int fitness;
    private int[] chromosome;
    private long runTime;

    public Solution(int[][] map, int height, int width, int[] chromosome, int maxGenes, int fitness) {
        this.height = height;
        this.width = width;
        this.fitness = fitness;
        this.map = new int[height][width];

        for (int i = 0; i < height; i++)
            System.arraycopy(map[i], 0, this.map[i], 0, width);

        this.chromosome = new int[maxGenes];
        System.arraycopy(chromosome, 0, this.chromosome, 0, maxGenes);
    }

    public void printMap() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long startTime, long finishTime) {
        this.runTime = finishTime - startTime;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int[] getChromosome() {
        return chromosome;
    }

    public void setChromosome(int[] chromosome) {
        this.chromosome = chromosome;
    }
}
