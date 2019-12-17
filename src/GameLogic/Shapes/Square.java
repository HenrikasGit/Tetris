package GameLogic.Shapes;
import GameLogic.*;
import GameLogic.Rotation.NoRotation;

public class Square extends Shape {
    public Square() {
        super(2, 1);
        setBlock(0,0);
        setBlock(0,1);
        setBlock(1,0);
        setBlock(1,1);
        super.setRotationBehaviour(new NoRotation());
        Position position = new Position();
        position.setPosition(4,4);
        setPosition(position);
    }
}