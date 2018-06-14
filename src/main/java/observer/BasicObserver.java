package observer;

import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekCloner.PimpekCloner;
import pimpek.pimpekSpawner.PimpekSpawner;
import pimpek.pimpekStatistic.BasicPimpekStatistics;
import pimpek.pimpekStatistic.PimpekStatistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BasicObserver implements MatchObserver {

    private final PimpekCloner pimpekCloner;
    private final PimpekSpawner pimpekSpawner;
    private Map<Pimpek, PimpekStatistics> beingsAndStats;
    private int living;
    private int dead;

    public BasicObserver(PimpekCloner pimpekCloner, PimpekSpawner pimpekSpawner, Set<Pimpek> beings) {
        this.pimpekCloner = pimpekCloner;
        this.pimpekSpawner = pimpekSpawner;
        this.beingsAndStats = new HashMap<>();
        beings.forEach(this::registerPimpek);
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
    public void rejuvenate() {
        beingsAndStats.keySet().forEach(Pimpek::regenerate);
    }

    private void registerPimpek(Pimpek pimpek) {
        beingsAndStats.put(pimpek.getAncestor(), new BasicPimpekStatistics());
        pimpek.setObserver(this);
    }

    private PimpekStatistics getPimpekStatistics(Pimpek pimpek) {
        if (! beingsAndStats.containsKey(pimpek)) {
            registerPimpek(pimpek);
        }
        return beingsAndStats.get(pimpek);
    }
}
