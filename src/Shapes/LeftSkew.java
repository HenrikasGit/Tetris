package Shapes;
import GameLogic.*;
public class LeftSkew extends Shape {
    public LeftSkew(){
        super(3, 6);
        setColor(6);
        setBlock(0,1);
        setBlock(1,1);
        setBlock(1,2);
        setBlock(2,2);
    }
    @Override
    public void rotate(){
        super.rotate();
        if(super.getBlock(0,0)>0){
            super.getShape().moveRowsDown(getSize()-1);
        }
    }
}
