package model.pimpekStatistic;

public class BasicPimpekStatistics implements PimpekStatistics {

    private int cloningPoints;
    private int energyPoints;

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
}
