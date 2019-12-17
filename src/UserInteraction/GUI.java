package UserInteraction;

import GameLogic.ShapeCache;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI {
    private int mainSquareSize = 30;
    private int infoSquareSize = mainSquareSize*2/3;
    private Grid gamePane;
    private Grid nextFigureDisplay;
    private Grid savedFigureDisplay;
    private Label rowsClearedLabel = new Label();
    public GUI(Stage stage, int mainColumns, int mainRows){
        HBox root = new HBox();
        gamePane = new Grid(mainColumns, mainSquareSize, mainRows, mainSquareSize, "000000");
        int maxFigureSize = ShapeCache.getMaxShapeSize();
        root.getChildren().addAll(gamePane.getGridPane(), getInfoPane(maxFigureSize));
        stage.setTitle("Tetris");
        stage.setScene(new Scene(root));
        stage.show();
    }
    private void setUpLabel(Label label, String text){
        label.setText(text);
        label.setTextFill(Color.WHITE);
    }

    private VBox getInfoPane(int maxFigureSize){
        VBox infoPane = new VBox();
        infoPane.setBackground(new Background(new BackgroundFill(Color.web("202020"), CornerRadii.EMPTY, Insets.EMPTY)));
        Label nextFigureLabel = new Label();
        Label savedFigureLabel = new Label();
        setUpLabel(nextFigureLabel, "Next");
        setUpLabel(savedFigureLabel,  "Saved");
        rowsClearedLabel.setTextFill(Color.WHITE);
        nextFigureDisplay = new Grid(maxFigureSize, infoSquareSize, maxFigureSize, infoSquareSize, "000020");
        savedFigureDisplay = new Grid(maxFigureSize,infoSquareSize,maxFigureSize,infoSquareSize, "000020");
        infoPane.getChildren().setAll(nextFigureLabel, nextFigureDisplay.getGridPane(), savedFigureLabel, savedFigureDisplay.getGridPane(), rowsClearedLabel);
        return infoPane;
    }
    public void paintGUI(int[][] gameMap, int[][] nextFigure, int[][] savedFigure, int clearedLines){
        gamePane.refillPane(gameMap);
        nextFigureDisplay.refillPane(nextFigure);
        savedFigureDisplay.refillPane(savedFigure);
        rowsClearedLabel.setText("Cleared\nrows: "+clearedLines);
    }
}