package GUI;

import Others.Input;
import javafx.scene.control.*;

public class GUIInput extends Input{
    int inp;
    GUIInput() {inp = 0;}
    public void setInp(int inp) {this.inp = inp;}
    public int getGUIInput(){

        return inp;
    }

}