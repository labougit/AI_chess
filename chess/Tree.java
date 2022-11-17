package chess;
import java.util.*;
import chess.*;

public class Tree{

    //attribut
    ArrayList<Tree> children = new ArrayList<Tree>();
    Board chess_node;

    public Tree(Integer profondeur,Board chess,Boolean i_color) {
        // color = true = false
        String color_m = "WHITE";
        String color_f = "BLACK";
        if(!i_color){
            color_m = "BLACK"; // couleur de la racine
            color_f = "WHITE"; ///couleur des noeuds fils
        }
        if(profondeur!=0){
        
            chess_node = chess;
            ArrayList<Tuple> all_move = chess.getMoves(color_f,chess,false);
            for (Tuple move : all_move){
                Integer pos_1 = move.getFirst();
                Integer pos_2 = move.getSecond();
                //chess_board_fil = setBoard(chess.getCoord(pos1),chess.getCoord(pos_2),chess,i_color);
                Tree node_f = new Tree(profondeur-1,chess_board_fil,!i_color);
                children.add(node_f);
            }
        }
    }

}