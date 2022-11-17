package chess;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
// chess package to use new Piece.java methods
import chess.*;
import chess.Tuple;


public class Board{
    // Define the next color to play 
    // Savoir quelle couleur doit jouer -> grace a l'API 
    String colorToPlay = "WHITE";
    // Savoir si on est noir ou blanc -> grace a l'API 
    String color = "BLACK";

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
    //Init the chess board
    public ArrayList<Piece> values;
    ArrayList<String> test;
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

        // Define the square number to take pawn 'en passant'
        int passant = -1;

        // Castling rights
        Boolean whiteCanCastling56 = true;
        Boolean whiteCanCastling63 = true;
        Boolean blackCanCastling0 = true;
        Boolean blackCanCastling7 = true;
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

    // Get all possible moves
    public ArrayList<Tuple> getMoves(Board myChess){
        int counter = 0;
        ArrayList<Tuple> moves = new ArrayList<Tuple>();
        // Init actual color
        // Consult each piece on the board
        for(Piece piece : values){
            if (piece.color != this.color){
                // Do nothing if square color is not our
            }
            else if(piece.name == "KING"){
                moves.addAll(piece.pos2_king(counter,"cAd",myChess));
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

    public static  void main(String[] args) {
        Board myChess = new Board();
        System.out.println(myChess.getValues());
        myChess.getMoves(myChess);
    }
}

