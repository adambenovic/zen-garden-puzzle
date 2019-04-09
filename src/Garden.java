public class Garden {
    private int[][] map;
    private int width;
    private int height;
    private int stones;
    private int circumference;
    private int maxGenes;
    private int toBeRaked;

    public Garden(int height, int width) {
        this.height = height;
        this.width = width;
        this.map = new int[height][width];
        this.stones = 0;
        this.circumference = 2 * (height + width);
    }

    public Solution rake(int [] chromosome) {
        Move currentMove, previousMove;
        int[][] tmpMap = copyMap();

        int order = 1;

        for (int i = 0; i < maxGenes; i++) {
            currentMove = direction(chromosome[i]);

            if (tmpMap[currentMove.getX()][currentMove.getY()] == Enum.SAND) {
                while (isInBounds(currentMove.getX(), currentMove.getY())) {
                    if (tmpMap[currentMove.getX()][currentMove.getY()] != Enum.SAND) {
                        previousMove = currentMove.getPrevious();
                        currentMove = bump(previousMove, chromosome[i], tmpMap);

                        if (currentMove == null) {
                            while (previousMove != null) {
                                tmpMap[previousMove.getX()][previousMove.getY()] = Enum.SAND;
                                previousMove = previousMove.getPrevious();
                            }
                            //System.out.println("Got stuck at chromosome[" + chromosome[i] +"], raked back.");
                            break;
                        }

                        if (!isInBounds(currentMove.getX(), currentMove.getY()))
                            break;
                    }

                    tmpMap[currentMove.getX()][currentMove.getY()] = order;
                    currentMove = new Move(currentMove);
                }

                order++;
            }
        }

        return new Solution(tmpMap, height, width, chromosome, maxGenes, raked(tmpMap));
    }

    public int raked(int[][] tmpMap) {
        int ok = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (tmpMap[i][j] != Enum.SAND && tmpMap[i][j] != Enum.STONE)
                    ok++;
            }
        }

        return ok;
    }

    public Move direction(int gene) {
        gene = Math.abs(gene);

        if (gene <= width) {
            return new Move(0, gene - 1, Enum.DOWN);
        } else if (gene <= (circumference/2) ){
            return new Move(gene - width - 1, width - 1 , Enum.LEFT);
        } else if (gene <= (circumference/2 + height) ) {
            return new Move((gene - (circumference/2) - 1), 0, Enum.RIGHT);
        } else {
            return new Move((height - 1), (gene - (circumference/2 + height) - 1), Enum.UP);
        }
    }

    public Move bump(Move move, int gene, int[][] tmpMap) {
        Move newMove = null;
        int moveDirection = move.getDirection();

        if (moveDirection == Enum.UP || moveDirection == Enum.DOWN)
            newMove = checkLeftRight(move, gene, tmpMap);
        else if (moveDirection == Enum.LEFT || moveDirection == Enum.RIGHT)
            newMove = checkUpDown(move, gene, tmpMap);

        return newMove;
    }

    public Move checkLeftRight(Move move, int gene, int[][] tmpMap) {
        Move newMove = null;
        int x = move.getX(), y = move.getY();
        boolean leftValid = isInBounds(x, y - 1) && tmpMap[x][y - 1] == Enum.SAND && isInBounds(x, y - 1);
        boolean rightValid = isInBounds(x, y + 1) && tmpMap[x][y + 1] == Enum.SAND && isInBounds(x, y + 1);

        if (leftValid && rightValid) {
            if (gene < 0)
                newMove = new Move(move, Enum.LEFT);
            else
                newMove = new Move(move, Enum.RIGHT);
        } else if (leftValid) {
            newMove = new Move(move, Enum.LEFT);
        } else if (rightValid) {
            newMove = new Move(move, Enum.RIGHT);
        }

        return newMove;
    }

    public Move checkUpDown(Move move, int gene, int[][] tmpMap) {
        Move newMove = null;
        int x = move.getX(), y = move.getY();
        boolean upValid = isInBounds(x -1, y) && tmpMap[x -1][y] == Enum.SAND && isInBounds(x - 1, y);
        boolean downValid = isInBounds(x + 1, y) && tmpMap[x + 1][y] == Enum.SAND && isInBounds(x + 1, y);

        if (upValid && downValid) {
            if (gene < 0)
                newMove = new Move(move, Enum.UP);
            else
                newMove = new Move(move, Enum.DOWN);
        } else if (upValid) {
            newMove = new Move(move, Enum.UP);
        } else if (downValid) {
            newMove = new Move(move, Enum.DOWN);
        }

        return newMove;
    }

    public int[][] copyMap() {
        int[][] target = new int[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(map[i], 0, target[i], 0, width);
        }

        return target;
    }

    public boolean isInBounds(int x, int y) {
        if (x < height && x >= 0 && y < width && y >= 0) {
            return true;
        }

        return false;
    }

    public boolean setStone(int x, int y) {
        if (this.isInBounds(x, y)) {
            map[x][y] = Enum.STONE;

            return true;
        }

        return false;
    }

    public void printMap(int[][] map) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                System.out.format("%2d ", map[i][j]);
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

    public int getToBeRaked() {
        return this.toBeRaked;
    }

    public void setToBeRaked() {
        this.toBeRaked = (this.width * this.height) - this.stones;
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
