// besoin explication ligne 147

package chess;
import java.util.*;
// chess package to use new Piece.java methods
import chess.*;


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
    // Init the chess board
    public ArrayList<Piece<T>> values;
    // en passant variable
    public int passant;
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
        // Define the square number to take pawn 'en passant'

        int passant = -1;

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

    /**Move a piece on the board from start to finish position depending to prise en passant, promote, under promote and castle rights
     * @start starting position of the piece to move (0..63)
     * @finish  finishing position of the piece moved (0..63)
     * @promote the pawn is change to requested piece
     * @return true if the move is possible (not king in check), if not false
     */
    public Boolean movePiece(int start, int finish, String promote, Board<T> myChess){
        
        // Moving piece
        Piece<T> pieceMoved = this.values.set(finish, this.values.get(start));
        // Replace last piece by empty one
        Piece<T> pieceTaken = this.values.set(start, new Piece<T>());
        
        // KING moved
        if (pieceMoved.name == "KING"){
            // White side
            if (pieceMoved.color == "WHITE"){
                // Moving for the first time, update castling
                if (start == 60){
                    this.whiteCanCastling56 = false;
                    this.whiteCanCastling63 = false;
                    // Castling, move the rook
                    if (finish == 58){
                        this.values.set(56, new Piece<T>());
                        this.values.set(59, new Piece<T>("TOWER","WHITE"));
                    }
                    else if (finish == 62){
                        this.values.set(63, new Piece<T>());
                        this.values.set(61, new Piece<T>("TOWER","WHITE"));
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
                        this.values.set(7, new Piece<T>());
                        this.values.set(5, new Piece<T>("TOWER","BLACK"));
                    }
                    else if (finish == 2){
                        this.values.set(0, new Piece<T>());
                        this.values.set(3, new Piece<T>("TOWER","BLACK"));
                    }
                }
            }

        }
        // TOWER moved
        else if (pieceMoved.name == "TOWER"){
           // White side
           if (pieceMoved.color == "WHITE"){
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
            if (pieceMoved.color == "WHITE"){
                // The en passant move
                if (this.passant == finish){
                    // Take black pawn
                    pieceTaken = this.values.get(finish+8);
                    // Insert an empty piece
                    this.values.set(finish+8, new Piece<T>());
                }
                // The white pawn moves 2 squares from starting squar then blacks can take "en passant" next move
                else if (start == 6 && finish == 4){
                    this.passant = finish+8;
                }
            }
            // Black side
            else {
                if (this.passant == finish){
                    pieceTaken = this.values.get(finish-8);
                    this.values.set(finish-8, new Piece<T>());
                }
                else if (start == 1 && finish == 3){
                    this.passant = finish-8;
                }
            }        

        }
        // Promote
        if (promote != ""){
            switch (promote){
                // QUEEN
                case "q" :
                    this.values.set(finish, new Piece<T>("QUEEN",this.color));
                    break;
                // KING
                case "k" :
                this.values.set(finish, new Piece<T>("KING",this.color));
                break;
                // KNIGHT
                case "n" :
                this.values.set(finish, new Piece<T>("KNIGHT",this.color));
                break;
                // BISHOP
                case "b" :
                this.values.set(finish, new Piece<T>("BISHOP",this.color));
                break;
            }
        }
        // King in check, game over
        if (!isChecked(oppositeColor(colorToPlay), myChess)){
            return false;
        }
        return true;
    }
    /** Determine if the king of the given color is in check
     * @color color of the king
     * @return true if the king is in check, if not false
     */
    public Boolean isChecked(String color, Board<T> myChess){
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
     * @position of the piece
     * @color of the piece
     * @return true if the destination is accepted, if not false
     */
    public Boolean isAttacked(int position, String color, Board<T> myChess){
        ArrayList<T> list = new ArrayList<>();
        list = getMoves(myChess);
        // Path of the position list, return true if one is equal to position in parameter
        /*
        for (T piece : list){*
            if(piece == position){
                return true;
            }
        }
        */
        return false;
    }

    public String oppositeColor(String color){
        
        return "BLACK";
    }

    public static <T> void main(String[] args) {
        Board<T> myChess = new Board<T>();
        myChess.getMoves(myChess);
        System.out.println(myChess.getValues());
    }
}

