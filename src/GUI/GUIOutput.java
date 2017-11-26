package GUI;

import Cmd.Others.Output;
import javafx.scene.control.*;

/**
 * The GUIOuput for program
 */
public class GUIOutput extends Output {
    private TextArea textArea;

    /**
     * @param textArea textArea for output
     */
    GUIOutput(TextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * @param str print string to textArea
     */
    public void Print(String str) {
        textArea.appendText(str + "\n");
    }
}
