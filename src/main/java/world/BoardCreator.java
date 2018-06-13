package world;

import java.io.FileNotFoundException;

public interface BoardCreator {

    Board create() throws FileNotFoundException;
}
