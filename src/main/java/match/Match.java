package match;

import observer.MatchObserver;
import world.Board;

public interface Match extends Runnable {

    /**
     * executes match,
     * @return Map with beings with statistics
     */

    Board getBoard();
    MatchObserver getObserver();

}
