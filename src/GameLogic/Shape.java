package GameLogic;

import Shapes.*;

public class Shape implements Cloneable  {
    private Field shape;
    private int size;
    private Position position = new Position();
    private int color;
    public Shape(int size, int color) {
        this.size = size;
        shape = new Field(size, size);
        this.color = color;
    }
    public int getSize(){
        return size;
    }
    public Shape copy(boolean copyRotation){
        Shape newShape;
        if(this.getClass()== Alpha.class) newShape = new Alpha();
        else if(this.getClass()==Gamma.class) newShape = new Gamma();
        else if(this.getClass()==LeftSkew.class) newShape = new LeftSkew();
        else if(this.getClass()==Line.class) newShape = new Line();
        else if(this.getClass()==Pyramid.class) newShape = new Pyramid();
        else if(this.getClass()==RightSkew.class) newShape = new RightSkew();
        else newShape = new Square();
        newShape.setPosition(getPositionX(), getPositionY());
        if(copyRotation) newShape.setShape(shape);
        return newShape;
    }
    public Field getShape(){
        return shape;
    }
    public void setShape(Field shape){
        this.shape = shape;
    }
    public int[][] getShapeArray(){
        return shape.getRangeOfLines(0, size);
    }
    public void setColor(int color){
        this.color = color;
    }
    public int getPositionX() {
        return position.getPositionX();
    }
    public int getPositionY()  {
        return position.getPositionY();
    }
    public void setPosition(int positionX, int positionY) {
        position.setPosition(positionX, positionY);
    }
    public void setPosition(Position position) {
        this.position=position;
    }
    public Position getPosition(){
        return position;
    }
    public void setBlock(int x, int y){
        shape.setItem(x,y,color);
    }
    public int getBlock(int x, int y){
        return shape.getItem(x,y);
    }
    public void rotate(){
        Field newShape = new Field(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newShape.setItem(j, i, shape.getItem(i, (size-1)-j));
            }
        }
        shape = newShape;
    }
    public void setPositionY(int positionY){
        position.setPositionY(positionY);
    }
    public void changeRelativeVerticalPosition(int amount){
        int positionY = getPositionY();
        positionY+=amount;
        setPositionY(positionY);
    }
}
