import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import chess.*;
import java.util.*;
import java.lang.*;
import java.util.Collections;

import api.API;

public class App {

    //attribute
    public static ArrayList<String> moves_preced = new ArrayList<String>();

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
             writeFile("Color of our king: "+color+" King is checked: "+ init.isChecked());

            //Creation de l'arbre
            writeFile("Create Tree");
            long begin_time = System.currentTimeMillis();
            Tree arbre = new Tree(3, init, api_stdin.ImWhite(), new Tuple(0, 0, ""));
            long end_time = System.currentTimeMillis() - begin_time;
            writeFile("Time tree: " + end_time + "ms.");

            //devine le bon chemin
            writeFile("End tree, find max mov");
            begin_time = System.currentTimeMillis();
            String moves = arbre.minimax(3, true).getThird(); //first minimax
            String[] move_list = moves.split(" ");  
            // 3 fold repetition
            moves_preced.add(move_list[move_list.length-2]);
            //writeFile("fréquence : " + Collections.frequency(moves_preced, moves));
            if ( Collections.frequency(moves_preced, move_list[move_list.length-2])==2){
                // moves_preced.clear();
                arbre.destroyNode(move_list[move_list.length-2]);
                //writeFile("moves précedent : " + move_list[move_list.length-2]);
                moves = arbre.minimax(3, true).getThird(); //second minimax
                move_list = moves.split(" ");       
                moves_preced.add(move_list[move_list.length-2]);     
                
            }
            end_time = System.currentTimeMillis() - begin_time;
            //writeFile("Time find move: "+ end_time + "ms.");
            //writeFile("Moves: " + moves);
            api_stdin.moveSend(move_list[move_list.length-2]);
            writeFile(moves_preced.toString());
            
        }

    }
}
