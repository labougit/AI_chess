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
                Board chess_copy = new Board(chess);
                System.out.println(chess_copy.getCoord(pos_1).toString()+chess_copy.getCoord(pos_2).toString());
                chess_copy.move_piece_without_check(chess_copy.getCoord(pos_1).toString()+chess_copy.getCoord(pos_2).toString());                Tree node_f = new Tree(profondeur-1,chess_copy,!i_color);
                children.add(node_f);
            }
        }
    }

    public Board getRacine() { return chess_node;}
    public void afficher() {
        System.out.println(this.getRacine().toString());
        for (Tree node : this.children) {
            node.afficher();
        }
    }

    

    public static void main(String[] args) {
        Board myChess = new Board();
        String color = "WHITE";
        Tree arbre = new Tree(2,myChess,true);
        arbre.afficher();

    }

}