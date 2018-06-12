package model.observer;

import model.pimpek.Pimpek;
import model.pimpekStatistic.PimpekStatistics;

import java.util.Map;
import java.util.Set;

public interface MatchObserver {

    void registerBeings(Set<Pimpek> toObserve);
    boolean clone(Pimpek pimpek);
    boolean registerEnergyPoints(Pimpek pimpek, int pointsToAdd);
    boolean registerDeath(Pimpek pimpek);
    Map<Pimpek,PimpekStatistics> getStatistics();
}
