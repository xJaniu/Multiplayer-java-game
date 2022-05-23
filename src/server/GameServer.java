package server;

import entity.Player;
import main.GameModule;
import main.KeyHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;

    private Socket p1Socket;
    private Socket p2Socket;
    private ReadFromClient p1ReadRunnable;
    private ReadFromClient p2ReadRunnable;
    private WriteToClient p1WriteRunnable;
    private WriteToClient p2WriteRunnable;

    private int p1x,p1y,p1s,p2x,p2y,p2s;
    private boolean p1tr,p2tr;

    KeyHandler keyH = new KeyHandler();
    GameModule gm = new GameModule();

    public GameServer(){
        System.out.println("===GAME SERVER===");
        numPlayers = 0;
        maxPlayers = 2;

        p1x = 100;
        p1y = 100;
        p2x = 200;
        p2y = 200;

        try{
            ss = new ServerSocket(45371);
        }catch (IOException e){
            System.out.println("IOException from GS constructor");
        }
    }

    public void acceptConnections(){
        try{
            System.out.println("Waiting for connections...");

            while(numPlayers < maxPlayers){
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                numPlayers++;

                out.writeInt(numPlayers);
                System.out.println("Player #" + numPlayers + " has connected");

                ReadFromClient rfc = new ReadFromClient(numPlayers, in);
                WriteToClient wtc = new WriteToClient(numPlayers, out);

                if(numPlayers == 1){
                    p1Socket = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;
                    Thread readThread1 = new Thread(p1ReadRunnable);
                    readThread1.start();
                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    writeThread1.start();
                }
                if(numPlayers == 2 ){
                    p2Socket = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    readThread2.start();
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread2.start();
                }

            }
            System.out.println("No longer accepting connections");

        }catch(IOException ex){
            System.out.printf("IOException from acceptConn()");
        }
    }

    private class ReadFromClient implements Runnable{
        private int playerID;
        private DataInputStream dataIn;

        public ReadFromClient(int pid,DataInputStream in){
            playerID = pid;
            dataIn = in;
            System.out.println("RFC" + playerID + "Runnable created");
        }
        public void run(){
            try{
                while(true){
                    if(playerID == 1) {
                        p1x = dataIn.readInt();
                        p1y = dataIn.readInt();
                        p1s = dataIn.readInt();
                        p1tr = dataIn.readBoolean();
                    }
                    if(playerID == 2){
                        p2x = dataIn.readInt();
                        p2y = dataIn.readInt();
                        p2s = dataIn.readInt();
                        p2tr = dataIn.readBoolean();
                    }
                }
            }catch(IOException ex){
                System.out.println("IOException from RFC run()");
            }
        }
    }

    private class WriteToClient implements Runnable{
        private int playerID;
        private DataOutputStream dataOut;

        public WriteToClient(int pid,DataOutputStream out){
            playerID = pid;
            dataOut = out;
            System.out.println("WTC" + playerID + "Runnable created");
        }
        public void run(){
            try{
                while(true){
                    if(playerID==1){
                        dataOut.writeInt(p2x);
                        dataOut.writeInt(p2y);
                        dataOut.writeInt(p2s);
                        dataOut.writeInt(numPlayers);
                        dataOut.writeBoolean(p2tr);
                        dataOut.flush();
                    }
                    if(playerID == 2){
                        dataOut.writeInt(p1x);
                        dataOut.writeInt(p1y);
                        dataOut.writeInt(p1s);
                        dataOut.writeInt(numPlayers);
                        dataOut.writeBoolean(p1tr);
                        dataOut.flush();
                    }
                    try{
                        Thread.sleep(5);
                    }catch(InterruptedException ex){
                        System.out.println("InterruptedException from WTC run");
                    }
                }
            }catch(IOException ex){
                System.out.println("IOException from WTC run()");
            }
        }
    }



    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }

}
