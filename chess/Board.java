package chess;
import java.util.*;
// chess package to use new Piece<T>.java methods
import chess.*;


public class Board<T>{
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
    public ArrayList<Piece<T>> values;
    ArrayList<String> test;
    // Board constructor
    public Board(){
        // The moves history is recovered by API --> need a list of

        //Init the chess board at starting position
        values = new ArrayList<Piece<T>>(Arrays.asList(
        new Piece<T>("TOWER","BLACK"), new Piece<T>("KNIGHT","BLACK"), new Piece<T>("BISHOP","BLACK"),new Piece<T>("QUEEN","BLACK"), new Piece<T>("KING","BLACK"), new Piece<T>("BISHOP","BLACK"), new Piece<T>("KNIGHT","BLACK"), new Piece<T>("TOWER","BLACK"),
        new Piece<T>("PAWN","BLACK"), new Piece<T>("PAWN","BLACK"), new Piece<T>("PAWN","BLACK"), new Piece<T>("PAWN","BLACK"), new Piece<T>("PAWN","BLACK"), new Piece<T>("PAWN","BLACK"), new Piece<T>("PAWN","BLACK"), new Piece<T>("PAWN","BLACK"),
        new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(),
        new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(),
        new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(),
        new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(), new Piece<T>(),
        new Piece<T>("PAWN","WHITE"), new Piece<T>("PAWN","WHITE"), new Piece<T>("PAWN","WHITE"), new Piece<T>("PAWN","WHITE"), new Piece<T>("PAWN","WHITE"), new Piece<T>("PAWN","WHITE"), new Piece<T>("PAWN","WHITE"), new Piece<T>("PAWN","WHITE"),
        new Piece<T>("TOWER","WHITE"), new Piece<T>("KNIGHT","WHITE"), new Piece<T>("BISHOP","WHITE"), new Piece<T>("QUEEN","WHITE"),new Piece<T>("KING","WHITE"),new Piece<T>("BISHOP","WHITE"),new Piece<T>("KNIGHT","WHITE"),new Piece<T>("TOWER","WHITE")
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
    public ArrayList<Piece<T>> getValues(){
        return values;
    }

    // Chess board setter
    public void setValues(int a){
        a = 1;
    }

    // Get all possible moves
    public ArrayList<T> getMoves(Board<T> myChess){
        int counter = 0;
        ArrayList<T> moves = new ArrayList<T>();
        // Init actual color
        // Consult each piece on the board
        for(Piece<T> piece : values){
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

    public static <T> void main(String[] args) {
        Board<T> myChess = new Board<T>();
        System.out.println(myChess.getValues());
        myChess.getMoves(myChess);
    }
}

