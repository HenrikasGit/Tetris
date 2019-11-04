package GameLogic;

public class GameBoard {
    private Field field = new Field(10, 24);
    private int clearedRows = 0;
    public int[][] getVisibleGameBoard(){
        return field.getRangeOfLines(4,24);
    }
    public void replaceFigure(Shape oldFigure, Shape newFigure){
        removeFigure(oldFigure);
        setFigure(newFigure);
    }
    public int getClearedRows(){
        return clearedRows;
    }
    public void moveRowsDown(int y){
        for (int i=y; i>0; i--){
            System.arraycopy(field.getRow(i-1), 0, field.getRow(i), 0, 10);
        }
    }
    public void removeRows(){
        for (int i=23; i>0; i--){
            if(field.isRowFull(i)){
                moveRowsDown(i);
                clearedRows++;
                i++;
            }
        }
    }
    public void setFigure(Shape figure){
        int blockValue=0;
        int posX=figure.getPositionX();
        int posY=figure.getPositionY();
        for (int i=posY; i<posY+figure.getSize(); i++){
            for (int j=posX; j<posX+figure.getSize(); j++){
                if(figure.getBlock(j-posX,i-posY)>0){
                    if(blockValue==0) blockValue=figure.getBlock(j-posX,i-posY);
                    field.setItem(j,i,blockValue);
                }
            }
        }
    }
    public void removeFigure(Shape figure){
        int posX=figure.getPositionX();
        int posY=figure.getPositionY();
        for (int i=posY; i<posY+figure.getSize(); i++){
            for (int j=posX; j<posX+figure.getSize(); j++){
                if(figure.getBlock(j-posX,i-posY)>0) field.setItem(j,i, 0);
            }
        }
    }
    public int getBlock(int x, int y){
        if(!isBlockInLimits(x,y)) return 0;
        return field.getItem(x,y);
    }
    public boolean isBlockInLimits(int x, int y){
        if(x<0 || x>9 || y<0 || y>23) return false;
        return true;
    }
}
