package model.observer;

import model.pimpek.Pimpek;
import model.pimpekCloner.PimpekCloner;
import model.pimpekSpawner.PimpekSpawner;
import model.pimpekStatistic.BasicPimpekStatistics;
import model.pimpekStatistic.PimpekStatistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BasicObserver implements MatchObserver {

    private final PimpekCloner pimpekCloner;
    private final PimpekSpawner pimpekSpawner;
    private Map<Pimpek, PimpekStatistics> beingsAndStats;
    private int living;
    private int dead;

    public BasicObserver(PimpekCloner pimpekCloner, PimpekSpawner pimpekSpawner) {
        this.pimpekCloner = pimpekCloner;
        this.pimpekSpawner = pimpekSpawner;
        this.beingsAndStats = new HashMap<>();
    }

    public BasicObserver(PimpekCloner pimpekCloner, PimpekSpawner pimpekSpawner, Set<Pimpek> pimpki) {
        this.pimpekCloner = pimpekCloner;
        this.pimpekSpawner = pimpekSpawner;
        this.beingsAndStats = new HashMap<>();
        pimpki.forEach(this::registerPimpek);
    }

    @Override
    public void registerBeings(Set<Pimpek> toObserve) {
        toObserve.forEach(this::registerPimpek);
    }

    @Override
    public boolean registerClone(Pimpek pimpek) {

        getPimpekStatistics(pimpek).incrementCloningPoints();
        Pimpek cloned = pimpekCloner.clone(pimpek);
        living++;
        return pimpekSpawner.spawnClone(cloned, pimpek);
    }

    @Override
    public boolean registerEnergyPoints(Pimpek pimpek, int pointsToAdd) {
        getPimpekStatistics(pimpek).incrementEnergyPoints(pointsToAdd);
        return true;
    }

    @Override
    public void registerDeath() {
        dead++;
    }

    @Override
    public Map<Pimpek, PimpekStatistics> getBeingsAndStats() {
        return beingsAndStats;
    }

    @Override
    public int getLiving() {
        int total = beingsAndStats.size() + living - dead;
        if (total < 0) {
            total = 0;
        }
        return total;
    }

    @Override
    public int getDead() {
        return dead;
    }

    @Override
    public void resurrectBeings() {
        for(Pimpek being : beingsAndStats.keySet()) {
            being.regenerate();
        }
    }

    private void registerPimpek(Pimpek pimpek) {
        beingsAndStats.put(pimpek, new BasicPimpekStatistics());
    }

    private PimpekStatistics getPimpekStatistics(Pimpek pimpek) {
        if (! beingsAndStats.containsKey(pimpek)) {
            registerPimpek(pimpek);
        }
        return beingsAndStats.get(pimpek);
    }
}
