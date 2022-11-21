package chess;
import java.util.*;
import chess.*;

public class Tree{

    //attribut
    ArrayList<Tree> children = new ArrayList<Tree>();
    Board chess_node;
    Tree node_f;
    ArrayList<String> list_move = new ArrayList<String>();
    //constructeur
    public Tree(Integer profondeur,Board chess,Boolean i_color_adv,ArrayList<String> m) {
        list_move = new ArrayList<String>(m);
        chess_node = new Board(chess);
        //nb_node++;
        // color = true = false
        String color_m = "WHITE";
        String color_f = "BLACK";
        if(!i_color_adv){
            color_m = "BLACK"; // couleur de la racine
            color_f = "WHITE"; ///couleur des noeuds fils
        }
        if(profondeur!=0){
            //création des noeuds fils
            ArrayList<Tuple> all_move = chess.getMoves(color_f,chess_node,false);
            for (Tuple move : all_move){
                Integer pos_1 = move.getFirst(); //pos initial
                Integer pos_2 = move.getSecond(); //pos final
                Board chess_copy = new Board(chess); //copi board
                chess_copy.move_piece_without_check(chess_copy.getCoord(pos_1).toString()+chess_copy.getCoord(pos_2).toString()); //met à jour le board
                String pos_arena = chess_copy.getCoord(pos_1).toString()+chess_copy.getCoord(pos_2).toString();
                list_move.add(pos_arena);         
                node_f = new Tree(profondeur-1,chess_copy,!i_color_adv,list_move); //noeud fils
                children.add(node_f); //ajout dans la liste de noeud fils
            }
        }
    }

    public Tree(Integer profondeur,Board chess,Boolean i_color_adv) {
        this(profondeur,chess,i_color_adv, new ArrayList<String>());
    }

    public Board getRacine() { return chess_node;}
    public void moveToString(){System.out.println(list_move.toString());}

    public void afficher() {
        System.out.println(this.getRacine().toString());
        System.out.println("Score de la board à t : ");
        this.moveToString();
        System.out.println( this.getRacine().heuristic().toString());
        for(int i = 0 ; i < children.size(); i++){
            System.out.println("\n\n\n");
            children.get(i).afficher();
        }

    }

    

    public static void main(String[] args) {
        Board myChess = new Board("WHITE");
        //donner la couleur de l'adverssaire
        Tree arbre = new Tree(2,myChess,false);
        arbre.afficher();

    }

}