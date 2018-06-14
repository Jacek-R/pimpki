package match;

import model.pimpek.Pimpek;
import model.pimpekStatistic.PimpekStatistics;

import java.util.Map;

public interface Match {

    /**
     * executes match,
     * @return Map with beings with statistics
     */

    Map<Pimpek, PimpekStatistics> executeMatch();

}
