package main;

import main.GameModule;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main{

    public static void main(String[] args) {
        JFrame window = new JFrame();
        GameModule gameModule = new GameModule();



        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("The Binding of Saiyan");
        window.add(gameModule);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gameModule.setupGame();
        gameModule.startGameThread();
    }
}
