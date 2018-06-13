package model.observer;

import model.pimpek.Pimpek;
import model.pimpekStatistic.PimpekStatistics;

import java.util.Map;
import java.util.Set;

public interface MatchObserver {

    void registerBeings(Set<Pimpek> toObserve);
    boolean registerClone(Pimpek pimpek);
    boolean registerEnergyPoints(Pimpek pimpek, int pointsToAdd);
    void registerDeath();
    Map<Pimpek,PimpekStatistics> getStatistics();
    int getLiving();
    int getDead();
}
