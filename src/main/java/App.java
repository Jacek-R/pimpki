import layout.gamescreen.GameScreen;
import match.Match;

import pimpek.pimpekModel.Angry;
import pimpek.pimpekModel.PimpekGenre;

import supplier.BasicSupplier;
import supplier.Supplier;
import tournament.PimpekTournament;
import tournament.Tournament;
import javafx.application.Application;
import javafx.stage.Stage;
import configuration.Configuration;
import configuration.WorldConfiguration;
import world.Board;

import java.io.FileNotFoundException;

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

    private void startTournament() {

        try {
            buildTournament().begin();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Problem occurred!");
        }
    }

    private Tournament buildTournament() {

        return PimpekTournament.getInstance(buildSupplier());
    }


    private Configuration buildConfig() {

        Configuration configuration = WorldConfiguration.getInstance(10, 10, 1, 50,
                140, 100, 100);
        configuration.addPimpeksQuantityByGenre(PimpekGenre.PACIFIST, 1);
        return configuration;
    }

    private Supplier buildSupplier() {
        return BasicSupplier.getInstance(buildConfig());
    }
}
