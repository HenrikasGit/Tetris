package GameLogic;

public class GameEngine {
    private Shape activeFigure, nextFigure;
    private Shape savedFigure = new Shape(0,0);
    GameBoard gameBoard;
    Validation validation;
    private int clearedLines=0;
    private boolean canSaveFigure =true;

    public GameEngine() {
        ShapeCache.loadCache();
        ShapeCache.countMaxShapeSize();
        gameBoard = new GameBoard();
        ShapeCache.setSpawnPoint(gameBoard);
        validation = new Validation(gameBoard);
        activeFigure = createRandomFigure();
        nextFigure = createRandomFigure();
        spawnFigure();

    }

    public Shape getNextFigure(){ return nextFigure; }
    public Shape getSavedFigure(){ return savedFigure; }
    public int getClearedLines(){ return clearedLines; }
    public int[][] getVisibleGameBoard(){ return gameBoard.getVisibleGameBoard(); }
    public void putFigureDown(){
        while(validation.canFigureMove(activeFigure, "Down")) moveFigure("Down");
        progress();
    }
    public Shape createRandomFigure(){
        Shape randomFigure;
        int rand=(int)(Math.random()*7)+1;
        randomFigure = ShapeCache.getShape(String.valueOf(rand));
        return randomFigure;
    }
    private void spawnFigure(){
        while(validation.doesFigureFit(activeFigure)==false){
            activeFigure.go("Up", 1);
        }
        gameBoard.setFigure(activeFigure);
    }
    public void saveFigure(){
        if(!canSaveFigure) return;
        gameBoard.removeFigure(activeFigure);
        if(savedFigure.getClass()==Shape.class){
            savedFigure=ShapeCache.getShape(activeFigure.getId());
            createNewFigures();
        } else {
            Shape temporary = ShapeCache.getShape(savedFigure.getId());
            savedFigure=ShapeCache.getShape(activeFigure.getId());
            activeFigure=ShapeCache.getShape(temporary.getId());
            spawnFigure();
        }
        canSaveFigure = false;
    }

    public void progress(){
        if(validation.canFigureMove(activeFigure, "Down")){
            moveFigure("Down");
        } else {
            gameBoard.removeRows();
            clearedLines=gameBoard.getClearedRows();
            if(validation.isGameOver()) System.exit(0);
            createNewFigures();
        }
    }
    public void createNewFigures(){
        canSaveFigure = true;
        activeFigure=nextFigure.clone();
        nextFigure = createRandomFigure();
        spawnFigure();
    }
    public void moveFigure(String direction){
        Shape newFigure = activeFigure.clone();
        newFigure.go(direction, 1);
        if(validation.canFigureMove(activeFigure, direction)) {
            gameBoard.replaceFigure(activeFigure, newFigure);
            activeFigure=newFigure;
        }
    }
    public void rotateActiveFigure(){
        Shape rotatedFigure = activeFigure.clone();
        rotatedFigure.performRotation();
        if(validation.canReplaceFigure(activeFigure, rotatedFigure)){
            activeFigure=rotatedFigure;
            gameBoard.setFigure(activeFigure);
        } else findPlaceForRotatedFigure(rotatedFigure);
    }
    private void findPlaceForRotatedFigure(Shape figure){
        for (int proximity=1; proximity<4; proximity++){
            Position position = whereCanBePlacedInProximity(proximity, figure);
            if(position!=null){
                figure.setPosition(position);
                gameBoard.replaceFigure(activeFigure, figure);
                activeFigure=figure;
                break;
            }
        }
    }
    public Position whereCanBePlacedInProximity(int proximity, Shape figure){
        String[] directions = {"Right", "Left", "Down", "Down-Right", "Down-Left", "Up-Left", "Up-Right", "Up"};
        for (int direction=0; direction<directions.length; direction++){
            figure.go(directions[direction], proximity);
            if(validation.canFigureBeThere(figure)) return figure.getPosition();
            figure.go(directions[direction], -proximity);
        }
        return null;
    }
}