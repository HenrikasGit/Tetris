package GameLogic.Rotation;

import GameLogic.Field;
import GameLogic.Shape;

public class AsymmetricalRotation implements RotationBehaviour {
    public Field rotate(Shape figure){
        SymmetricalRotation symmetricalRotation = new SymmetricalRotation();
        int size = figure.getSize();
        Field newShape = symmetricalRotation.rotate(figure);
        for (int row=0; row<size; row++){
            if(newShape.getItem(0, row)>0) return newShape;
        }
        newShape.moveColumnsLeft(0);
        return newShape;
    }
}
