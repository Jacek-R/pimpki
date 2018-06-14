package parser.statisticsToPoints;

import pimpek.pimpekStatistic.PimpekStatistics;

public class PimpekPoints implements StatisticToPoints {


    @Override
    public int getPoints(PimpekStatistics statistics) {
        return statistics.getEnergyPoints() + statistics.getCloningPoints()*100;
    }
}
