import Controller.DataBase;
import View.View;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String name="bastani";
        String off="";
        double price=2321.3213;
        int i=1;
        long id=1232312;
        System.out.printf("%d. %-30s  %-8.2f $  %-15s Id:%d\n",i,name,price,off,id);

        name="khordaniiiiii";
        price=32.123;
        i=2;
        off="off~"+12+"%";
        id=49043;
        System.out.printf("%d. %-30s  %-8.2f $  %-15s Id:%d\n",i,name,price,off,id);

        name="kiiii";
        price=3232.123;
        i=2;
        off="";
        id=49043;
        System.out.printf("%d. %-30s  %-8.2f $  %-15s Id:%d\n",i,name,price,off,id);
        System.out.println();
        String firstProductName="bastani";
        String firstProductOffDetail="off~"+12+"%";
        String firstExplanation="asjkdaskjdnaskdnkjasd";
        double firstProductPrice=123.3213;
        double firstProductAverageScore=14.6;
        System.out.printf("%-30s %-10.2f $ %-20s Score: %-10.2f Detail:%s\n",firstProductName,firstProductPrice,firstProductOffDetail,firstProductAverageScore,firstExplanation);
        firstProductName="khordaniiiii";
        firstProductOffDetail="";
        firstExplanation="asdklajsdklajsdlkjasldkjaslkdjlaksdj";
        firstProductPrice=123123.32;
        firstProductAverageScore=100;
        System.out.printf("%-30s %-10.2f $ %-20s Score: %-10.2f Detail:%s\n",firstProductName,firstProductPrice,firstProductOffDetail,firstProductAverageScore,firstExplanation);

        DataBase.startProgram();
    }
}
