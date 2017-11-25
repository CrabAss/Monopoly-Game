package GUI;

import Cmd.Others.Output;
import javafx.scene.control.*;

public class GUIOutput extends Output{
    private TextArea textArea;

    public GUIOutput(TextArea textArea) {
        this.textArea = textArea;
    }

    public void Print(String str){
        textArea.appendText(str + "\n");
    }
}
