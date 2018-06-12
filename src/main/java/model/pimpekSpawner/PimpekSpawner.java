package model.pimpekSpawner;

import model.pimpek.Pimpek;

import java.util.Set;

public interface PimpekSpawner {

    boolean spawn(Set<Pimpek> pimpki);
    boolean spawnClone(Pimpek cloned, Pimpek parent);  // parent to get parent's position
}
