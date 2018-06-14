package coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Coords implements Coordinates {

    private int x;
    private int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Coordinates get(int x, int y) {
        return new Coords(x,y);
    }

    @Override
    public Coordinates getN() {
        return new Coords(x, y+1);
    }

    @Override
    public Coordinates getNE() {
        return new Coords(x+1, y+1);
    }

    @Override
    public Coordinates getE() {
        return new Coords(x+1, y);
    }

    @Override
    public Coordinates getSE() {
        return new Coords(x+1, y-1);
    }

    @Override
    public Coordinates getS() {
        return new Coords(x, y-1);
    }

    @Override
    public Coordinates getSW() {
        return new Coords(x-1, y-1);
    }

    @Override
    public Coordinates getW() {
        return new Coords(x-1, y);
    }

    @Override
    public Coordinates getNW() {
        return new Coords(x-1, y+1);
    }

    @Override
    public List<Coordinates> getNeighbors() {

        return new ArrayList<>(Arrays.asList(getE(), getN(), getS(), getW(), getNE(), getNW(), getSE(), getSW()));
    }

    @Override
    public int hashCode() {
        int multiplier = 16;
        return (x-1)*multiplier + (y+1)*(multiplier+1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Coordinates) {
            Coordinates coordinates = (Coordinates) obj;
            return this.x == coordinates.getX() && this.y == coordinates.getY();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("x(%s), y(%s)", x, y);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
