package coordinates;

import java.util.Set;

public interface Coordinates {

    Coordinates get(int x, int y);
    Coordinates getN();
    Coordinates getNE();
    Coordinates getE();
    Coordinates getSE();
    Coordinates getS();
    Coordinates getSW();
    Coordinates getW();
    Coordinates getNW();
    Set<Coordinates> getNeighbors();
    int getX();
    int getY();


}
