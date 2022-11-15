package api;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;


public class Entree extends Thread {

    private ArrayBlockingQueue<String> queueGo;
    private ArrayBlockingQueue<String[]> queuePos;
    private ArrayBlockingQueue<String> queueMove;

    public Entree(ArrayBlockingQueue<String> queueGo, ArrayBlockingQueue<String[]> queuePos, ArrayBlockingQueue<String> queueMove) {
        this.queueGo = queueGo;
        this.queuePos = queuePos;
        this.queueMove = queueMove;
    }

    private void writeFile(String line) {
        try {
            FileWriter myWriter = new FileWriter("debug_entree.txt", true);
            myWriter.write(line+"\n");
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    }

    private void inputUCI() {
        System.out.println("id name Useless\r\n");
        System.out.println("id author LePlusBEAU\r\n");
        System.out.println("uciok\r\n");
    }

    private void inputIsReady() {
        System.out.println("readyok\r\n");
    }

    private void inputGo() {
        try {
            this.queueGo.put("go");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        //Continue to send if it's not the bestmove
        writeFile("Go pass, wait bestmove");
        String move;
        do {
            try {
                move = this.queueMove.take();
                writeFile("move: "+move);
                System.out.println(move);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }while (!("bestmove".equals(move.substring(0, 8))));
        
        
    }

    private void inputPosition(String line) {
        if (line.indexOf("startpos") != -1) {
            if (line.indexOf("moves") != -1) {

                String[] array_moves = (line.substring(line.indexOf("moves")+5)).split(" ");
                writeFile("Array_moves: "+line.substring(line.indexOf("moves")+5)+"\n");
                try {
                    this.queuePos.put(array_moves);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                writeFile("End array_moves\n");
            }
            else {
                try {
                    String[] emptyStrings = {};
                    this.queuePos.put(emptyStrings);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
            
            String inputString = scanner.nextLine();
            writeFile(inputString);
            if("uci".equals(inputString)) {
                inputUCI();
            }
            else if("isready".equals(inputString)) {
                inputIsReady();
            }
            else if("position".equals(inputString.substring(0, 8))) {
                inputPosition(inputString);
            }
            else if("go".equals(inputString.substring(0,2))) {
                inputGo();
            }
        }
    }
    
}
