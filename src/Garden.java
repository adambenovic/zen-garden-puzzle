import java.lang.reflect.Array;

public class Garden {
    private int[][] map;
    private int width;
    private int height;
    private int stones;
    private int circumference;
    private int maxGenes;

    public Garden(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new int[width][height];
        this.stones = 0;
        this.circumference = 2 * width + 2 * height;
    }

    public int rake() {
        
    }

    public boolean setStone(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height)
            return false;

        map[x][y] = Enum.STONE;

        return true;
    }

    public void printMap() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public void incStones() {
        stones++;
    }

    public void computeMaxGenes() {
        this.maxGenes = this.circumference / 2 + this.stones;
    }

    public int getMaxGenes() {
        return maxGenes;
    }

    public void setMaxGenes(int maxGenes) {
        this.maxGenes = maxGenes;
    }

    public int getStones() {
        return stones;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public int getCircumference() {
        return circumference;
    }

    public void setCircumference(int circumference) {
        this.circumference = circumference;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
