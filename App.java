import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import chess.*;

import api.API;

public class App {

    private static void writeFile(String line) {
        try {
            FileWriter myWriter = new FileWriter("debug/debug_app.txt", true);
            myWriter.write(line+"\n");
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }
    
    public static void main(String[] args) {
        API api_stdin = new API();

        while(true) {
            Board init = api_stdin.refresh();
            writeFile(init.toString());
            //api_stdin.moveSend(next_move);
        }

    }
}
