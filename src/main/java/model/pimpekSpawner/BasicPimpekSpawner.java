package model.pimpekSpawner;

import model.World;
import model.configuration.Configuration;
import model.helpers.NameGenerator;
import model.pimpek.Pimpek;

import java.util.Set;

public class BasicPimpekSpawner implements PimpekSpawner {

    private World world;
    private final NameGenerator nameGenerator;

    public BasicPimpekSpawner(World world, NameGenerator nameGenerator) {
        this.world = world;
        this.nameGenerator = nameGenerator;
    }

    @Override
    public boolean spawn(Set<Pimpek> pimpki) {
        return true;
    }

    @Override
    public boolean spawnClone(Pimpek cloned, Pimpek parent) {
        return true;
    }
}
