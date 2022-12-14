package chess;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Collections;
// chess package to use new Piece.java methods
import chess.*;
import chess.Tuple;


public class Board{
    // Define the next color to play 
    // Init the 8x8 chess board ArrayList object
    public int nb_coup = 0;
    public String color;
    public static ArrayList<String> coord = new ArrayList<String>(Arrays.asList(
        "a8","b8","c8","d8","e8","f8","g8","h8",
        "a7","b7","c7","d7","e7","f7","g7","h7",
        "a6","b6","c6","d6","e6","f6","g6","h6",
        "a5","b5","c5","d5","e5","f5","g5","h5",
        "a4","b4","c4","d4","e4","f4","g4","h4",
        "a3","b3","c3","d3","e3","f3","g3","h3",
        "a2","b2","c2","d2","e2","f2","g2","h2",
        "a1","b1","c1","d1","e1","f1","g1","h1"
    ));

    //square's score of piece
    public static ArrayList<Float> s_bishop_w = new ArrayList<Float>(Arrays.asList(
        -20f,-10f,-10f,-10f,-10f,-10f,-10f,-20f,
        -10f, 0f, 0f, 0f, 0f, 0f, 0f, -10f,
        -10f, 0f, 5f, 10f, 10f, 5f, 0f, -10f,
        -10f, 5f, 5f, 10f, 10f, 5f, 5f, -10f,
        -10f, 0f, 10f, 10f, 10f, 10f, 0f, -10f,
        -10f, 10f, 10f, 10f, 10f, 10f, 10f, -10f,
        -10f, 5f, 0f, 0f, 0f, 0f, 5f, -10f,
        -20f,-10f,-10f,-10f,-10f,-10f,-10f,-20f));

    public static ArrayList<Float> s_bishop_b = new ArrayList<Float>(Arrays.asList(
        -20f, -10f, -10f, -10f, -10f, -10f, -10f,
         -20f, -10f, 5f, 0f, 0f, 0f, 0f, 5f, -10f, 
         -10f, 10f, 10f, 10f, 10f, 10f, 10f, -10f, 
         -10f, 0f, 10f, 10f, 10f, 10f, 0f, -10f, -10f,
          5f, 5f, 10f, 10f, 5f, 5f, -10f, -10f, 0f, 5f,
           10f, 10f, 5f, 0f, -10f, -10f, 0f, 0f, 0f, 0f,
            0f, 0f, -10f, -20f, -10f, -10f, -10f, -10f, -10f, -10f, -20f
    ));
    public static ArrayList<Float> s_pawn_w = new ArrayList<Float>(Arrays.asList(
        0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
50f, 50f, 50f, 50f, 50f, 50f, 50f, 50f,
10f, 10f, 20f, 30f, 30f, 20f, 10f, 10f,
 5f, 5f, 10f, 25f, 25f, 10f, 5f, 5f,
 0f, 0f, 0f, 20f, 20f, 0f, 0f, 0f,
 5f, -5f, -10f, 0f, 0f, -10f, -5f, 5f,
 5f, 10f, 10f,-20f,-20f, 10f, 10f, 5f,
 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));

        public static ArrayList<Float> s_pawn_b = new ArrayList<Float>(Arrays.asList(
            0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
             5f, 10f, 10f, -20f, -20f, 10f,
              10f, 5f, 5f, -5f, -10f, 0f, 0f, 
              -10f, -5f, 5f, 0f, 0f, 0f, 20f, 20f,
               0f, 0f, 0f, 5f, 5f, 10f, 25f, 25f, 
               10f, 5f, 5f, 10f, 10f, 20f, 30f, 30f, 
               20f, 10f, 10f, 50f, 50f, 50f, 50f, 
               50f, 50f, 50f, 50f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
    
    public static ArrayList<Float> s_tower_w = new ArrayList<Float>(Arrays.asList(
        0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f,
  5f, 10f, 10f, 10f, 10f, 10f, 10f, 5f,
 -5f, 0f, 0f, 0f, 0f, 0f, 0f, -5f,
 -5f, 0f, 0f, 0f, 0f, 0f, 0f, -5f,
 -5f, 0f, 0f, 0f, 0f, 0f, 0f, -5f,
 -5f, 0f, 0f, 0f, 0f, 0f, 0f, -5f,
 -5f, 0f, 0f, 0f, 0f, 0f, 0f, -5f,
  0f, 0f, 0f, 5f, 5f, 0f, 0f, 0f));

           public static ArrayList<Float> s_tower_b = new ArrayList<Float>(Arrays.asList(
            0f, 0f, 0f, 5f, 5f, 0f, 0f, 0f,
             -5f, 0f, 0f, 0f, 0f, 0f, 0f, -5f, -5f,
              0f, 0f, 0f, 0f, 0f, 0f, -5f, -5f, 0f,
               0f, 0f, 0f, 0f, 0f, -5f, -5f, 0f, 0f, 
               0f, 0f, 0f, 0f, -5f, -5f, 0f, 0f, 0f, 
               0f, 0f, 0f, -5f, 5f, 10f, 10f, 10f, 10f, 
               10f, 10f, 5f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));
        
    public static ArrayList<Float> s_knight_w = new ArrayList<Float>(Arrays.asList(
        -50f,-40f,-30f,-30f,-30f,-30f,-40f,-50f,
    -40f,-20f, 0f, 0f, 0f, 0f,-20f,-40f,
    -30f, 0f, 10f, 15f, 15f, 10f, 0f, -30f,
    -30f, 5f, 15f, 20f, 20f, 15f, 5f, -30f,
    -30f, 0f, 15f, 20f, 20f, 15f, 0f, -30f,
    -30f, 5f, 10f, 15f, 15f, 10f, 5f, -30f,
    - 40f,-20f, 0f, 5f, 5f, 0f,-20f,-40f,
    -50f,-40f,-30f,-30f,-30f,-30f,-40f,-50f
        ));
        public static ArrayList<Float> s_knight_b = new ArrayList<Float>(Arrays.asList(
            -50f, -40f, -30f, -30f, -30f, -30f,-40f, -50f,
             -40f, -20f, 0f, 5f, 5f, 0f, -20f, -40f, 
             -30f, 5f, 10f, 15f, 15f, 10f, 5f, -30f, -30f, 0f, 15f, 20f,
             20f, 15f, 0f, -30f, -30f, 5f, 15f, 20f, 
             20f, 15f, 5f, -30f, -30f, 0f, 10f, 15f, 
             15f, 10f, 0f, -30f, -40f, -20f, 0f, 0f, 
             0f, 0f, -20f, -40f, -50f, -40f, -30f, 
             -30f, -30f, -30f, -40f, -50f
            ));

    public static ArrayList<Float> s_king_w = new ArrayList<Float>(Arrays.asList(
        -30f,-40f,-40f,-50f,-50f,-40f,-40f,-30f,
-30f,-40f,-40f,-50f,-50f,-40f,-40f,-30f,
-30f,-40f,-40f,-50f,-50f,-40f,-40f,-30f,
-30f,-40f,-40f,-50f,-50f,-40f,-40f,-30f,
-20f,-30f,-30f,-40f,-40f,-30f,-30f,-20f,
-10f,-20f,-20f,-20f,-20f,-20f,-20f,-10f,
 20f, 20f, 0f, 0f, 0f, 0f, 20f, 20f,
 20f, 30f, 10f, 0f, 0f, 10f, 30f, 20f
        ));

        public static ArrayList<Float> s_king_b = new ArrayList<Float>(Arrays.asList(
            20f, 30f, 10f, 0f, 0f, 10f, 30f, 
            20f, 20f, 20f, 0f, 0f, 0f, 0f, 20f, 20f,
             -10f, -20f, -20f, -20f, -20f, -20f, 
             -20f, -10f, -20f, -30f, -30f, -40f, 
             -40f, -30f, -30f, -20f, -30f, -40f, 
             -40f, -50f, -50f, -40f, -40f, -30f, 
             -30f, -40f, -40f, -50f, -50f, -40f, 
             -40f, -30f, -30f, -40f, -40f, -50f, 
             -50f, -40f, -40f, -30f, -30f, -40f, 
             -40f, -50f, -50f, -40f, -40f, -30f
            ));
    
    public static ArrayList<Float> s_queen_w = new ArrayList<Float>(Arrays.asList(
        -20f,-10f,-10f, -5f, -5f,-10f,-10f,-20f,
-10f, 0f, 0f, 0f, 0f, 0f, 0f, -10f,
-10f, 0f, 5f, 5f, 5f, 5f, 0f, -10f,
 -5f, 0f, 5f, 5f, 5f, 5f, 0f, -5f,
  0f, 0f, 5f, 5f, 5f, 5f, 0f, -5f,
-10f, 5f, 5f, 5f, 5f, 5f, 0f, -10f,
-10f, 0f, 5f, 0f, 0f, 0f, 0f, -10f,
-20f,-10f,-10f, -5f, -5f,-10f,-10f,-20f
        ));

        public static ArrayList<Float> s_queen_b = new ArrayList<Float>(Arrays.asList(
            -20f, -10f, -10f, -5f, -5f, -10f, -10f,
             -20f, -10f, 0f, 0f, 0f, 0f, 5f, 0f, 
             -10f, -10f, 0f, 5f, 5f, 5f, 5f, 5f, 
             -10f, -5f, 0f, 5f, 5f, 5f, 5f, 0f, 0f, 
             -5f, 0f, 5f, 5f, 5f, 5f, 0f, -5f, -10f,
              0f, 5f, 5f, 5f, 5f, 0f, -10f, -10f, 0f,
               0f, 0f, 0f, 0f, 0f, -10f, -20f, -10f,
                -10f, -5f, -5f, -10f, -10f, -20f));

                public static ArrayList<Float> s_king_w_end = new ArrayList<Float>(Arrays.asList(-50f,-40f,-30f,-20f,-20f,-30f,-40f,-50f,
                -30f,-20f,-10f,  0f,  0f,-10f,-20f,-30f,
                -30f,-10f, 20f, 30f, 30f, 20f,-10f,-30f,
                -30f,-10f, 30f, 40f, 40f, 30f,-10f,-30f,
                -30f,-10f, 30f, 40f, 40f, 30f,-10f,-30f,
                -30f,-10f, 20f, 30f, 30f, 20f,-10f,-30f,
                -30f,-30f,  0f,  0f,  0f,  0f,-30f,-30f,
                -50f,-30f,-30f,-30f,-30f,-30f,-30f,-50f));

                public static ArrayList<Float> s_king_b_end = new ArrayList<Float>(Arrays.asList(-50.0f, -30.0f, -30.0f, -30.0f, -30.0f, -30.0f, -30.0f, -50.0f, -30.0f, -30.0f, 0.0f, 0.0f, 0.0f, 0.0f, -30.0f, -30.0f, -30.0f, -10.0f, 20.0f, 30.0f, 30.0f, 20.0f, -10.0f, -30.0f, -30.0f, -10.0f, 30.0f, 40.0f, 40.0f, 30.0f, -10.0f, -30.0f, -30.0f, -10.0f, 30.0f, 40.0f, 40.0f, 30.0f, -10.0f, -30.0f, -30.0f, -10.0f, 20.0f, 30.0f, 30.0f, 20.0f, -10.0f, -30.0f, -3.0f, -20.0f, -10.0f, 0.0f, 0.0f, -10.0f, -20.0f, -30.0f, -50.0f, -40.0f, -30.0f, -20.0f, -20.0f, -30.0f, -40.0f, -50.0f));

    public void SetColor(String i_color) { color = i_color;}
    public String getColor() { return color; }

 
  
    public String getCoord(Integer pos)
    {
        return coord.get(pos);
    }
    // Init the chess board
    public ArrayList<Piece> values;
    // en enPassant variable
    public int enPassant;
    // Castling rights
    Boolean whiteCanCastling56;
    Boolean whiteCanCastling63;
    Boolean blackCanCastling0;
    Boolean blackCanCastling7;

    // Board constructor
    public Board(String i_color){
        // The moves history is recovered by API --> need a list of

        //Init the chess board at starting position
        values = new ArrayList<Piece>(Arrays.asList(
        new Piece("TOWER","BLACK"), new Piece("KNIGHT","BLACK"), new Piece("BISHOP","BLACK"),new Piece("QUEEN","BLACK"), new Piece("KING","BLACK"), new Piece("BISHOP","BLACK"), new Piece("KNIGHT","BLACK"), new Piece("TOWER","BLACK"),
        new Piece("PAWN","BLACK"), new Piece("PAWN","BLACK"), new Piece("PAWN","BLACK"), new Piece("PAWN","BLACK"), new Piece("PAWN","BLACK"), new Piece("PAWN","BLACK"), new Piece("PAWN","BLACK"), new Piece("PAWN","BLACK"),
        new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
        new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
        new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
        new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(), new Piece(),
        new Piece("PAWN","WHITE"), new Piece("PAWN","WHITE"), new Piece("PAWN","WHITE"), new Piece("PAWN","WHITE"), new Piece("PAWN","WHITE"), new Piece("PAWN","WHITE"), new Piece("PAWN","WHITE"), new Piece("PAWN","WHITE"),
        new Piece("TOWER","WHITE"), new Piece("KNIGHT","WHITE"), new Piece("BISHOP","WHITE"), new Piece("QUEEN","WHITE"),new Piece("KING","WHITE"),new Piece("BISHOP","WHITE"),new Piece("KNIGHT","WHITE"),new Piece("TOWER","WHITE")
        ));
        // Define the square number to take pawn 'en enPassant'

        int enPassant = -1;

        // Castling rights
        whiteCanCastling56 = true;
        whiteCanCastling63 = true;
        blackCanCastling0 = true;
        blackCanCastling7 = true;
        this.SetColor(i_color);
    }

    public Board(){
        values = null;
            int enPassant = -1;

        // Castling rights
        whiteCanCastling56 = false;
        whiteCanCastling63 = false;
        blackCanCastling0 = false;
        blackCanCastling7 = false;


    }

   

    public Board(Board i_board){
        values = new ArrayList<Piece>(i_board.getValues());
        int enPassant = -1;

        // Castling rights
        whiteCanCastling56 = i_board.whiteCanCastling56;
        whiteCanCastling63 = i_board.whiteCanCastling63;
        blackCanCastling0 = i_board.blackCanCastling0;
        blackCanCastling7 = i_board.blackCanCastling7;
        this.SetColor(i_board.getColor());

    }


    /* Moves list function, return all possible moves depending to the color
     * Useful for heuristic
     * 
     */

    // Chess board getter
    public ArrayList<Piece> getValues(){
        return values;
    }

    // Chess board setter
    public void setValues(int a){
        a = 1;
    }

    /** Get all possible moves
     * 
     * @param myChess the latest version of the board composition 
     * @param color the color of our IA
     * @return ArrayList<Tuple> of all possible moves possible for each piece
     */
    public ArrayList<Tuple> getMoves(String color,Board myChess, Boolean boolean_attack){
        int counter = 0;
        ArrayList<Tuple> moves = new ArrayList<Tuple>();
        // Init actual color
        // Consult each piece on the board
        String Color_Adverse = "WHITE".equals(color)?"BLACK":"WHITE";
        for(Piece piece : values){
            if (!piece.color.equals(color)){
                // Do nothing if square color is not our
            }
            
            else if(piece.name.equals("KING") && boolean_attack){
                moves.addAll(piece.pos2_king_1(counter,Color_Adverse,myChess,this.isChecked()));
                writeFile("king moves: " + piece.pos2_king_1(counter,Color_Adverse,myChess,this.isChecked()));
                
            }
            else if(piece.name.equals("KING")){
                moves.addAll(piece.pos2_king_2(counter,Color_Adverse,myChess));
                //writeFile("king moves: " + piece.pos2_king(counter,Color_Adverse,myChess,this.isChecked()));
                
            }
            else if(piece.name.equals("QUEEN")){
                moves.addAll(piece.pos2_tower(counter,Color_Adverse,myChess));
                moves.addAll(piece.pos2_bishop(counter,Color_Adverse,myChess));
            }
             else if(piece.name.equals("TOWER")){
            //     if(!boolean_attack){
            //         writeFile("Tower called: " + piece.pos2_tower(counter,Color_Adverse,myChess));
            //     }
                moves.addAll(piece.pos2_tower(counter,Color_Adverse,myChess));
            }
            else if(piece.name.equals("KNIGHT")){
                moves.addAll(piece.pos2_knight(counter,Color_Adverse,myChess));
            }
            else if(piece.name.equals("BISHOP")){
                moves.addAll(piece.pos2_bishop(counter,Color_Adverse,myChess));
            }
            else if(piece.name.equals("PAWN")){
                moves.addAll(piece.pos2_pawn(counter,Color_Adverse,myChess));
                //writeFile("pawn moves: " + piece.pos2_pawn(counter,Color_Adverse,myChess));

            }
            counter += 1;
        }
        return moves;
    }

    /* Convert the string move to index for the array list which represents the baord
     * i.e.: "e2e7" return a Tuple object where Tuple(12, 57, ""), 12 for e2 and 57 for e7
     * 
     */
    private Tuple convert_string_move_to_index(String move) {
        String first_pos = move.substring(0,2);
        String last_pos = move.substring(2);

        // A beautiful function to get the index 
        int first_index = ((int)first_pos.charAt(0))-(int)'a' + (8-Integer.parseInt(first_pos.substring(1)))*8;
        int second_index = ((int)last_pos.charAt(0))-(int)'a' + (8-Integer.parseInt(last_pos.substring(1)))*8;
        return new Tuple(first_index, second_index, "");
    }

    /* Move a piece without check if the movement isn't illegal
     * @arg move: a simple string which give the move, i.e: "e2e7"
     * 
     */
    public void move_piece_without_check(String move) {
        nb_coup += 1;
        // We have a pawn which transforms in a queen
        if(move.length() == 5) {
            String new_piece = Character.toString(move.charAt(4));
            String new_move = move.substring(0, 4);
            Tuple new_positions = convert_string_move_to_index(new_move);
            String color_piece = values.get(new_positions.getFirst()).color;
            values.set(new_positions.getFirst(), new Piece());
            if("q".equals(new_piece)) {
                values.set(new_positions.getSecond(), new Piece("QUEEN", color_piece));
            } else if("r".equals(new_piece)) {
                values.set(new_positions.getSecond(), new Piece("TOWER", color_piece));
            } else if("b".equals(new_piece)) {
                values.set(new_positions.getSecond(), new Piece("BISHOP", color_piece));
            } 
        }

        Tuple positions = convert_string_move_to_index(move);

        if(this.getValues().get(4).name().equals("KING") && move.equals("e8g8")){
            Piece tmp_p = values.get(7);
            values.set(7, new Piece());
            values.set(5,tmp_p);
        }
        else if(this.getValues().get(4).name().equals("KING") && move.equals("e8b8")){
            Piece tmp_p = values.get(0);
            values.set(0, new Piece());
            values.set(2,tmp_p);
        }
        else if(this.getValues().get(60).name().equals("KING") && move.equals("e1g1")){
            Piece tmp_p = values.get(63);
            values.set(63, new Piece());
            values.set(61,tmp_p);
        }
        else if(this.getValues().get(60).name().equals("KING") && move.equals("e1b1")){
            Piece tmp_p = values.get(56);
            values.set(56, new Piece());
            values.set(58,tmp_p);
        }
        //writeFile("move: " + move);

        

        //writeFile("position: "+ String.valueOf(positions.getFirst()) + ":"+ String.valueOf(positions.getSecond()));
        
        Piece first_Pieces = values.get(positions.getFirst());
        //writeFile("Piece: " + first_Pieces.name + "from color: " + first_Pieces.color);
        values.set(positions.getFirst(), new Piece());
        values.set(positions.getSecond(), first_Pieces);
    }

    @Override
    public String toString() {        
        String sortie = "";
        for(int i =0; i < this.values.size(); i++) {
            if(i%8 == 0) {
                sortie += "|\n|";
            }
            String name = this.values.get(i).name;
            String color = this.values.get(i).color.length() >0 ? this.values.get(i).color.substring(0, 1): "";
            sortie += " "+ name + color;
            for (int j =0; j < 9-(name.length()+color.length()); j++) {
                sortie += " ";
            }
            sortie += " ";
        }
        return sortie;

        
    }

    private void writeFile(String line) {
        try {
            FileWriter myWriter = new FileWriter("debug/debug_board.txt", true);
            myWriter.write(line+"\n");
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }


    /**Move a piece on the board from start to finish position depending to prise en enPassant, promote, under promote and castle rights
     * @start starting position of the piece to move (0..63)
     * @finish  finishing position of the piece moved (0..63)
     * @promote the pawn is change to requested piece
     * @param myChess the latest version of the board composition 
     * @param color the color of our IA
     * @param colorToPlay color of the side to play
     * @return true if the move is possible (not king in check), if not false
     */
    public Boolean movePiece(int start, int finish, String promote, Board myChess, String color, String colorToPlay){
        
        // Moving piece
        Piece pieceMoved = this.values.set(finish, this.values.get(start));
        // Replace last piece by empty one
        Piece pieceTaken = this.values.set(start, new Piece());
        
        // KING moved
        if (pieceMoved.name == "KING"){
            // White side
            if (color == "WHITE"){
                // Moving for the first time, update castling
                if (start == 60){
                    this.whiteCanCastling56 = false;
                    this.whiteCanCastling63 = false;
                    // Castling, move the rook
                    if (finish == 58){
                        this.values.set(56, new Piece());
                        this.values.set(59, new Piece("TOWER","WHITE"));
                    }
                    else if (finish == 62){
                        this.values.set(63, new Piece());
                        this.values.set(61, new Piece("TOWER","WHITE"));
                    }
                }
            }
            // Black side
            else {
                // Moving for the first time, update castling
                if (start == 4){
                    this.blackCanCastling0 = false;
                    this.blackCanCastling7 = false;
                    // Castling, move the rook
                    if (finish == 6){
                        this.values.set(7, new Piece());
                        this.values.set(5, new Piece("TOWER","BLACK"));
                    }
                    else if (finish == 2){
                        this.values.set(0, new Piece());
                        this.values.set(3, new Piece("TOWER","BLACK"));
                    }
                }
            }

        }
        // TOWER moved
        else if (pieceMoved.name == "TOWER"){
           // White side
           if (color == "WHITE"){
                // Castling
                if (start == 56){
                    this.whiteCanCastling56 = false;
                }
                else if (start == 63){
                    this.whiteCanCastling63 = false;
                }
            }
            // Black side
            else {
                if (start == 0){
                    this.blackCanCastling0 = false;
                }
                else if (start == 7){
                    this.blackCanCastling7 = false;
                }
            }
        }
        // PAWN moved
        else if (pieceMoved.name == "PAWN"){
            // White side
            if (color == "WHITE"){
                // The en enPassant move
                if (this.enPassant == finish){
                    // Take black pawn
                    pieceTaken = this.values.get(finish+8);
                    // Insert an empty piece
                    this.values.set(finish+8, new Piece());
                }
                // The white pawn moves 2 squares from starting squar then blacks can take "en enPassant" next move
                else if (start == 6 && finish == 4){
                    this.enPassant = finish+8;
                }
            }
            // Black side
            else {
                if (this.enPassant == finish){
                    pieceTaken = this.values.get(finish-8);
                    this.values.set(finish-8, new Piece());
                }
                else if (start == 1 && finish == 3){
                    this.enPassant = finish-8;
                }
            }        

        }
        // Promote
        if (promote != ""){
            switch (promote){
                // QUEEN
                case "q" :
                    this.values.set(finish, new Piece("QUEEN",color));
                    break;
                // KING
                case "k" :
                this.values.set(finish, new Piece("KING",color));
                break;
                // KNIGHT
                case "n" :
                this.values.set(finish, new Piece("KNIGHT",color));
                break;
                // BISHOP
                case "b" :
                this.values.set(finish, new Piece("BISHOP",color));
                break;
            }
        }
        // King in check, game over
        // if (!isChecked(color, oppositeColor(colorToPlay), myChess)){
        //     return false;
        // }
        return true;
    }
    /** Determine if the king of the given color is in check
     * @param color color of our king
     * @param colorToPlay color of the side to play
     * @param myChess the latest version of the board composition 
     * @return true if the king is in check, if not false
     */
    public Boolean isChecked(){
         int position = 0;
         // Find the king in the board
         for (int i = 1; i < 64; i++){
             if (this.values.get(i).name == "KING" && this.values.get(i).color == this.getColor()){
                 position = i;
                 break;
             }
         }
         return isAttacked(position);
     }

    /** Determine if square at the position in parameter is a destination for the color in parameter
     * Function used for in check and castle moves
     * @param position of the piece
     * @param color of the piece
     * @return true if the destination is accepted, if not false
     */
     public Boolean isAttacked(int position){
          ArrayList<Tuple> list = new ArrayList<>();

          list = getMoves(oppositeColor(color),this, false);
          // Path of the position list, return true if one is equal to position in parameter
          for (Tuple move : list){
            //writeFile("Position of our king: " + position);
            //writeFile("Move possible from the "+color+": "+this.getCoord(move.getFirst()).toString()+this.getCoord(move.getSecond()).toString());
              if(move.getSecond() == position){
                  return true;
              }
          }
          return false;
      }

    // Determine the opposite color in parameter
    public String oppositeColor(String color){
        if(color == "BLACK"){
            return "WHITE";
        }
        return "BLACK";
    }

    // Determine the row from 0(a8-h8) to 7(a1-h1) of the position in parameter
    public Integer Row(Integer position){
        int row = position / 8;
        return row;
    }

    // Determine the column from 0 to 7 of the position in parameter
    public Integer Col(Integer position){
        int row = position % 8;
        return row;
    }

    public Integer getEnPassant(){
        return this.enPassant;
    }

    public Boolean getBlackCanCastling0(){
        return this.blackCanCastling0;
    }

    public Boolean getBlackCanCastling7(){
        return this.blackCanCastling7;
    }

    public Boolean getWhiteCanCastling56(){
        return this.whiteCanCastling56;
    }

    public Boolean getWhiteCanCastling63(){
        return this.whiteCanCastling63;
    }

    public int howMuchPiece(String color, boolean both_color) {
        int result = 0;

        for(Piece piece : values) {
            if(!piece.name.equals(piece.VIDE) && (both_color || color.equals(piece.color))) {
                result += 1;
            }
        }
        return result;
    }

    public Float heuristic(){
        Float WhiteScore = 0.0f;
        Float BlackScore = 0.0f;
        Float score_piece=0.0f;
        for(int i = 0; i <this.getValues().size(); i++){
            Integer val = this.getValues().get(i).getVal(this.color);
            if(this.getValues().get(i).color().equals("WHITE")){
                        if(this.getValues().get(i).name().equals("TOWER")){
                            score_piece =val+s_tower_w.get(i);
                        }
                        else if(getValues().get(i).name().equals("QUEEN")){
                            score_piece=val+s_queen_w.get(i);
                        }
                        else if(getValues().get(i).name().equals("BISHOP")){
                            score_piece=val+s_bishop_w.get(i);
                        }
                        else if(getValues().get(i).name().equals("KING")){
                            if(this.howMuchPiece("WHITE", false) <= 7) {
                                score_piece=(float)val + s_king_w_end.get(i);
                            } else {
                                score_piece=(float)val + s_king_w.get(i);
                            }
                            
                        }
                        else if(getValues().get(i).name().equals("PAWN")){
                            score_piece=val+s_pawn_w.get(i);
                        }
                        else if(getValues().get(i).name().equals("KNIGHT")){
                            score_piece=val+s_knight_w.get(i);
                        }

                        
                    
                    WhiteScore = WhiteScore +  score_piece;
                    score_piece = 0.0f;
            }
            else{

                    if(getValues().get(i).name().equals("TOWER")){
                        score_piece =val+s_tower_b.get(i);
                    }
                    else if(getValues().get(i).name().equals("QUEEN")){
                        score_piece =val +s_queen_b.get(i);
                    }
                    else if(getValues().get(i).name().equals("BISHOP")){
                        score_piece =val+s_bishop_b.get(i);
                    }
                    else if(getValues().get(i).name().equals("KING")){
                        if(this.howMuchPiece("BLACK", false) <= 7) {
                            score_piece = (float)val + s_king_b_end.get(i);
                        } else {
                            score_piece =(float)val + s_king_b.get(i);
                        }
                        
                        
                    }
                    else if(getValues().get(i).name().equals("PAWN")){
                        score_piece =val+s_pawn_b.get(i);
                    }
                    else if(getValues().get(i).name().equals("KNIGHT")){
                        score_piece =val+s_knight_b.get(i);
                    }

                    
                
                    BlackScore = BlackScore +score_piece;
                    score_piece = 0.0f;


            }
        }
        if(this.getColor().equals("WHITE")){return WhiteScore-BlackScore;}
        else{return BlackScore - WhiteScore;}
    }

    public static void main(String[] args) {
        Board myChess = new Board("WHITE");
        String color = "WHITE";
        ArrayList<Tuple> all_move = myChess.getMoves(color,myChess, false);
        Integer a = all_move.get(0).getFirst();
        //System.out.println(all_move.get(0).getFirst().toString());
        //System.out.println(myChess.getCoord(all_move.get(0).getFirst()).toString());
       
    }
}

