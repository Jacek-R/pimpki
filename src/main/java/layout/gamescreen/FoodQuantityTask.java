package layout.gamescreen;

import observer.MatchObserver;

public class FoodQuantityTask extends javafx.concurrent.Task{

    private MatchObserver matchObserver;

    public FoodQuantityTask(MatchObserver matchObserver) {
        this.matchObserver = matchObserver;
    }

    @Override
    protected Void call() throws Exception {
        while (true) {
            updateMessage(String.valueOf(matchObserver.getFoodQuantity()));
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                break;
            }
        }
        return null;
    }
}
