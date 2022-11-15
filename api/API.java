package api;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;

public class API {

    ArrayBlockingQueue<String> queueGo = new ArrayBlockingQueue<>(50);
    ArrayBlockingQueue<String[]> queuePos = new ArrayBlockingQueue<>(50);
    ArrayBlockingQueue<String> queueMove = new ArrayBlockingQueue<>(50);

    boolean White =false;
    private boolean first = true;

    //Create thread
    Entree stdin_stdout = new Entree(queueGo, queuePos, queueMove);

    ArrayList<Integer> echiquier;

    

    public API(ArrayList<Integer> echiquier) {
        this.echiquier = echiquier;

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

    private void updateEchiquier(String[] pos) {

        //Check if you're white or black
        if(first) {
            first = false;
            this.White = pos.length == 0 ? true:false;
        }

    }
    
    public boolean refresh() {
        writeFile("Begin position;");
        //Update position
        String[] position;
        try {
            position = queuePos.take();
            updateEchiquier(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeFile("Finish position;");

        //Wait the GO to begin ponder
        try {
            queueGo.take();
            writeFile("Finish Go take;");
            writeFile("I'm white: " + ImWhite());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    private void writeFile(String line) {
        try {
            FileWriter myWriter = new FileWriter("debug_API.txt", true);
            myWriter.write(line+"\n");
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }
}