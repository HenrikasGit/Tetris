package GameLogic;

import GameLogic.Rotation.RotationBehaviour;

public class Shape implements Cloneable  {
    protected Field shape;
    protected int size;
    private Position position = new Position();
    private int color;
    private RotationBehaviour rotationBehaviour;
    private String id;
    public Shape(int size, int color) {
        this.size = size;
        shape = new Field(size, size);
        this.color = color;
        this.id = String.valueOf(color);
        position.setPosition(3, 4);
    }

    public String getId() {
        return id;
    }

    public void setRotationBehaviour(RotationBehaviour rotationBehaviour) {
        this.rotationBehaviour = rotationBehaviour;
    }
    public Shape clone(){
        Shape clone = null;
        try{
            clone = (Shape)super.clone();
        } catch (Exception e){
            e.printStackTrace();
        }
        Position position = new Position();
        position.setPosition(this.position.getPositionX(), this.position.getPositionY());
        clone.setPosition(position);
        return clone;
    }
    public int getSize(){
        return size;
    }
    public Field getShape(){
        return shape;
    }
    public int[][] getShapeArray(){
        return shape.getRangeOfRows(0, size);
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
    public void performRotation(){
        shape = rotationBehaviour.rotate(this);
    }
    public void go(String direction, int amount){
        position.moveRelatively(direction, amount);
    }
}