package GameLogic;

public class Field {
    int[][] field;
    int sizeX, sizeY;

    public Field(int x, int y) {
        field = new int[y][x];
        sizeX=x;
        sizeY=y;
    }
    public boolean isRowFull(int rowID){
        for (int i=0; i<sizeX; i++){
            if(field[rowID][i]==0) return false;
        }
        return true;
    }
    public int getItem(int x, int y){
        return field[y][x];
    }
    public void setItem(int x, int y, int value){
        field[y][x]=value;
    }
    public void moveRowsDown(int y){
        for (int i=y; i>0; i--){
            System.arraycopy(field[i-1], 0, field[i], 0, sizeX);
        }
        fillRowsWithZeroes(0);
    }
    public int[] getRow(int rowID){
        return field[rowID];
    }
    public int[] getColumn(int columnID){
        int[] column = new int[sizeY];
        for (int i=0; i<sizeY; i++){
            column[i]=field[i][columnID];
        }
        return column;
    }
    public int[][] getRangeOfLines(int startY, int endY){
        int[][] range = new int[endY-startY][sizeX];
        for (int i=0; i<endY-startY; i++){
            System.arraycopy(field[startY+i], 0, range[i], 0, sizeX);
        }
        return range;
    }
    public void fillRowsWithZeroes(int rowID){
        for (int i=0; i<sizeX; i++){
            field[rowID][i]=0;
        }
    }
}
