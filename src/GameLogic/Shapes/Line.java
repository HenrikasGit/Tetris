package GameLogic.Shapes;
import GameLogic.*;
import GameLogic.Rotation.AsymmetricalRotation;

public class Line extends Shape {
    public Line(){
        super(4, 2);
        setBlock(0,1);
        setBlock(1,1);
        setBlock(2,1);
        setBlock(3,1);
        super.setRotationBehaviour(new AsymmetricalRotation());
    }
}
