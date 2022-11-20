import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        File[] list_debug = new File("debug").listFiles();
        for(File debug: list_debug) {
            debug.delete();
        }

        while(true) {
            Board init = api_stdin.refresh();
            writeFile(init.toString());

            String color = api_stdin.ImWhite()?"WHITE": "BLACK";
            String color_ennemy = api_stdin.ImWhite()?"BLACK":"WHITE";
            writeFile("Color of our king: "+color+" King is checked: "+init.isChecked(color, color, init));

            //Creation de l'arbre
            writeFile("Create Tree");
            Tree arbre = new Tree(2, init, api_stdin.ImWhite(), new Tuple(0, 0, ""));

            //devine le bon chemin
            writeFile("End tree, find max mov");
            String moves = arbre.minimax(2, true).getThird();
            writeFile("Moves: " + moves);
            String[] move_list = moves.split(" ");            
            api_stdin.moveSend(move_list[move_list.length-2]);


        }

    }
}
