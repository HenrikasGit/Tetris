package GameLogic.Rotation;

import GameLogic.Field;
import GameLogic.Shape;

public class SymmetricalRotation implements RotationBehaviour{
    public Field rotate(Shape figure){
        Field shape = figure.getShape();
        int size = shape.getRow(0).length;
        Field newShape = new Field(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newShape.setItem(j, i, shape.getItem(i, (size-1)-j));
            }
        }
        return newShape;
    }
}
