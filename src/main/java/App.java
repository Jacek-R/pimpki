import layout.gamescreen.GameScreen;
import match.Match;

import pimpek.pimpekModel.PimpekGenre;

import supplier.BasicSupplier;
import supplier.Supplier;
import javafx.application.Application;
import javafx.stage.Stage;
import configuration.Configuration;
import configuration.WorldConfiguration;
import world.Board;

public class App extends Application {

    void run(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Supplier supplier = buildSupplier();
        Match match = supplier.getNewMatch();
        Board board = match.getBoard();

        primaryStage.setTitle("The Pimpki game");

        GameScreen gameScreen = new GameScreen(board.getGridPane(), match);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(gameScreen.buildScene());
        primaryStage.show();
    }

    private Configuration buildConfig() {

        Configuration configuration = WorldConfiguration.getInstance(27, 24,
                1, 20,
                140, 130, 1000);

        configuration.addPimpeksQuantityByGenre(PimpekGenre.PACIFIST, 7);
        configuration.addPimpeksQuantityByGenre(PimpekGenre.PREDATOR, 4);

        return configuration;
    }

    private Supplier buildSupplier() {
        return BasicSupplier.getInstance(buildConfig());
    }
}
