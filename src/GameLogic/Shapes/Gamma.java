package GameLogic.Shapes;
import GameLogic.*;
import GameLogic.Rotation.SymmetricalRotation;

public class Gamma extends Shape {
    public Gamma(){
        super(3, 3);
        setBlock(0,0);
        setBlock(0,1);
        setBlock(1,1);
        setBlock(2,1);
        super.setRotationBehaviour(new SymmetricalRotation());
    }
}
