import Controller.DataBase;
import View.View;

import java.io.IOException;
import java.lang.module.Configuration;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws Exception {
        //View.client.run();
        DataBase.startProgram();
        try {
//            DataBase.startProgram();
        } catch (Exception e) {
            DataBase.endProgram();
        }
    }
}
