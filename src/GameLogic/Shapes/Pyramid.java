package GameLogic.Shapes;
import GameLogic.*;
import GameLogic.Rotation.SymmetricalRotation;

public class Pyramid extends Shape {
    public Pyramid(){
        super(3, 7);
        setBlock(1,0);
        setBlock(0,1);
        setBlock(1,1);
        setBlock(2,1);
        super.setRotationBehaviour(new SymmetricalRotation());
    }
}
