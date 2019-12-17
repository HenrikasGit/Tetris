package GameLogic;

public class Validation {
    GameBoard gameBoard;
    public Validation(GameBoard gameBoard){
        this.gameBoard=gameBoard;
    }
    public boolean isFigureInLimits(Shape figure){
        Position position = figure.getPosition();
        int posX = position.getPositionX();
        int posY = position.getPositionY();
        for (int row=0; row<figure.getSize(); row++){
            for (int column=0; column<figure.getSize(); column++){
                if(figure.getBlock(column, row)>0 && gameBoard.isBlockInLimits(column+posX, row+posY) == false) return false;
            }
        }
        return true;
    }
    public boolean doesFigureFit(Shape figure){
        Position newPosition = figure.getPosition();
        int posY=newPosition.getPositionY();
        int posX=newPosition.getPositionX();
        for (int row=0; row<figure.getSize(); row++){
            for (int column=0; column<figure.getSize(); column++){
                if(figure.getBlock(column, row)>0 && gameBoard.getBlock(column+posX, row+posY)>0) return false;
            }
        }
        return true;
    }
    public boolean isGameOver(){
        for (int row=0; row<ShapeCache.getMaxShapeSize(); row++){
            for (int column=0; column<gameBoard.getColumnsSize(); column++){
                if(gameBoard.getBlock(column,row)>0) return true;
            }
        }
        return false;
    }
    public boolean canFigureBeThere(Shape figure){
        return doesFigureFit(figure) && isFigureInLimits(figure);
    }
    public boolean canReplaceFigure(Shape oldFigure, Shape newFigure){
        gameBoard.removeFigure(oldFigure);
        if(canFigureBeThere(newFigure)) return true;
        else {
            gameBoard.setFigure(oldFigure);
            return false;
        }
    }
    public boolean canFigureMove(Shape figure, String direction){
        if(figure==null) return false;
        Shape newFigure = (Shape)figure.clone();
        newFigure.go(direction, 1);
        return canReplaceFigure(figure, newFigure);
    }
}