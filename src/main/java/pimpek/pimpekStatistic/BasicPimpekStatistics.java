package pimpek.pimpekStatistic;

public class BasicPimpekStatistics implements PimpekStatistics {

    private int cloningPoints;
    private int energyPoints;
    private final int CLONING_BONUS = 100;

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
    public int getTotalPoints() {
        return energyPoints + cloningPoints * CLONING_BONUS;
    }
}
