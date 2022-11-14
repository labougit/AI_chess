package chess;
import java.util.*;

import chess.Piece;


public class Board {
    // Init the 8x8 chess board ArrayList object
    ArrayList<String> coord = new ArrayList<String>(Arrays.asList(
        "a8","b8","c8","d8","e8","f8","g8","h8",
        "a7","b7","c7","d7","e7","f7","g7","h7",
        "a6","b6","c6","d6","e6","f6","g6","h6",
        "a5","b5","c5","d5","e5","f5","g5","h5",
        "a4","b4","c4","d4","e4","f4","g4","h4",
        "a3","b3","c3","d3","e3","f3","g3","h3",
        "a2","b2","c2","d2","e2","f2","g2","h2",
        "a1","b1","c1","d1","e1","f1","g1","h1"
    ));
    //Init the chess board at starting position
    ArrayList<String> values = new ArrayList<Piece>(Arrays.asList(
        Piece("TOWER","BLACK"),Piece("KNIGHT","BLACK"),Piece("BISHOP","BLACK"),Piece("QUEEN","BLACK"),Piece("KING","BLACK"),Piece("BISHOP","BLACK"),Piece("KNIGHT","BLACK"),Piece("TOWER","BLACK"),
        Piece("PAWN","BLACK"),Piece("PAWN","BLACK"),Piece("PAWN","BLACK"),Piece("PAWN","BLACK"),Piece("PAWN","BLACK"),Piece("PAWN","BLACK"),Piece("PAWN","BLACK"),Piece("PAWN","BLACK"),
        Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),
        Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),
        Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),
        Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),Piece(),
        Piece("PAWN","WHITE"),Piece("PAWN","WHITE"),Piece("PAWN","WHITE"),Piece("PAWN","WHITE"),Piece("PAWN","WHITE"),Piece("PAWN","WHITE"),Piece("PAWN","WHITE"),Piece("PAWN","WHITE"),
        Piece("TOWER","WHITE"),Piece("KNIGHT","WHITE"),Piece("BISHOP","WHITE"),Piece("QUEEN","WHITE"),Piece("KING","WHITE"),Piece("BISHOP","WHITE"),Piece("KNIGHT","WHITE"),Piece("TOWER","WHITE");
    ));

    /* Board constructor
     * 
     */
    public Board(){
        ArrayList<String> values_init = values;
    }

    /* Moves list function, return all possible moves depending to the color
     * Useful for heuristic
     * 
     */

    public static void main(String[] args) {
        Board myChess = new Board();
        System.out.println(myChess.coord);
    }
}

