package UserInteraction;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid {
    GridPane gridPane = new GridPane();

    public GridPane getGridPane() {
        return gridPane;
    }

    public Grid(int columnsCount, int width, int rowsCount, int height, String color){
        setColumns(columnsCount, width);
        setRows(rowsCount, height);
        gridPane.setGridLinesVisible(true);
        gridPane.setBackground(new Background(new BackgroundFill(Color.web(color), CornerRadii.EMPTY, Insets.EMPTY)));
    }
    private void setRows(int rowsCount, int height){
        for (int row = 0; row < rowsCount; row++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPrefHeight(height);
            gridPane.getRowConstraints().add(rowConst);
        }
    }
    private void setColumns(int columnsCount, int width){
        for (int column = 0; column < columnsCount; column++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPrefWidth(width);
            gridPane.getColumnConstraints().add(colConst);
        }
    }
    private void createBlock(int column, int row, int color){
        Rectangle block = new Rectangle();
        int dimention = (int)gridPane.getRowConstraints().get(0).getPrefHeight()-2;
        block.setHeight(dimention);
        block.setWidth(dimention);
        setBlockColor(color, block);
        gridPane.add(block, column, row);
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
    private void clearPane(GridPane gridPane){
        ObservableList nodes = gridPane.getChildren();
        for (int i=0; i<nodes.size(); i++){
            if(nodes.get(i).getClass()==Rectangle.class) {
                gridPane.getChildren().remove(nodes.get(i));
                i--;
            }
        }
    }
    public void refillPane(int[][] field){
        clearPane(gridPane);
        int padding=0;
        if(field.length<gridPane.getRowCount()) padding=1;
        for (int row=0; row<field.length; row++){
            for (int column=0; column<field[row].length; column++){
                if(field[row][column]>0) createBlock(column+padding, row+padding, field[row][column]);
            }
        }
    }
}
