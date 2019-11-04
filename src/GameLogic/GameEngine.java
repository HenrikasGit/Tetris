package GameLogic;

import Shapes.*;

public class GameEngine {
    private Shape activeFigure, nextFigure;
    private Shape savedFigure = new Shape(0,0);
    GameBoard gameBoard = new GameBoard();
    Validation validation = new Validation(gameBoard);
    private int clearedLines=0;
    private boolean canSaveShape=true;
    public Shape getNextFigure(){
        return nextFigure;
    }
    public Shape getSavedFigure(){
        return savedFigure;
    }
    public int getClearedLines(){
        return clearedLines;
    }
    public int[][] getVisibleGameBoard(){
        return gameBoard.getVisibleGameBoard();
    }
    public void smashFigureDown(){
        while(validation.canFigureMoveDown(activeFigure)){
            moveFigure("Down");
        }
        progress();
    }
    public Shape createRandomFigure(){
        Shape randomFigure;
        int rand=(int)(Math.random()*7);
        if (rand==0) randomFigure = new Alpha();
        else if (rand==1) randomFigure = new Gamma();
        else if (rand==2) randomFigure = new LeftSkew();
        else if (rand==3) randomFigure = new Line();
        else if (rand==4) randomFigure = new Pyramid();
        else if (rand==5) randomFigure = new RightSkew();
        else randomFigure = new Square();
        return randomFigure;
    }
    private void spawnFigure(){
        int posX=3;
        int posY=3;
        activeFigure.setPosition(posX, posY);
        while(validation.doesFigureFit(activeFigure, activeFigure)==false){
            posY--;
            activeFigure.setPosition(posX, posY);
        }
        gameBoard.setFigure(activeFigure);
    }
    public void saveFigure(){
        if(canSaveShape){
            gameBoard.removeFigure(activeFigure);
            if(savedFigure.getClass()==Shape.class){
                savedFigure=activeFigure.copy(false);
                createNewFigures();
            } else {
                Shape temporary=savedFigure.copy(false);
                savedFigure=activeFigure.copy(false);
                activeFigure=temporary.copy(false);
                spawnFigure();
            }
            canSaveShape=false;
        }
    }

    public void progress(){
        if(validation.canFigureMoveDown(activeFigure)){
            moveFigure("Down");
        } else {
            gameBoard.removeRows();
            clearedLines=gameBoard.getClearedRows();
            if(validation.isGameOver()) System.exit(0);
            createNewFigures();
        }
    }
    public void createNewFigures(){
        canSaveShape=true;
        if(nextFigure==null) activeFigure = createRandomFigure();
        else {
            activeFigure=nextFigure.copy(true);
        }
        nextFigure = createRandomFigure();
        spawnFigure();
    }
    public Shape createVirtualFigureForMovement(String direction, int amount, Shape figure){
        Shape virtualShape = figure.copy(true);
        int newPosY=virtualShape.getPositionY();
        int newPosX=virtualShape.getPositionX();
        if(direction=="Down") newPosY+=amount;
        else if(direction=="Left") newPosX-=amount;
        else if(direction=="Right") newPosX+=amount;
        else if(direction=="Up") newPosY-=amount;
        else if(direction=="Down-Left"){
            newPosY+=amount;
            newPosX-=amount;
        }
        else if(direction=="Down-Right"){
            newPosY+=amount;
            newPosX+=amount;
        }
        else if(direction=="Up-Left"){
            newPosY-=amount;
            newPosX-=amount;
        }
        else{
            newPosY-=amount;
            newPosX+=amount;
        }
        virtualShape.setPosition(newPosX, newPosY);
        return virtualShape;
    }
    public void moveFigure(String direction){
        Shape newFigure = createVirtualFigureForMovement(direction, 1, activeFigure);
        if(validation.canFigureBeThere(activeFigure, newFigure)) {
            gameBoard.replaceFigure(activeFigure, newFigure);
            activeFigure=newFigure;
        }
    }
    public void rotateFigure(){
        Shape rotatedFigure = activeFigure.copy(true);
        rotatedFigure.rotate();
        if(validation.canFigureBeThere(activeFigure, rotatedFigure)){
            gameBoard.replaceFigure(activeFigure, rotatedFigure);
            activeFigure=rotatedFigure.copy(true);
        } else {
            for (int i=1; i<3; i++){
                if(whereCanBePlaced(i, rotatedFigure)!=null){
                    rotatedFigure.setPosition(whereCanBePlaced(i, rotatedFigure));
                    gameBoard.replaceFigure(activeFigure, rotatedFigure);
                    activeFigure=rotatedFigure;
                    break;
                }
            }
        }
    }
    private Position whereCanBePlaced(int amount, Shape figure){
        String[] directions = {"Right", "Left", "Down", "Down-Right", "Down-Left", "Up-Left", "Up-Right", "Up"};
        for (int i=0; i<directions.length; i++){
            Shape newFigure = createVirtualFigureForMovement(directions[i], amount, figure);
            if(validation.canFigureBeThere(activeFigure, newFigure)) return newFigure.getPosition();
        }
        return null;
    }
}
