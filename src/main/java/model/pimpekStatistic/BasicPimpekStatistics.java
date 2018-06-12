package model.pimpekStatistic;

public class BasicPimpekStatistics implements PimpekStatistics {

    private int cloningPoints;
    private int energyPoints;
    private boolean isDead;

    @Override
    public void incrementEnergyPoints(int toAdd) {
        energyPoints += toAdd;
    }

    @Override
    public void incrementCloningPoints() {
        cloningPoints++;
    }

    @Override
    public int getEnergyPoints() {
        return energyPoints;
    }

    @Override
    public int getCloningPoints() {
        return cloningPoints;
    }

    @Override
    public boolean isLive() {
        return ! isDead;
    }

    @Override
    public void registerDeath() {
        isDead = true;
    }
}
