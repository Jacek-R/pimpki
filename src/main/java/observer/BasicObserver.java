package observer;

import food.foodModel.Food;
import food.foodSpawner.FoodSpawner;
import match.Match;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekCloner.PimpekCloner;
import pimpek.pimpekSpawner.PimpekSpawner;
import pimpek.pimpekStatistic.BasicPimpekStatistics;
import pimpek.pimpekStatistic.PimpekStatistics;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BasicObserver implements MatchObserver {

    private final PimpekCloner pimpekCloner;
    private final PimpekSpawner pimpekSpawner;
    private final FoodSpawner foodSpawner;
    private Match match;
    private Map<Pimpek, PimpekStatistics> beingsAndStats;
    private int living;
    private int dead;
    private int foodQuantity;
    private final Set<Food> fodder;

    public BasicObserver(PimpekCloner pimpekCloner, PimpekSpawner pimpekSpawner, FoodSpawner foodSpawner,
                         Set<Pimpek> beings, Set<Food> fodder) {
        this.pimpekCloner = pimpekCloner;
        this.pimpekSpawner = pimpekSpawner;
        this.foodSpawner = foodSpawner;
        this.beingsAndStats = new HashMap<>();
        beings.forEach(this::registerPimpek);
        this.fodder = fodder;
        this.living = beings.size();
        this.foodQuantity = fodder.size();
    }

    @Override
    public boolean registerClone(Pimpek pimpek) throws FileNotFoundException {

        getPimpekStatistics(pimpek).incrementCloningPoints();
        Pimpek cloned = pimpekCloner.clone(pimpek);
        cloned.setObserver(this);

        living++;
        if (match == null) {
            return false;
        }
        pimpekSpawner.spawnClone(cloned, pimpek);
        return match.registerClonedPlayer(cloned);
    }

    @Override
    public boolean registerEnergyPoints(Pimpek pimpek, int pointsToAdd) {
        getPimpekStatistics(pimpek).incrementEnergyPoints(pointsToAdd);
        return true;
    }

    @Override
    public void registerDeath() throws FileNotFoundException {
        dead++;
        living--;
        handleFoodSpawn();
    }

    @Override
    public synchronized void registerFoodConsumption() {
        foodQuantity--;
    }

    @Override
    public Map<Pimpek, PimpekStatistics> getBeingsAndStats() {
        return beingsAndStats;
    }

    @Override
    public int getLiving() {
        return living;
    }

    @Override
    public int getDead() {
        return dead;
    }

    @Override
    public void rejuvenate() {
        beingsAndStats.keySet().forEach(Pimpek::regenerate);
    }

    @Override
    public void registerMatch(Match match) {
        this.match = match;
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

    private void handleFoodSpawn() throws FileNotFoundException {
        if (foodQuantity*0.5 < living) {
            foodSpawner.spawn(fodder);
            foodQuantity += foodSpawner.getSpawnedFood();
        }
    }

    @Override
    public int getFoodQuantity() {
        if (foodQuantity < 0) {
            return 0;
        }
        return foodQuantity;
    }
}
