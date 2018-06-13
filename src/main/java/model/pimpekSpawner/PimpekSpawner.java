package model.pimpekSpawner;

import model.pimpek.Pimpek;

import java.io.FileNotFoundException;
import java.util.Set;

public interface PimpekSpawner {

    boolean spawn(Set<Pimpek> pimpki) throws FileNotFoundException;
    boolean spawnClone(Pimpek cloned, Pimpek parent);  // parent to get parent's position
}
