package GameLogic.Shapes;
import GameLogic.*;
import GameLogic.Rotation.AsymmetricalRotation;

public class LeftSkew extends Shape{
    public LeftSkew(){
        super(3, 6);
        setBlock(0,0);
        setBlock(1,0);
        setBlock(1,1);
        setBlock(2,1);
        super.setRotationBehaviour(new AsymmetricalRotation());
    }
}
