package Shapes;
import GameLogic.*;
public class Pyramid extends Shape {
    public Pyramid(){
        super(3, 7);
        setColor(7);
        setBlock(1,1);
        setBlock(0,2);
        setBlock(1,2);
        setBlock(2,2);
    }
}
