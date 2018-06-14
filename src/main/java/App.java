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
        primaryStage.setTitle("Super title");

        primaryStage.setFullScreen(true);
        primaryStage.setScene(new GameScreen(board.getGridPane()).buildScene());
        primaryStage.show();

        Thread duel = new Thread(match);
        duel.start();
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

        Configuration configuration = WorldConfiguration.getInstance(10, 10,
                1, 10,
                140, 10, 100);
        configuration.addPimpeksQuantityByGenre(PimpekGenre.PACIFIST, 3);
        return configuration;
    }

    private Supplier buildSupplier() {
        return BasicSupplier.getInstance(buildConfig());
    }
}
