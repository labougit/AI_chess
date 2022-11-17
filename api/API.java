package api;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;
import chess.Board;

public class API {

    ArrayBlockingQueue<String> queueGo = new ArrayBlockingQueue<>(50);
    ArrayBlockingQueue<String[]> queuePos = new ArrayBlockingQueue<>(50);
    ArrayBlockingQueue<String> queueMove = new ArrayBlockingQueue<>(50);

    boolean White =false;
    private boolean first = true;

    //Create thread
    Entree stdin_stdout = new Entree(queueGo, queuePos, queueMove);


    

    public API() {

        //Run the thread
        stdin_stdout.start();
    }

    public boolean infoSend(String info) {
        String line = "info "+info;
        try {
            queueMove.put(line);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public boolean moveSend(String move) {
        String line = "bestmove "+move;
        try {
            queueMove.put(line);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ImWhite(){
        return White;
    }

    private Board updateEchiquier(String[] pos) {

        //Check if you're white or black
        if(first) {
            first = false;
            this.White = pos.length == 0 ? true:false;
        }

        String color = this.White ? "WHITE": "BLACK";
        Board init_board = new Board(color);

        writeFile("Update board: " + Arrays.toString(pos) + "\nlength: " + pos.length);
        //Update Board
        for (String pos_move: pos) {
            if(pos_move.matches("[a-h][0-9][a-h][0-9]")) {
                init_board.move_piece_without_check(pos_move);
            }
            
        }

        return init_board;

    }
    
    public Board refresh() {
        writeFile("Begin position;");
        //init Board
        Board init_board;
        try {
            String[] position = queuePos.take();
            init_board = updateEchiquier(position);
        } catch (Exception e) {
            e.printStackTrace();
            init_board = new Board("WHITE");
        }
        writeFile("Finish position;");

        //Wait the GO to begin ponder
        try {
            queueGo.take();
            writeFile("Finish Go take;");
            writeFile("I'm white: " + ImWhite());
            
        } catch (Exception e) {
            e.printStackTrace();
        }


        return init_board;
        
    }

    private void writeFile(String line) {
        try {
            FileWriter myWriter = new FileWriter("debug/debug_API.txt", true);
            myWriter.write(line+"\n");
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }
}