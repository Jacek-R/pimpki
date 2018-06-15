package observer;

import match.Match;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekStatistic.PimpekStatistics;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NullObserver implements MatchObserver {

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
    public void registerFoodConsumption() {

    }

    @Override
    public Map<Pimpek, PimpekStatistics> getBeingsAndStats() {
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

    @Override
    public void rejuvenate() { }

    @Override
    public void registerMatch(Match match) {

    }

    @Override
    public int getFoodQuantity() {
        return 0;
    }

    @Override
    public void executeFoodSpawn() throws FileNotFoundException {

    }
}
