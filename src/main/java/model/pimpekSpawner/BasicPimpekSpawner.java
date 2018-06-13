package model.pimpekSpawner;

import model.World;
import model.configuration.Configuration;
import model.helpers.NameGenerator;
import model.pimpek.Pimpek;

import java.util.Set;

public class BasicPimpekSpawner implements PimpekSpawner {

    private World world;

    public BasicPimpekSpawner(World world) {
        this.world = world;
    }

    @Override
    public boolean spawn(Set<Pimpek> pimpki) {


        // put pimpki on the world map

        return true;
    }

    @Override
    public boolean spawnClone(Pimpek cloned, Pimpek parent) {

        // put registerClone on the world map

        return true;
    }
}
