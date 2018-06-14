package observer;

import match.Match;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekStatistic.PimpekStatistics;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

public interface MatchObserver {

    boolean registerClone(Pimpek pimpek) throws FileNotFoundException;
    boolean registerEnergyPoints(Pimpek pimpek, int pointsToAdd);
    void registerDeath();
    Map<Pimpek,PimpekStatistics> getBeingsAndStats();
    int getLiving();
    int getDead();
    void rejuvenate();
    void registerMatch(Match match);
}
