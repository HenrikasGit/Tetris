package GameLogic.Shapes;
import GameLogic.*;
import GameLogic.Rotation.SymmetricalRotation;

public class Alpha extends Shape{
    public Alpha(){
        super(3, 4);
        setBlock(2,0);
        setBlock(0,1);
        setBlock(1,1);
        setBlock(2,1);
        super.setRotationBehaviour(new SymmetricalRotation());
    }
}
