package chess;
import java.util.*;
import chess.*;

public class Tree{

    //attribut
    ArrayList<Tree> children = new ArrayList<Tree>();
    Board chess_node;
    Tree node_f;

    public Tree(Integer profondeur,Board chess,Boolean i_color) {
        chess_node = new Board(chess);
        //nb_node++;
        // color = true = false
        String color_m = "WHITE";
        String color_f = "BLACK";
        if(!i_color){
            color_m = "BLACK"; // couleur de la racine
            color_f = "WHITE"; ///couleur des noeuds fils
        }
        if(profondeur!=0){
        
            ArrayList<Tuple> all_move = chess.getMoves(color_f,chess_node,false);
            for (Tuple move : all_move){
                Integer pos_1 = move.getFirst(); //pos initial
                Integer pos_2 = move.getSecond(); //pos initial
                Board chess_copy = new Board(chess); //copi board
                chess_copy.move_piece_without_check(chess_copy.getCoord(pos_1).toString()+chess_copy.getCoord(pos_2).toString()); //met à jour le board         
                node_f = new Tree(profondeur-1,chess_copy,!i_color); //noeud fils
                children.add(node_f); //ajout dans la liste de noeud fils
            }
        }
    }

    public Board getRacine() { return chess_node;}

    public void afficher() {
        System.out.println(this.getRacine().toString());
        for(int i = 0 ; i < children.size(); i++){
            System.out.println("\n\n\n");
            children.get(i).afficher();
        }

    }

    

    public static void main(String[] args) {
        Board myChess = new Board();
        //donner la couleur de l'adverssaire
        Tree arbre = new Tree(4,myChess,false);
        arbre.afficher();

    }

}