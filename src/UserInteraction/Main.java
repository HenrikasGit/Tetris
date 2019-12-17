package UserInteraction;
import GameLogic.GameEngine;
import GameLogic.Shape;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private Timeline timeline;
    private Double initialFrameLength = 250.0;
    private double speedup = 1.03;
    private Duration duration = Duration.millis(initialFrameLength);
    private GameEngine gameEngine = new GameEngine();
    private Shape nextFigure;
    private int clearedLines=0;
    private GUI gui;
    private boolean endOfGame=false;
    private Stage stage;
    private double addedTime;
    @Override
    public void start(Stage stage){
        this.stage = stage;
        int[][] gameBoard = gameEngine.getVisibleGameBoard();
        gui = new GUI(stage, gameBoard[0].length, gameBoard.length);
        startTimer();
        startKeyListener();
        play();
    }
    public void startTimer(){
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame;
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                addedTime=0;
                gameEngine.progress();
                gui.paintGUI(gameEngine.getVisibleGameBoard(), gameEngine.getNextFigure().getShapeArray(),
                        gameEngine.getSavedFigure().getShapeArray(), gameEngine.getClearedLines());
                clearedLines=gameEngine.getClearedLines();
                timeline.setRate(Math.pow(speedup, clearedLines));
            }
        };
        keyFrame = new KeyFrame(duration, onFinished);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    private void addTime(){
        if(addedTime>initialFrameLength*2/timeline.getRate()) return;
        Duration currentTime = timeline.getCurrentTime();
        addedTime+=currentTime.toMillis();
        timeline.jumpTo(Duration.millis(0));
    }
    private void performAction(String command){
        if(command=="Space") gameEngine.putFigureDown();
        else if(command=="Control") gameEngine.saveFigure();
        else if(command=="Up") {
            gameEngine.rotateActiveFigure();
            addTime();
        }
        else if(command=="No Input");
        else {
            gameEngine.moveFigure(command);
            addTime();
        }
    }
    private void startKeyListener(){
        stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if(endOfGame) return;
                String command = InputInterpreter.interpretKeyboard(ke);
                performAction(command);
                gui.paintGUI(gameEngine.getVisibleGameBoard(), gameEngine.getNextFigure().getShapeArray(),
                        gameEngine.getSavedFigure().getShapeArray(), gameEngine.getClearedLines());
            }
        });
    }
    private void play(){
        gameEngine.progress();
        nextFigure = gameEngine.getNextFigure();
        gui.paintGUI(gameEngine.getVisibleGameBoard(), gameEngine.getNextFigure().getShapeArray(), gameEngine.getSavedFigure().getShapeArray(), gameEngine.getClearedLines());
    }
    public static void main(String[] args) {
        launch(args);
    }
}