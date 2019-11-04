package GameLogic;

public class Validation {
    GameBoard gameBoard;
    public Validation(GameBoard gameBoard){
        this.gameBoard=gameBoard;
    }
    public boolean isFigureInLimits(Shape figure){
        int posY=figure.getPositionY();
        int posX=figure.getPositionX();
        for (int i=posY; i<posY+figure.getSize(); i++) {
            for (int j = posX; j < posX+figure.getSize(); j++) {
                if (figure.getBlock(j - posX, i - posY) > 0 && gameBoard.isBlockInLimits(j, i) == false) return false;
            }
        }
        return true;
    }
    public boolean doesFigureFit(Shape oldFigure, Shape newFigure){
        try{
            if(oldFigure!=newFigure) gameBoard.removeFigure(oldFigure);
        } catch (Exception e){
            return false;
        }
        int posY=newFigure.getPositionY();
        int posX=newFigure.getPositionX();
        for (int i=posY; i<posY+oldFigure.getSize(); i++){
            for (int j=posX; j<posX+oldFigure.getSize(); j++){
                if(newFigure.getBlock(j-posX,i-posY)>0 && gameBoard.getBlock(j,i)>0){
                    if(oldFigure!=newFigure) gameBoard.setFigure(oldFigure);
                    return false;
                }
            }
        }
        if(oldFigure!=newFigure) gameBoard.setFigure(oldFigure);
        return true;
    }
    public boolean isGameOver(){
        for (int i=0; i<4; i++){
            for (int j=0; j<10; j++){
                if(gameBoard.getBlock(j,i)>0) return true;
            }
        }
        return false;
    }
    public boolean canFigureBeThere(Shape oldFigure, Shape newFigure){
        if(doesFigureFit(oldFigure, newFigure)==true && isFigureInLimits(newFigure)==true){
            return true;
        }
        return false;
    }
    public boolean canFigureMoveDown(Shape figure){
        if(figure==null) return false;
        Shape newFigure = figure.copy(true);
        newFigure.changeRelativeVerticalPosition(1);
        return canFigureBeThere(figure, newFigure);
    }
}


