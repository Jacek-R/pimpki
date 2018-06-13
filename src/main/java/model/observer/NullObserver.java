package model.observer;

import model.pimpek.Pimpek;
import model.pimpekStatistic.PimpekStatistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NullObserver implements MatchObserver {

    @Override
    public void registerBeings(Set<Pimpek> toObserve) { }

    @Override
    public boolean registerClone(Pimpek pimpek) {
        return false;
    }

    @Override
    public boolean registerEnergyPoints(Pimpek pimpek, int pointsToAdd) {
        return false;
    }

    @Override
    public void registerDeath() { }

    @Override
    public Map<Pimpek, PimpekStatistics> getStatistics() {
        return new HashMap<>();
    }

    @Override
    public int getLiving() {
        return 0;
    }

    @Override
    public int getDead() {
        return 0;
    }
}