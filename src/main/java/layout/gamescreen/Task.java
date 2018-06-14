package layout.gamescreen;

import observer.MatchObserver;

public class Task extends javafx.concurrent.Task{

    private MatchObserver matchObserver;

    public Task(MatchObserver matchObserver) {
        this.matchObserver = matchObserver;
    }

    @Override
    protected Void call() throws Exception {
    while (true) {
        updateMessage(String.valueOf(matchObserver.getLiving()));
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            break;
        }
    }
    return null;
    }
}
