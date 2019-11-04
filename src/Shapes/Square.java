package Shapes;
import GameLogic.*;
public class Square extends Shape {
    public Square() {
        super(3, 1);
        setColor(1);
        setBlock(1,1);
        setBlock(1,2);
        setBlock(2,1);
        setBlock(2,2);
    }
    @Override
    public void rotate(){}
}