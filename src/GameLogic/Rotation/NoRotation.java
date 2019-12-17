package GameLogic.Rotation;

import GameLogic.Field;
import GameLogic.Shape;

public class NoRotation implements RotationBehaviour{
    public Field rotate(Shape figure){return figure.getShape();};
}
