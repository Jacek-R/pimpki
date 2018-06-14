package pimpek.pimpekSpawner;

import pimpek.pimpekModel.Pimpek;

import java.io.FileNotFoundException;
import java.util.Set;

public interface PimpekSpawner {

    boolean spawn(Set<Pimpek> pimpki) throws FileNotFoundException;
    boolean spawnClone(Pimpek cloned, Pimpek parent) throws FileNotFoundException;  // parent to get parent's position
}
