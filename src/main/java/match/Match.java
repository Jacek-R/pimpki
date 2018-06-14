package match;

import pimpek.pimpekModel.Pimpek;
import world.Board;

public interface Match extends Runnable {

    /**
     * executes match,
     * @return Map with beings with statistics
     */
    Board getBoard();
    boolean registerClonedPlayer(Pimpek pimpek);

}
