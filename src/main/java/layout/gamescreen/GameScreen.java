package layout.gamescreen;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import layout.utils.GridConstraints;
import layout.utils.SetMargins;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameScreen {

    private static final String BACKGROUND_PATH = "src/main/resources/img/background.png";
    private static final String GAME_TITLE = "Pimpki";
    private static final Color FONT_COLOR = Color.YELLOW;
    private static final Font FONT = Font.font(null, FontWeight.BOLD, 15);

    private static final double MAP_PERCENTAGE_SIZE = 65;
    private static final double OPTIONS_PERCENTAGE_SIZE = 35;
    private static final double ROW_PERCENTAGE_SIZE = 100;
    private static final double SMALL_MARGIN = 5;
    private static final double LARGE_MARGIN = 10;
    private static final String BTN_BG_PATH = "src/main/resources/img/btn_bg.png";
    private static final String BTN_BG_HOVER = "src/main/resources/img/btn_hover.png";
    private static final String BTN_BG_CLICK = "src/main/resources/img/btn_click.png";

    private static final int NUMBER_OF_BEST_PLAYERS = 10;
    private static final int STATISTICS_TO_TRACK = 5;

    private GridPane worldGridPane;

    private Label pimpkiQuantity;
    private Label roundNumberInformation;
    private Label foodQuantity;

    private Label[][] rowsWithBestPimpki;


    public GameScreen(GridPane worldGridPane) {
        this.worldGridPane = worldGridPane;
        rowsWithBestPimpki = new Label[NUMBER_OF_BEST_PLAYERS][STATISTICS_TO_TRACK];
    }

    public Scene buildScene() throws FileNotFoundException {
        ScrollPane mapContainer = new ScrollPane(worldGridPane);

        VBox optionsContainer = createOptionsContainer();
        GridPane root = createRoot(mapContainer, optionsContainer);

        SetMargins.gridPane(LARGE_MARGIN, mapContainer, optionsContainer);
        return new Scene(root);
    }

    private VBox createOptionsContainer() throws FileNotFoundException {
        VBox vBox = new VBox();
        GridPane header = createHeaderContainer();
        GridPane resultsTable = createResultsTableContainer();
        GridPane statsContainer = createStatsContainer();
        GridPane buttonsContainer = createButtonsContainer();
//        buttonsContainer.setVisible(false);
        vBox.getChildren().addAll(header, resultsTable, statsContainer, buttonsContainer);
        return vBox;
    }

    private GridPane createResultsTableContainer() {
        GridPane container = new GridPane();
        SetMargins.vBox(SMALL_MARGIN, container);
        GridConstraints.column(container, 20, 20, 20, 20, 20);
        container.addRow(0, createHeaderRowForTable());
        for (int i = 0; i < NUMBER_OF_BEST_PLAYERS; i++) {
            container.addRow(i + 1, createRowForResults(i));
        }
        return container;
    }

    private Node[] createHeaderRowForTable() {
        Node[] nodes = new Node[]{
                createLabel("Pos", FONT_COLOR),
                createLabel("Name", FONT_COLOR),
                createLabel("Children", FONT_COLOR),
                createLabel("Energy", FONT_COLOR),
                createLabel("Points", FONT_COLOR)};
        SetMargins.gridPane(SMALL_MARGIN, nodes);
        return nodes;
    }

    private Node[] createRowForResults(int index) {
        Label labelPosition = createLabel(String.valueOf(index + 1), FONT_COLOR);
        Label name = createLabel("Henryk", FONT_COLOR);
        Label children = createLabel(String.valueOf(index * 2), FONT_COLOR);
        Label energy = createLabel(String.valueOf(index * 10), FONT_COLOR);
        Label points = createLabel(String.valueOf(index * 22), FONT_COLOR);
        Label[] nodes = new Label[]{labelPosition, name, children, energy, points};
        rowsWithBestPimpki[index] = nodes;
        SetMargins.gridPane(SMALL_MARGIN, nodes);
        return nodes;
    }

    private GridPane createHeaderContainer() {
        GridPane container = new GridPane();
        SetMargins.vBox(SMALL_MARGIN, container);

        Label gameTitle = createLabel(GAME_TITLE, FONT_COLOR);
        roundNumberInformation = createLabel(String.format("Turn %d out of %d", 1, 10), FONT_COLOR);

        SetMargins.gridPane(SMALL_MARGIN, gameTitle, roundNumberInformation);
        GridConstraints.column(container, 50, 50);
        container.addRow(0, gameTitle, roundNumberInformation);
        return container;
    }

    private GridPane createStatsContainer() {
        GridPane container = new GridPane();
        SetMargins.vBox(SMALL_MARGIN, container);

        Label pimpkiLabel = createLabel("Pimpki left : ", FONT_COLOR);
        pimpkiQuantity = createLabel("30", FONT_COLOR);

        Label foodLabel = createLabel("Food on board : ", FONT_COLOR);
        foodQuantity = createLabel("20", FONT_COLOR);

        SetMargins.gridPane(SMALL_MARGIN, pimpkiLabel, pimpkiQuantity, foodLabel, foodQuantity);
        GridConstraints.column(container, 35, 15, 35, 15);
        container.addRow(0, pimpkiLabel, pimpkiQuantity, foodLabel, foodQuantity);
        return container;
    }

    private GridPane createButtonsContainer() throws FileNotFoundException {
        GridPane container = new GridPane();
        SetMargins.vBox(SMALL_MARGIN, container);

        Button start = createButton("Start");
        start.setOnMousePressed(createClickStartHandler(start));

        Button pause = createButton("Pause");
        pause.setOnMousePressed(createClickPauseHandler(pause));

        Button stop = createButton("Stop");
        stop.setOnMousePressed(createClickStopHandler(stop));

        SetMargins.gridPane(SMALL_MARGIN, start, pause, stop);
        GridConstraints.column(container, 33, 33, 33);
        container.addRow(0, start, pause, stop);
        return container;
    }

    private Button createButton(String text) throws FileNotFoundException {
        Button button = new Button(text);
        button.setMinWidth(100);
        button.setMinHeight(40);
        button.setOnMouseEntered(event -> {
            try {
                button.setBackground(createBackground(BTN_BG_HOVER));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        button.setOnMouseExited(event -> {
            try {
                button.setBackground(createBackground(BTN_BG_PATH));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        button.setOnMouseReleased(event -> {
            try {
                button.setBackground(createBackground(BTN_BG_PATH));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        button.setBackground(createBackground(BTN_BG_PATH));
        return button;
    }

    private EventHandler<MouseEvent> createClickStartHandler(Button button) {
        return event -> {
            try {
                button.setBackground(createBackground(BTN_BG_CLICK));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        };
    }

    private EventHandler<MouseEvent> createClickPauseHandler(Button button) {
        return event -> {
            try {
                button.setBackground(createBackground(BTN_BG_CLICK));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        };
    }

    private EventHandler<MouseEvent> createClickStopHandler(Button button) {
        return event -> {
            try {
                button.setBackground(createBackground(BTN_BG_CLICK));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        };
    }

    private Label createLabel(String value, Color color) {
        Label label = new Label(value);
        label.setTextFill(color);
        label.setFont(FONT);
        return label;
    }

    private GridPane createRoot(ScrollPane mapContainer, VBox optionsContainer) throws FileNotFoundException {
        GridPane root = new GridPane();
        root.setBackground(createBackground(BACKGROUND_PATH));
        GridConstraints.column(root, MAP_PERCENTAGE_SIZE, OPTIONS_PERCENTAGE_SIZE);
        GridConstraints.row(root, ROW_PERCENTAGE_SIZE);
        root.addRow(0, mapContainer, optionsContainer);
        return root;
    }

    private Background createBackground(String path) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(path));
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
        return new Background(backgroundImage);
    }

    public void setPimpkiQuantity(int quantity) {
        this.pimpkiQuantity.setText(String.valueOf(quantity));
    }

    public void setRoundNumberInformation(int round, int numberOfRounds) {
        this.roundNumberInformation.setText(String.format("Turn %d out of %d", round, numberOfRounds));
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity.setText(String.valueOf(foodQuantity));
    }

    public void setRowOfTheBests(int index, String name, int children, int energy, int points) {
        int nameIndex = 1;
        int childrenIndex = 2;
        int energyIndex = 3;
        int pointsIndex = 4;

        rowsWithBestPimpki[index][nameIndex].setText(name);
        rowsWithBestPimpki[index][childrenIndex].setText(String.valueOf(children));
        rowsWithBestPimpki[index][energyIndex].setText(String.valueOf(energy));
        rowsWithBestPimpki[index][pointsIndex].setText(String.valueOf(points));
    }
}
