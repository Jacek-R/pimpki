package match;

import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekStatistic.PimpekStatistics;
import world.Board;

import java.util.Map;

public interface Match extends Runnable {

    /**
     * executes match,
     * @return Map with beings with statistics
     */

//    Map<Pimpek, PimpekStatistics> executeMatch();
    Board getBoard();

}
