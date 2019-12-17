package GameLogic;

public class Field {
    int[][] field;
    int columns, rows;

    public Field(int columns, int rows) {
        field = new int[rows][columns];
        this.columns = columns;
        this.rows = rows;
    }

    public boolean isRowFull(int rowID){
        for (int i = 0; i<columns; i++){
            if(field[rowID][i]==0) return false;
        }
        return true;
    }
    public int getItem(int column, int row){
        return field[row][column];
    }
    public void setItem(int column, int row, int value){
        field[row][column]=value;
    }
    public void moveRowsDown(int rowID){
        for (int i=rowID; i>0; i--){
            System.arraycopy(field[i-1], 0, field[i], 0, columns);
        }
        fillRow(0, 0);
    }
    public void moveColumnsLeft(int columnID){
        for (int i = 0; i < rows; i++){
            for (int j = columnID; j < columns; j++){
                if(j == columns-1) field[i][j]=0;
                else field[i][j]=Integer.valueOf(field[i][j+1]);
            }
        }
        fillColumn(columns-1, 0);
    }
    public void fillColumn(int columnID, int color){
        for (int i = 0; i< rows; i++){
            field[i][columnID] = color;
        }
    }
    public void fillRow(int rowID, int color){
        for (int i = 0; i< columns; i++){
            field[rowID][i] = color;
        }
    }
    public int[] getRow(int rowID){
        return field[rowID];
    }
    public int[][] getRangeOfRows(int startY, int endY){
        int[][] range = new int[endY-startY][columns];
        for (int i=0; i<endY-startY; i++){
            System.arraycopy(field[startY+i], 0, range[i], 0, columns);
        }
        return range;
    }
}