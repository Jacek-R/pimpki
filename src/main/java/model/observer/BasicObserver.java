package model.observer;

import model.pimpek.Pimpek;
import model.pimpekGenerator.PimpekGenerator;
import model.pimpekSpawner.PimpekSpawner;
import model.pimpekStatistic.BasicPimpekStatistics;
import model.pimpekStatistic.PimpekStatistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BasicObserver implements MatchObserver {

    private final PimpekGenerator pimpekGenerator;
    private final PimpekSpawner pimpekSpawner;
    private Map<Pimpek, PimpekStatistics> statistics;
    private int living;
    private int dead;

    public BasicObserver(PimpekGenerator pimpekGenerator, PimpekSpawner pimpekSpawner) {
        this.pimpekGenerator = pimpekGenerator;
        this.pimpekSpawner = pimpekSpawner;
        this.statistics = new HashMap<>();
    }

    public BasicObserver(PimpekGenerator pimpekGenerator, PimpekSpawner pimpekSpawner, Set<Pimpek> pimpki) {
        this.pimpekGenerator = pimpekGenerator;
        this.pimpekSpawner = pimpekSpawner;
        this.statistics = new HashMap<>();
        pimpki.forEach(this::registerPimpek);
    }

    @Override
    public void registerBeings(Set<Pimpek> toObserve) {
        toObserve.forEach(this::registerPimpek);
    }

    @Override
    public boolean registerClone(Pimpek pimpek) {

        getPimpekStatistics(pimpek).incrementCloningPoints();
        Pimpek cloned = pimpekGenerator.clone(pimpek);
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
    public Map<Pimpek, PimpekStatistics> getStatistics() {
        return statistics;
    }

    @Override
    public int getLiving() {
        int total = statistics.size() + living - dead;
        if (total < 0) {
            total = 0;
        }
        return total;
    }

    @Override
    public int getDead() {
        return dead;
    }

    private void registerPimpek(Pimpek pimpek) {
        statistics.put(pimpek, new BasicPimpekStatistics());
    }

    private PimpekStatistics getPimpekStatistics(Pimpek pimpek) {
        if (! statistics.containsKey(pimpek)) {
            registerPimpek(pimpek);
        }
        return statistics.get(pimpek);
    }
}
