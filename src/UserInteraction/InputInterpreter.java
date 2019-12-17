package UserInteraction;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputInterpreter {
    public static String interpretKeyboard(KeyEvent ke){
        if(ke.getCode()== KeyCode.UP) return "Up";
        if(ke.getCode()== KeyCode.LEFT) return "Left";
        if(ke.getCode()== KeyCode.RIGHT) return "Right";
        if(ke.getCode()== KeyCode.DOWN) return "Down";
        if(ke.getCode()== KeyCode.SPACE) return "Space";
        if(ke.getCode()== KeyCode.CONTROL) return "Control";
        return "No Input";
    }
}
