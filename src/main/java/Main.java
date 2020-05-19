import Controller.DataBase;
import View.View;

import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws Exception {
        DataBase.startProgram();
        try {
            //DataBase.startProgram();
        } catch (Exception e) {
            DataBase.endProgram();
        }
    }

}
