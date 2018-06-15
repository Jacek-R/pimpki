package layout.utils;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SetMargins {
    public static void hBox(double value, Node... nodes) {
        for (Node n : nodes) {
            HBox.setMargin(n, new Insets(value));
        }
    }

    public static void vBox(double value, Node... nodes) {
        for (Node n : nodes) {
            VBox.setMargin(n, new Insets(value));
        }
    }

    public static void gridPane(double value, Node... nodes) {
        for (Node n : nodes) {
            GridPane.setMargin(n, new Insets(value));
        }
    }
}
