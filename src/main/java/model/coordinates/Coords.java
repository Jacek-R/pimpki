package model.coordinates;

public class Coords implements Coordinates {

    private int x;
    private int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Coordinates get(int x, int y) {
        setX(x);
        setY(y);
        return this;
    }

    @Override
    public Coordinates getN() {
        y++;
        return this;
    }

    @Override
    public Coordinates getNE() {
        x++;
        y++;
        return null;
    }

    @Override
    public Coordinates getE() {
        x++;
        return this;
    }

    @Override
    public Coordinates getSE() {
        y--;
        x++;
        return this;
    }

    @Override
    public Coordinates getS() {
        y--;
        return this;
    }

    @Override
    public Coordinates getSW() {
        x--;
        y--;
        return this;
    }

    @Override
    public Coordinates getW() {
        x--;
        return this;
    }

    @Override
    public Coordinates getNW() {
        x--;
        y++;
        return this;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        int multiplier = 16;
        return (x-1)*multiplier + (y+1)*(multiplier+1);
    }

}
