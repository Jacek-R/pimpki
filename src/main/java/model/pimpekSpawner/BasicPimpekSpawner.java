package model.pimpekSpawner;

import explorer.WorldManager;
import model.pimpek.Pimpek;
import java.util.Set;

public class BasicPimpekSpawner implements PimpekSpawner {

    private WorldManager worldManager;

    public BasicPimpekSpawner(WorldManager worldManager) {
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
