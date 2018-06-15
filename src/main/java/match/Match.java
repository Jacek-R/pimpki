package match;

import layout.gamescreen.GameScreen;
import observer.MatchObserver;
import pimpek.pimpekModel.Pimpek;
import world.Board;

public interface Match extends Runnable {

    /**
     * executes match,
     * @return Map with beings with statistics
     */
    Board getBoard();
    boolean registerClonedPlayer(Pimpek pimpek);
    MatchObserver getObserver();

    void setGameScreen(GameScreen gameScreen);
}
