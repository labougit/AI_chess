package chess;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
// chess package to use new Piece.java methods
import chess.*;
import chess.Tuple;


public class Board{
    // Define the next color to play 
    // Init the 8x8 chess board ArrayList object
    public final ArrayList<String> coord = new ArrayList<String>(Arrays.asList(
        "a8","b8","c8","d8","e8","f8","g8","h8",
        "a7","b7","c7","d7","e7","f7","g7","h7",
        "a6","b6","c6","d6","e6","f6","g6","h6",
        "a5","b5","c5","d5","e5","f5","g5","h5",
        "a4","b4","c4","d4","e4","f4","g4","h4",
        "a3","b3","c3","d3","e3","f3","g3","h3",
        "a2","b2","c2","d2","e2","f2","g2","h2",
        "a1","b1","c1","d1","e1","f1","g1","h1"
    ));
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
    public Board(){
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
        for(Piece piece : values){
            if (piece.color != color){
                // Do nothing if square color is not our
            }
            else if(piece.name == "KING"){
                moves.addAll(piece.pos2_king(counter,"cAd",myChess,boolean_attack));
            }
            else if(piece.name == "QUEEN"){
                moves.addAll(piece.pos2_tower(counter,"cAd",myChess));
                moves.addAll(piece.pos2_bishop(counter,"cAd",myChess));
            }
            else if(piece.name == "TOWER"){
                moves.addAll(piece.pos2_tower(counter,"cAd",myChess));
            }
            else if(piece.name == "KNIGHT"){
                moves.addAll(piece.pos2_knight(counter,"cAd",myChess));
            }
            else if(piece.name == "BISHOP"){
                moves.addAll(piece.pos2_bishop(counter,"cAd",myChess));
            }
            else if(piece.name == "PAWN"){
                moves.addAll(piece.pos2_pawn(counter,"cAd",myChess));
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
        writeFile("move: " + move);

        Tuple positions = convert_string_move_to_index(move);

        writeFile("position: "+ String.valueOf(positions.getFirst()) + ":"+ String.valueOf(positions.getSecond()));
        
        Piece first_Pieces = values.get(positions.getFirst());
        writeFile("Piece: " + first_Pieces.name + "from color: " + first_Pieces.color);
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
        if (!isChecked(color, oppositeColor(colorToPlay), myChess)){
            return false;
        }
        return true;
    }
    /** Determine if the king of the given color is in check
     * @param color color of our king
     * @param colorToPlay color of the side to play
     * @param myChess the latest version of the board composition 
     * @return true if the king is in check, if not false
     */
    public Boolean isChecked(String color, String colorToPlay, Board myChess){
        int position = 0;
        // Find the king in the board
        for (int i = 1; i < 64; i++){
            if (this.values.get(i).name == "KING" && this.values.get(i).color == colorToPlay){
                position = i;
                break;
            }
        }
        return isAttacked(position, oppositeColor(color), myChess);
    }

    /** Determine if square at the position in parameter is a destination for the color in parameter
     * Function used for in check and castle moves
     * @param position of the piece
     * @param color of the piece
     * @return true if the destination is accepted, if not false
     */
    public Boolean isAttacked(int position, String color, Board myChess){
        ArrayList<Tuple> list = new ArrayList<>();
        list = getMoves(color,myChess, false);
        // Path of the position list, return true if one is equal to position in parameter
        for (Tuple move : list){
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
        int row = position % 8;
        return row;
    }

    // Determine the column from 0 to 7 of the position in parameter
    public Integer Col(Integer position){
        // fonction Erwan convert...
        int row = position / 8;
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
        return this.whiteCanCastling63;
    }

    public Boolean getWhiteCanCastling63(){
        return this.whiteCanCastling63;
    }

    public static void main(String[] args) {
        Board myChess = new Board();
        String color = "WHITE";
        myChess.getMoves(color,myChess, false);
        System.out.println(myChess.getValues());
        System.out.println(myChess.Col(63));
    }
}

