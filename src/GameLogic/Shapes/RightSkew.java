package GameLogic.Shapes;
import GameLogic.*;
import GameLogic.Rotation.AsymmetricalRotation;

public class RightSkew extends Shape {
    public RightSkew(){
        super(3, 5);
        setBlock(0,1);
        setBlock(1,1);
        setBlock(1,0);
        setBlock(2,0);
        super.setRotationBehaviour(new AsymmetricalRotation());
    }
}
