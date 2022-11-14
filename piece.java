import java.util.*;


public class Piece<T> {

    //Attributes

    String VIDE = ".";
    String name = "";
    String color = "";
    Integer value = 0;

    // Name of the pieces
    ArrayList<String> namePiece = new ArrayList<String>((Arrays.asList(VIDE,"KING","QEEN","TOWER","KNIGHT","BISHOP","PAWN")));
    
    // Scrore of each piece
    ArrayList<Integer> valPiece = new ArrayList<Integer>((Arrays.asList(0,0,9,3,3,1)));

    // For the piece moves, using "mail box" method
    // From Robert Hyatt
    // It can be show that move of piece is possible
    ArrayList<Integer> tab120 = new ArrayList<Integer>((Arrays.asList(
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1,  0,  1,  2,  3,  4,  5,  6,  7, -1,
        -1,  8,  9, 10, 11, 12, 13, 14, 15, -1,
        -1, 16, 17, 18, 19, 20, 21, 22, 23, -1,
        -1, 24, 25, 26, 27, 28, 29, 30, 31, -1,
        -1, 32, 33, 34, 35, 36, 37, 38, 39, -1,
        -1, 40, 41, 42, 43, 44, 45, 46, 47, -1,
        -1, 48, 49, 50, 51, 52, 53, 54, 55, -1,
        -1, 56, 57, 58, 59, 60, 61, 62, 63, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    )));

    ArrayList<Integer> tab = new ArrayList<Integer>((Arrays.asList(
        21, 22, 23, 24, 25, 26, 27, 28,
        31, 32, 33, 34, 35, 36, 37, 38,
        41, 42, 43, 44, 45, 46, 47, 48,
        51, 52, 53, 54, 55, 56, 57, 58,
        61, 62, 63, 64, 65, 66, 67, 68,
        71, 72, 73, 74, 75, 76, 77, 78,
        81, 82, 83, 84, 85, 86, 87, 88,
        91, 92, 93, 94, 95, 96, 97, 98
    )));

    //Vector's moving of piece since 'tab64'
    ArrayList<Integer> move_tower = new ArrayList<Integer>((Arrays.asList(-10,10,-1,1)));
    ArrayList<Integer> move_bishop = new ArrayList<Integer>((Arrays.asList(-11,9,11,9)));
    ArrayList<Integer> move_knight = new ArrayList<Integer>((Arrays.asList(-12,-21,-19,-8,12,21,19,8)));


    //Constructor
    Piece(String i_name,String i_color){
        name = i_name;
        color = i_color;
        value = valPiece.get(namePiece.indexOf(name));
    }

    //function return Boolean type
    boolean isEmpty(){
        return(name==VIDE);
    }

    //////////////////// MOVE function //////////////////////
    ArrayList<T> pos2_knight(Integer pos1,String cAd, Boards chess_board){
        //returns the list of move's possible of knight
        ArrayList<T> list = new ArrayList<T>();

        //for(int i=0;i<move_knight.size();i++>){
            //Integer i = tab120.get(tab64.get(pos1)+i);
        //}
        return list;
    }






}