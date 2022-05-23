package main;

import entity.Player;
import objects.SuperObject;
import server.GameServer;
import tiles.AssetSetter;
import tiles.TileManager;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameModule extends JPanel implements Runnable {
    final int originalTileSize = 16;
    public final int scale = 2;
    public final int tileSize = originalTileSize * scale;

//world settings
    public final int maxWorldCol = 25;
    public final int maxWorldRow = 22;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public final int maxScreenCol = 24;
    public final int maxScreenRow = 16;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;
    final int FPS = 60;

    TileManager tileM = new TileManager(this);

    private Socket socket;
    private int playerID;
    private int players;
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker colChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[5];

    Player otherPlayer = new Player(this, keyH);

   // Player[] playersObj = new Player[3];


    public GameModule(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        connectToServer();
        Thread readThread = new Thread(rfsRunnable);
        Thread writeThread = new Thread(wtsRunnable);
        readThread.start();
        writeThread.start();



        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime) / drawInterval;
            timer += (currentTime-lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                //System.out.println("FPS: " + drawCount);
                drawCount=0;
                timer=0;
            }

        }

    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
//        tileM.drawExtends(g2);



        for(int i = 0; i< obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }


        player.drawOtherPlayer(g2);
        player.draw(g2);
        g2.dispose();
    }

    public void connectToServer(){
        try{
            socket = new Socket("localhost", 45371);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.println("You are player# " + playerID);

            rfsRunnable = new ReadFromServer(in);
            wtsRunnable = new WriteToServer(out);

        }catch(IOException ex){
            System.out.println("IOException from connectToserver()");
        }
    }

    private class ReadFromServer implements Runnable{
        private DataInputStream dataIn;

        public ReadFromServer(DataInputStream in){
            dataIn = in;
            System.out.println("RFS Runnable created");
        }
        public void run() {
            try{
                while(true){
                    player.otherX = dataIn.readInt();
                    player.otherY = dataIn.readInt();
                    otherPlayer.spriteNum = dataIn.readInt();
                    players = dataIn.readInt();
                    player.otherRight = dataIn.readBoolean();
                }
            }catch(IOException ex){
                System.out.println("IOException from RFS run()");
            }
        }
    }

    private class WriteToServer implements Runnable{
        private DataOutputStream dataOut;

        public WriteToServer(DataOutputStream out){
            dataOut = out;
            System.out.println("WTS Runnable created");
        }
        public void run() {
            try{
                while(true){
                    dataOut.writeInt(player.x);
                    dataOut.writeInt(player.y);
                    dataOut.writeInt(player.spriteNum);
                    dataOut.writeBoolean(player.turned_right);

                    dataOut.flush();
                    try{
                        Thread.sleep(5);
                    }catch(InterruptedException ex){
                        System.out.println("InterruptedException from WTS run()");
                    }
                }

            }catch(IOException ex){
                System.out.println("IOException from WTS run()");
            }
        }
    }
}
