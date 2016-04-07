package clinic.entity;

import java.util.ArrayList;

public class OutputConsole implements Output {

    @Override
    public void Println(String line) {
        System.out.println(line);
    }

    public void PrintList(String inform,ArrayList listObject) {
        this.Println(inform);
        for (int i = 0; i < listObject.size(); i++) {
            System.out.println("№: " + i + " " + listObject.get(i).toString());
        }
    }

}



