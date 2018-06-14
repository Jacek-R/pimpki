package observer;

import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekStatistic.PimpekStatistics;

import java.util.Map;
import java.util.Set;

public interface MatchObserver {

    void registerBeings(Set<Pimpek> toObserve);
    boolean registerClone(Pimpek pimpek);
    boolean registerEnergyPoints(Pimpek pimpek, int pointsToAdd);
    void registerDeath();
    Map<Pimpek,PimpekStatistics> getBeingsAndStats();
    int getLiving();
    int getDead();
    void reset();
}
