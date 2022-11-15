import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import api.API;

public class App {

    private static void writeFile(String line) {
        try {
            FileWriter myWriter = new FileWriter("debug_app.txt", true);
            myWriter.write(line+"\n");
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }
    
    public static void main(String[] args) {
        ArrayList<Integer> echiquier = new ArrayList<>();
        API api_stdin = new API(echiquier);

        while(true) {
            api_stdin.refresh();
            //String next_move = readFile("move.txt", api_stdin.ImWhite());
            String next_move = api_stdin.ImWhite()? "e2e4": "c7c5";
            writeFile(next_move);
            api_stdin.moveSend(next_move);
        }

    }
}
