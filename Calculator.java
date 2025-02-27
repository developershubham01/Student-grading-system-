import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application {
    private TextField display = new TextField();

    @Override
    public void start(Stage primaryStage) {
        display.setEditable(false);
        display.setStyle("-fx-font-size: 20px; -fx-alignment: center-right;");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        int row = 1, col = 0;
        for (String text : buttons) {
            Button button = new Button(text);
            button.setMinSize(50, 50);
            button.getStyleClass().add("button");

            button.setOnAction(e -> handleButtonClick(text));

            grid.add(button, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        grid.add(display, 0, 0, 4, 1);

        Scene scene = new Scene(grid, 250, 300);
        scene.getStylesheets().add("style.css");

        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(String text) {
        if (text.equals("=")) {
            try {
                String expression = display.getText();
                double result = eval(expression);
                display.setText(String.valueOf(result));
            } catch (Exception e) {
                display.setText("Error");
            }
        } else if (text.equals("C")) {
            display.clear();
        } else {
            display.appendText(text);
        }
    }

    private double eval(String expression) {
        return new javafx.scene.web.WebEngine().executeScript("eval('" + expression + "')").hashCode();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
