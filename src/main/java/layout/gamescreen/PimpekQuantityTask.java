package layout.gamescreen;

import observer.MatchObserver;

public class PimpekQuantityTask extends javafx.concurrent.Task{

    private MatchObserver matchObserver;

    public PimpekQuantityTask(MatchObserver matchObserver) {
        this.matchObserver = matchObserver;
    }

    @Override
    protected Void call() throws Exception {
    while (true) {
        updateMessage(String.valueOf(matchObserver.getLiving()));
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            break;
        }
    }
    return null;
    }
}
