package Shapes;
import GameLogic.*;
public class RightSkew extends Shape {
    public RightSkew(){
        super(3, 5);
        setColor(5);
        setBlock(0,2);
        setBlock(1,2);
        setBlock(1,1);
        setBlock(2,1);
    }
    @Override
    public void rotate(){
        super.rotate();
        if(super.getBlock(2,0)>0){
            super.getShape().moveRowsDown(getSize()-1);
        }
    }
}
