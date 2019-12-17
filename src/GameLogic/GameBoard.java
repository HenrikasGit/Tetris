package GameLogic;

public class GameBoard {
    private int columnsSize = 10;
    private int rowsSize = 20;
    private Field field;
    private int clearedRows = 0;

    public GameBoard() {
        rowsSize +=ShapeCache.getMaxShapeSize();
        field = new Field(columnsSize, rowsSize);
    }

    public int getColumnsSize() {
        return columnsSize;
    }

    public int[][] getVisibleGameBoard(){
        return field.getRangeOfRows(ShapeCache.getMaxShapeSize(), rowsSize);
    }
    public void replaceFigure(Shape oldFigure, Shape newFigure){
        removeFigure(oldFigure);
        setFigure(newFigure);
    }
    public int getClearedRows(){
        return clearedRows;
    }
    public void removeRows(){
        for (int row=rowsSize-1; row>0; row--){
            if(field.isRowFull(row)){
                field.moveRowsDown(row);
                clearedRows++;
                row++;
            }
        }
    }
    public void setFigure(Shape figure){
        int blockValue=0;
        Position position = figure.getPosition();
        int posX=position.getPositionX();
        int posY=position.getPositionY();
        for (int row=0; row<figure.getSize(); row++){
            for (int column=0; column<figure.getSize(); column++){
                if(figure.getBlock(column,row)>0){
                    if(blockValue==0) blockValue=figure.getBlock(column,row);
                    field.setItem(column+posX,row+posY,blockValue);
                }
            }
        }
    }
    public void removeFigure(Shape figure){
        Position position = figure.getPosition();
        int posX=position.getPositionX();
        int posY=position.getPositionY();
        for (int row=0; row<figure.getSize(); row++){
            for (int column=0; column<figure.getSize(); column++){
                if(figure.getBlock(column,row)>0) field.setItem(column+posX,row+posY, 0);
            }
        }
    }
    public int getBlock(int column, int row){
        if(!isBlockInLimits(column,row)) return 0;
        return field.getItem(column,row);
    }
    public boolean isBlockInLimits(int column, int row){
        if(column<0 || column> columnsSize -1 || row<0 || row> rowsSize -1) return false;
        return true;
    }
    public void print(){
        for (int i=0; i<rowsSize; i++){
            for (int j=0; j<columnsSize; j++){
                System.out.printf(field.getItem(j, i)+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
