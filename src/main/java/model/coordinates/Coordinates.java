package model.coordinates;

import java.util.List;

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
    List<Coordinates> getNeighbors();
    int getX();
    int getY();


}
