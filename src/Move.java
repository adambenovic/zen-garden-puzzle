public class Move {
    private int x;
    private int y;
    private int direction;
    private Move previous;

    public Move(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Move(Move move) {
        switch (move.getDirection()) {
            case Enum.UP:
                this.x = move.getX() - 1;
                this.y = move.getY();
                this.direction = Enum.UP;
                break;
            case Enum.DOWN:
                this.x = move.getX() + 1;
                this.y = move.getY();
                this.direction = Enum.DOWN;
                break;
            case Enum.LEFT:
                this.x = move.getX();
                this.y = move.getY() - 1;
                this.direction = Enum.LEFT;
                break;
            case Enum.RIGHT:
                this.x = move.getX();
                this.y = move.getY() + 1;
                this.direction = Enum.RIGHT;
                break;
            default:
                System.out.println("Creating from previous move.");
        }

        this.previous = move;
    }

    public Move (Move move, int direction) {
        switch (direction) {
            case Enum.UP:
                this.x = move.getX() - 1;
                this.y = move.getY();
                this.direction = Enum.UP;
                break;
            case Enum.DOWN:
                this.x = move.getX() + 1;
                this.y = move.getY();
                this.direction = Enum.DOWN;
                break;
            case Enum.LEFT:
                this.x = move.getX();
                this.y = move.getY() - 1;
                this.direction = Enum.LEFT;
                break;
            case Enum.RIGHT:
                this.x = move.getX();
                this.y = move.getY() + 1;
                this.direction = Enum.RIGHT;
                break;
            default:
                System.out.println("Creating from previous move and new direction.");
        }

        this.previous = move;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Move getPrevious() {
        return previous;
    }

    public void setPrevious(Move previous) {
        this.previous = previous;
    }
}
