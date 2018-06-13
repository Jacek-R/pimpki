package model.pimpekSpawner;

import explorer.WorldManager;
import model.coordinates.Coordinates;
import model.coordinates.Coords;
import model.pimpek.Pimpek;
import world.World;

import java.util.Set;

public class BasicPimpekSpawner implements PimpekSpawner {

    private World world;
    private final WorldManager worldManager;

    public BasicPimpekSpawner(World world, WorldManager worldManager) {
        this.world = world;
        this.worldManager = worldManager;
    }

    @Override
    public boolean spawn(Set<Pimpek> pimpki) {

        // algorytm wybiera coordynaty (Coordinates)
        // po wybraniu rejetrujemy w worldManager:
        // worldManager.registerBeing(pimpek); <-- ta metoda (worldManagera) mogłaby również seterem ustawić
        // Content w danym cell


        return true;
    }

    @Override
    public boolean spawnClone(Pimpek cloned, Pimpek parent) {

        // put registerClone on the world map - JW.

        return true;
    }
}
