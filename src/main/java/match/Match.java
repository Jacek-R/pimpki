package match;

import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekStatistic.PimpekStatistics;

import java.util.Map;

public interface Match {

    /**
     * executes match,
     * @return Map with beings with statistics
     */

    Map<Pimpek, PimpekStatistics> executeMatch();

}
