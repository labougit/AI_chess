package chess;
import java.util.*;
import chess.Tuple;

import chess.Board;

public class Piece {

    //Attributes

    String VIDE = "";
    String name = "";
    String color = "";
    Integer value = 0;

    // Name of the pieces
    ArrayList<String> namePiece = new ArrayList<String>((Arrays.asList(VIDE,"KING","QUEEN","TOWER","KNIGHT","BISHOP","PAWN")));
    
    // Scrore of each piece
    ArrayList<Integer> valPiece = new ArrayList<Integer>((Arrays.asList(0,9999999,900,500,320,330,100)));

    public Integer getVal(String i_color) {
        if(this.name.equals("KING") && !this.color.equals(i_color))
        {
            value = value/2;
        }
        return (value);
    }
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

    ArrayList<Integer> tab64 = new ArrayList<Integer>((Arrays.asList(
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
    ArrayList<Integer> move_bishop = new ArrayList<Integer>((Arrays.asList(-11,-9,11,9)));
    ArrayList<Integer> move_knight = new ArrayList<Integer>((Arrays.asList(-12,-21,-19,-8,12,21,19,8)));


    //Constructor
    public Piece(String i_name,String i_color){
        name = i_name;
        color = i_color;
        value = valPiece.get(namePiece.indexOf(name));
    }

    // Empty piece
    public Piece(){
        name = "";
        color = "";
        value = valPiece.get(namePiece.indexOf(name));
    }

    //function return Boolean type
    public boolean isEmpty(){
        return(name==VIDE);
    }
    

    //function return color type
    public String color(){
        return color;
    }

    //function return name
    public String name(){
        return name;
    }

    //////////////////// MOVE function //////////////////////
    public ArrayList<Tuple> pos2_knight(Integer pos1,String cAd, Board chess_board){
        //returns the list of move's possible of knight
        ArrayList<Tuple> list = new ArrayList<Tuple>();
        Integer n;
        for(int i=0;i<move_knight.size();i++){
            n = tab120.get(tab64.get(pos1)+ move_knight.get(i));
            if(n != -1)
            {
                ArrayList<Piece> chess_t = chess_board.getValues();
                if(chess_t.get(n).isEmpty() || cAd.equals(chess_t.get(n).color())){
                    list.add(new Tuple(pos1, n, cAd));
                }
            }
        }
        return list;
    }

    public ArrayList<Tuple> pos2_tower(Integer pos1,String cAd, Board chess_board){
        //returns the list of move's possible of tower
        ArrayList<Tuple> list = new ArrayList<Tuple>();

        for(int i=0;i<move_tower.size();i++){
            Integer j=1;
            while(true){
                Integer n = tab120.get(tab64.get(pos1)+(move_tower.get(i)*j));
                ArrayList<Piece> chess_t = chess_board.getValues();
                if(n != -1)
                {
                    if(chess_t.get(n).isEmpty() || cAd.equals(chess_t.get(n).color())){
                        list.add(new Tuple(pos1, n, ""));
                    }
                }
                else{
                    break;
                }
                if(chess_t.get(n).isEmpty() == false){
                    break;
                }
                j = j+1;
            }
        }

        return list;
    }

    public ArrayList<Tuple> pos2_bishop(Integer pos1,String cAd, Board chess_board){
        //returns the list of move's possible of bishop
        ArrayList<Tuple> list = new ArrayList<Tuple>();

        for(int i=0;i<move_bishop.size();i++){
            Integer j=1;
            while(true){
                Integer n = tab120.get(tab64.get(pos1)+(move_bishop.get(i)*j));
                ArrayList<Piece> chess_t = chess_board.getValues();
                if(n != -1)
                {
                    if(chess_t.get(n).isEmpty() || cAd.equals(chess_t.get(n).color())){
                        list.add(new Tuple(pos1, n, ""));
                    }
                }
                else{
                    break;
                }
                if(chess_t.get(n).isEmpty() == false){
                    break;
               }
                j = j+1;
            }
        }

        return list;
    }

    public ArrayList<Tuple> pos2_pawn(Integer pos1,String cAd, Board chess_board){
        //returns the list of move's possible of pawn
        ArrayList<Tuple> list = new ArrayList<Tuple>();
        ArrayList<Piece> chess_t = chess_board.getValues();
        if("WHITE".equals(color)){
            Integer n = tab120.get(tab64.get(pos1)-10);
            if(n!=-1){
                if(chess_t.get(n).isEmpty()){
                    if(n<8){
                        list.add(new Tuple(pos1,n,"q"));
                        list.add(new Tuple(pos1,n,"r"));
                        list.add(new Tuple(pos1,n,"n"));
                        list.add(new Tuple(pos1,n,"b"));
                    }
                    else{
                        list.add(new Tuple(pos1,n,""));

                    }
                }
            }

            n = tab120.get(tab64.get(pos1)-20);
            //Check if the pawn is on the init pos, the pawn can't reverse
            if(n!=-1 && pos1/8 == 6) {
                if(chess_t.get(pos1-8).isEmpty() & chess_t.get(pos1-16).isEmpty()){
                    list.add(new Tuple(pos1, n, ""));
                }
            }


            n = tab120.get(tab64.get(pos1)-11);
            if(n!=-1){
                if("BLACK".equals(chess_t.get(n).color()) || chess_board.getEnPassant()==n){
                    if(n<8){
                        list.add(new Tuple(pos1,n,"q"));
                        list.add(new Tuple(pos1,n,"r"));
                        list.add(new Tuple(pos1,n,"n"));
                        list.add(new Tuple(pos1,n,"b"));
                    }
                    else{
                        list.add(new Tuple(pos1,n,""));

                    }
                }
            }

            n = tab120.get(tab64.get(pos1)-9);
            if(n!=-1){
                if("BLACK".equals(chess_t.get(n).color()) || chess_board.getEnPassant()==n){
                    if(n<8){
                        list.add(new Tuple(pos1,n,"q"));
                        list.add(new Tuple(pos1,n,"r"));
                        list.add(new Tuple(pos1,n,"n"));
                        list.add(new Tuple(pos1,n,"b"));
                    }
                    else{
                        list.add(new Tuple(pos1,n,""));

                    }
                }
            }


        }

        else{

            Integer n = tab120.get(tab64.get(pos1)+10);
            if(n!=-1){
                if(chess_t.get(n).isEmpty()){
                    if(n>55){
                        list.add(new Tuple(pos1,n,"q"));
                        list.add(new Tuple(pos1,n,"r"));
                        list.add(new Tuple(pos1,n,"n"));
                        list.add(new Tuple(pos1,n,"b"));
                    }
                    else{
                        list.add(new Tuple(pos1,n,""));

                    }
                }
            }

            n = tab120.get(tab64.get(pos1)+20);
            if(n!=-1 && pos1/8==1){
                if(chess_t.get(pos1+8).isEmpty() & chess_t.get(pos1+16).isEmpty()){
                    list.add(new Tuple(pos1, n, ""));
                }
                
            }


            n = tab120.get(tab64.get(pos1)+9);
            if(n!=-1){
                if("WHITE".equals(chess_t.get(n).color()) || chess_board.getEnPassant()==n){
                    if(n>55){
                        list.add(new Tuple(pos1,n,"q"));
                        list.add(new Tuple(pos1,n,"r"));
                        list.add(new Tuple(pos1,n,"n"));
                        list.add(new Tuple(pos1,n,"b"));
                    }
                    else{
                        list.add(new Tuple(pos1,n,""));

                    }
                }
            }

            n = tab120.get(tab64.get(pos1)+11);
            if(n!=-1){
                if("WHITE".equals(chess_t.get(n).color()) || chess_board.getEnPassant()==n){
                    if(n>55){
                        list.add(new Tuple(pos1,n,"q"));
                        list.add(new Tuple(pos1,n,"r"));
                        list.add(new Tuple(pos1,n,"n"));
                        list.add(new Tuple(pos1,n,"b"));
                    }
                    else{
                        list.add(new Tuple(pos1,n,""));

                    }
                }
            }

        }
        return list;
    }

    public ArrayList<Tuple> pos2_king_1(Integer pos1,String cAd, Board chess_board, Boolean Attack){
        //returns the list of move's possible of knight
        ArrayList<Tuple> list = new ArrayList<Tuple>();
        ArrayList<Piece> chess_t = chess_board.getValues();
        for(int i=0;i<move_tower.size();i++){
            Integer n = tab120.get(tab64.get(pos1)+move_tower.get(i));
            if(n!=-1){
                if(chess_t.get(n).isEmpty() || cAd.equals(chess_t.get(n).color())){
                    list.add(new Tuple(pos1,n,""));
                }
            }
        }
        for(int i=0; i<move_bishop.size(); i++){
            Integer n = tab120.get(tab64.get(pos1)+move_bishop.get(i));
            if(n!=-1){
                if(chess_t.get(n).isEmpty() || cAd.equals(chess_t.get(n).color())){
                    list.add(new Tuple(pos1,n,""));
                }
            }
        }

        String c = chess_board.oppositeColor(cAd);

        if(c.equals("WHITE"))
        {
            if(chess_board.getWhiteCanCastling63()){
                 if(chess_t.get(63).name=="TOWER" &&
                 chess_t.get(63).color()=="WHITE" &&
                 chess_t.get(61).isEmpty()&&
                 chess_t.get(62).isEmpty()
                 && !Attack
                 ){
                     list.add(new Tuple(pos1, 62, ""));

                 }
             }
             if(chess_board.getWhiteCanCastling56()){
                 if(chess_t.get(56).name=="TOWER" &&
                 chess_t.get(56).color()=="WHITE" &&
                 chess_t.get(57).isEmpty() &&
                 chess_t.get(58).isEmpty() &&
                 chess_t.get(59).isEmpty()&& !Attack
                 ){
                     list.add(new Tuple(pos1, 58, ""));

                 }
             }

         }

         else
         {
             if(chess_board.getBlackCanCastling7()){
                 if(chess_t.get(7).name=="TOWER" &&
                 chess_t.get(7).color()=="BLACK" &&
                 chess_t.get(5).isEmpty() &&
                 chess_t.get(6).isEmpty()&& !Attack ){
                     list.add(new Tuple(pos1, 6, ""));

                 }
             }
             if(chess_board.getBlackCanCastling0()){
                 if(chess_t.get(0).name=="TOWER" &&
                 chess_t.get(0).color()=="BLACK" &&
                 chess_t.get(1).isEmpty() &&
                 chess_t.get(2).isEmpty() &&
                 chess_t.get(3).isEmpty()&& !Attack ){
                     list.add(new Tuple(pos1, 2, ""));

                 }
             }

         }
        return list;
    }

    private boolean ourKing(int n, ArrayList<Piece> chess_t) {
        int position_around_King[] = {1, -1, 9, -9, -11, 11, 10, -10};
        boolean verite = true;
        for(int position: position_around_King) {
            int position_x = tab120.get(tab64.get(n)+position);
            if(position_x != -1) {
                verite = verite && !chess_t.get(position_x).name.equals("KING");
            }
            
        }
        // boolean verite = !chess_t.get(n+1).name.equals("KING") && !chess_t.get(n-1).name.equals("KING") && !chess_t.get(n+11).name.equals("KING")
        //         && !chess_t.get(n-11).name.equals("KING") && !chess_t.get(n+10).name.equals("KING") && !chess_t.get(n-10).name.equals("KING") 
        //         && !chess_t.get(n+9).name.equals("KING") && !chess_t.get(n-9).name.equals("KING");

        return verite;
    }

    public ArrayList<Tuple> pos2_king_2(Integer pos1,String cAd, Board chess_board){
        //returns the list of move's possible of knight
        ArrayList<Tuple> list = new ArrayList<Tuple>();
        ArrayList<Piece> chess_t = chess_board.getValues();
        for(int i=0;i<move_tower.size();i++){
            Integer n = tab120.get(tab64.get(pos1)+move_tower.get(i));
            if(n!=-1){
                if((chess_t.get(n).isEmpty() && ourKing(n, chess_t))|| cAd.equals(chess_t.get(n).color())){
                    list.add(new Tuple(pos1,n,""));
                }
            }
        }
        for(int i=0; i<move_bishop.size(); i++){
            Integer n = tab120.get(tab64.get(pos1)+move_bishop.get(i));
            if(n!=-1){
                if(chess_t.get(n).isEmpty() || cAd.equals(chess_t.get(n).color())){
                    list.add(new Tuple(pos1,n,""));
                }
            }
        }

        String c = chess_board.oppositeColor(cAd);

        if(c.equals("WHITE"))
        {
            if(chess_board.getWhiteCanCastling63()){
                 if(chess_t.get(63).name=="TOWER" &&
                 chess_t.get(63).color()=="WHITE" &&
                 chess_t.get(61).isEmpty()&&
                 chess_t.get(62).isEmpty()
                 
                 ){
                     list.add(new Tuple(pos1, 62, ""));

                 }
             }
             if(chess_board.getWhiteCanCastling56()){
                 if(chess_t.get(56).name=="TOWER" &&
                 chess_t.get(56).color()=="WHITE" &&
                 chess_t.get(57).isEmpty() &&
                 chess_t.get(58).isEmpty() &&
                 chess_t.get(59).isEmpty()
                 ){
                     list.add(new Tuple(pos1, 58, ""));

                 }
             }

         }

         else
         {
             if(chess_board.getBlackCanCastling7()){
                 if(chess_t.get(7).name=="TOWER" &&
                 chess_t.get(7).color()=="BLACK" &&
                 chess_t.get(5).isEmpty() &&
                 chess_t.get(6).isEmpty()){
                     list.add(new Tuple(pos1, 6, ""));

                 }
             }
             if(chess_board.getBlackCanCastling0()){
                 if(chess_t.get(0).name=="TOWER" &&
                 chess_t.get(0).color()=="BLACK" &&
                 chess_t.get(1).isEmpty() &&
                 chess_t.get(2).isEmpty() &&
                 chess_t.get(3).isEmpty() ){
                     list.add(new Tuple(pos1, 2, ""));

                 }
             }

         }
        return list;
    }


}
