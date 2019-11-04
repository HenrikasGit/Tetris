package GameLogic;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private GridPane gamePane = new GridPane();
    private GridPane nextFigureDisplay = new GridPane();
    private GridPane savedFigureDisplay = new GridPane();
    private Label rowsClearedLabel = new Label();
    private Timeline timeline;
    private Double frameLength=250.0;
    private double speedup=1.03;
    private Duration duration = Duration.millis(frameLength);
    private GameEngine gameEngine = new GameEngine();
    private Shape nextFigure;
    private int clearedLines=0;
    private Shape savedFigure;
    @Override
    public void start(Stage stage){
        setUpGUI(stage);
        startTimer();
        startKeyListener(stage);
        play();
    }
    public void startTimer(){
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                gameEngine.progress();
                paintGUI();
                clearedLines=gameEngine.getClearedLines();
                speedup=Math.pow(speedup, clearedLines);
                timeline.setRate(speedup);
            }
        };
        KeyFrame keyFrame = new KeyFrame(duration, onFinished);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    private void paintGUI(){
        int[][] gameMap = gameEngine.getVisibleGameBoard();
        fillPane(gameMap, gamePane);
        nextFigure=gameEngine.getNextFigure();
        int[][] nextFigureArray = nextFigure.getShapeArray();
        fillPane(nextFigureArray, nextFigureDisplay);
        savedFigure = gameEngine.getSavedFigure();
        int[][] savedFigureArray = savedFigure.getShapeArray();
        fillPane(savedFigureArray, savedFigureDisplay);
        rowsClearedLabel.setText("Cleared\nrows: "+clearedLines);
    }
    private void addTime(){
        double maxMillis, jumpToMillis;
        maxMillis = 175/speedup;
        jumpToMillis = 100/speedup;
        if(timeline.getCurrentTime().greaterThan(Duration.millis(maxMillis))) timeline.jumpTo(Duration.millis(jumpToMillis));
    }
    private void startKeyListener(Stage stage){
        stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                KeyCode pressedButton = ke.getCode();
                if (pressedButton == KeyCode.DOWN) gameEngine.moveFigure("Down");
                else if (pressedButton == KeyCode.LEFT){
                    gameEngine.moveFigure("Left");
                    addTime();
                }
                else if (pressedButton == KeyCode.RIGHT){
                    gameEngine.moveFigure("Right");
                    addTime();
                }
                else if (pressedButton == KeyCode.UP){
                    gameEngine.rotateFigure();
                    addTime();
                }
                else if (pressedButton == KeyCode.CONTROL) gameEngine.saveFigure();
                else if (pressedButton == KeyCode.SPACE) gameEngine.smashFigureDown();
                paintGUI();
            }
        });
    }
    private void createBlock(int x, int y, int color, GridPane gridPane){
        Rectangle block = new Rectangle();
        int dimention = (int)gridPane.getRowConstraints().get(0).getPrefHeight()-2;
        block.setHeight(dimention);
        block.setWidth(dimention);
        setBlockColor(color, block);
        gridPane.add(block, x,y);
    }
    private void setBlockColor(int color, Rectangle block){
        if (color == 1) block.setFill(Color.LIGHTGOLDENRODYELLOW);
        else if (color == 2) block.setFill(Color.LIGHTBLUE);
        else if (color == 3) block.setFill(Color.BLUE);
        else if (color == 4) block.setFill(Color.ORANGE);
        else if (color == 5) block.setFill(Color.DARKGREEN);
        else if (color == 6) block.setFill(Color.RED);
        else if (color == 7) block.setFill(Color.PURPLE);
        else block.setFill(Color.BLACK);
    }
    private void paintLine(int lineID, int[] field, GridPane gridPane){
        for (int i=0; i<field.length; i++){
            if(field[i]>0) createBlock(i, lineID, field[i], gridPane);
        }
    }
    private void clearPane(GridPane gridPane){
        ObservableList nodes = gridPane.getChildren();
        for (int i=0; i<nodes.size(); i++){
            if(nodes.get(i).getClass()==Rectangle.class) {
                gridPane.getChildren().remove(nodes.get(i));
                i--;
            }
        }
    }
    private void fillPane(int[][] field, GridPane gridPane){
        clearPane(gridPane);
        for (int i=0; i<field.length; i++) paintLine(i, field[i], gridPane);
    }
    private void play(){
        gameEngine.progress();
        nextFigure = gameEngine.getNextFigure();
        paintGUI();

    }
    private void setUpGUI(Stage stage){
        HBox root = new HBox();
        setUpGrid(gamePane, 10, 30, 20, 30, "000000");
        root.getChildren().addAll(gamePane, getInfoPane());
        stage.setTitle("Tetris");
        stage.setScene(new Scene(root));
        stage.show();
    }
    private VBox getInfoPane(){
        VBox infoPane = new VBox();
        infoPane.setBackground(new Background(new BackgroundFill(Color.web("202020"), CornerRadii.EMPTY, Insets.EMPTY)));
        Label nextFigureLabel = new Label();
        Label savedFigureLabel = new Label();
        setUpLabel(nextFigureLabel, "Next");
        setUpLabel(savedFigureLabel,  "Saved");
        rowsClearedLabel.setTextFill(Color.WHITE);
        setUpGrid(nextFigureDisplay, 4, 20, 4, 20, "000020");
        setUpGrid(savedFigureDisplay, 4,20,4,20, "000020");
        infoPane.getChildren().setAll(nextFigureLabel, nextFigureDisplay, savedFigureLabel, savedFigureDisplay, rowsClearedLabel);
        return infoPane;
    }
    private void setUpLabel(Label label, String text){
        label.setText(text);
        label.setTextFill(Color.WHITE);
    }
    private void setRows(GridPane gridPane, int rowsCount, int height){
        for (int i = 0; i < rowsCount; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(height);
            gridPane.getRowConstraints().add(rowConst);
        }
    }
    private void setColumns(GridPane gridPane, int columnsCount, int width){
        for (int i = 0; i < columnsCount; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(width);
            gridPane.getColumnConstraints().add(colConst);
        }
    }
    private void setUpGrid(GridPane gridPane, int columnsCount, int width, int rowsCount, int height, String color){
        setColumns(gridPane, columnsCount, width);
        setRows(gridPane, rowsCount, height);
        gridPane.setGridLinesVisible(true);
        gridPane.setBackground(new Background(new BackgroundFill(Color.web(color), CornerRadii.EMPTY, Insets.EMPTY)));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
