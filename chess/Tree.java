package chess;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import chess.*;

public class Tree{

    private static String MyColor;
    private static boolean first = true;
    private static Tree racine;
    //attribut
    ArrayList<Tree> children = new ArrayList<Tree>();
    Board chess_node;
    Tree node_f;

    String color_m, color_f;

    Tuple moveObj;

    //constructeur
    public Tree(Integer profondeur,Board chess,Boolean i_color_adv, Tuple moveGiv) {
        this.moveObj = moveGiv;
        chess_node = new Board(chess);
        
        //nb_node++;
        // color = true = false
        color_m = "WHITE";
        color_f = "BLACK";
        if(!i_color_adv){
            color_m = "BLACK"; // couleur de la racine
            color_f = "WHITE"; ///couleur des noeuds fils
        }
        if(first) {
            first = false;
            MyColor = color_m;
            racine = this;
        }
        if(profondeur!=0){
            //création des noeuds fils
            ArrayList<Tuple> all_move = chess.getMoves(color_m,chess_node,false);
            for (Tuple move : all_move){
                Integer pos_1 = move.getFirst(); //pos initial
                Integer pos_2 = move.getSecond(); //pos final
                Board chess_copy = new Board(chess); //copi board
                writeFile("Moves: "+chess_copy.getCoord(pos_1).toString()+chess_copy.getCoord(pos_2).toString());
                chess_copy.move_piece_without_check(chess_copy.getCoord(pos_1).toString()+chess_copy.getCoord(pos_2).toString()); //met à jour le board         
                node_f = new Tree(profondeur-1,chess_copy,!i_color_adv, move); //noeud fils
                children.add(node_f); //ajout dans la liste de noeud fils
            }
        }
    }
    
    public Tuple minimax(int profondeur, boolean evalMax){
        ArrayList<Integer> liste = new ArrayList<>();
        ArrayList<String> liste_move = new ArrayList<>();
        Tuple result = new Tuple(-1, -1, "");
        
        if(profondeur == 0){
            // writeFile("This node is the racine ?: " + !(racine!=this) + " Is our king is checked ?" + chess_node.isChecked(MyColor, MyColor, chess_node));
            // writeFile("Color of the Chess ?: "+chess_node.getColor()+"Heuristic?: "+chess_node.heuristic());
            // writeFile("Move: " + chess_node.getCoord(this.moveObj.getFirst()).toString()+chess_node.getCoord(this.moveObj.getSecond()).toString());
            result.setFirst(chess_node.heuristic());
            result.setThird(chess_node.getCoord(this.moveObj.getFirst()).toString()+chess_node.getCoord(this.moveObj.getSecond()).toString());
            return result;
        } else{
            for(Tree child: children){
                Tuple tmp = child.minimax(profondeur-1, !evalMax);
                liste.add(tmp.getFirst());
                liste_move.add(tmp.getThird());
            }
            if(evalMax){
                int i = Collections.max(liste);
                String move_one = liste_move.get(liste.indexOf(i)) + " " + chess_node.getCoord(this.moveObj.getFirst()).toString()+chess_node.getCoord(this.moveObj.getSecond()).toString();
                return new Tuple(i, -1, move_one);
            } else {
                int i = Collections.min(liste);
                String move_one = liste_move.get(liste.indexOf(i)) + " " + chess_node.getCoord(this.moveObj.getFirst()).toString()+chess_node.getCoord(this.moveObj.getSecond()).toString();
                return new Tuple(i, -1, move_one);
            }
        }
    }

    private void writeFile(String line) {
        try {
            FileWriter myWriter = new FileWriter("debug/debug_tree.txt", true);
            myWriter.write(line+"\n");
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }

    private int getNodeLayer() {
        return children.size();
    }

    public int getNodes() {
        int result = 0;
        for(Tree child: children) {
            result += child.getNodes();
        }
        result += getNodeLayer();
        return result;
    }


    public Board getRacine() { return chess_node;}

    public void afficher() {
        System.out.println(this.getRacine().toString());
        System.out.println("Score de la board à t : ");
        System.out.println( this.getRacine().heuristic().toString());
        for(int i = 0 ; i < children.size(); i++){
            System.out.println("\n\n\n");
            children.get(i).afficher();
        }

    }

    

    public static void main(String[] args) {
        Board myChess = new Board("WHITE");
        //donner la couleur de l'adverssaire
        Tree arbre = new Tree(2,myChess,false, new Tuple(0,0,""));
        //arbre.afficher();
        System.out.println("Noeuds: "+arbre.getNodes());

    }

}