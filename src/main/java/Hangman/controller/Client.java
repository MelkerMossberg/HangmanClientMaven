package Hangman.controller;

import Hangman.net.ConnectionHandler;
import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;


public class Client {

    ConnectionHandler cc;

    public static void main(String[] args) {
        new Client();
    }

    /**
     * (1) Start one thread that listens for server
     * (2) Start another that listens to user input.
     */
    public Client(){
        try{
            Socket socket = new Socket("localhost", 44444);
            cc = new ConnectionHandler(socket, this);
            cc.start();

            listenUserInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenUserInput(){
        Scanner console = new Scanner(System.in);
        while(true){
            if (console.hasNext()){
                String userInput = console.nextLine();
                cc.sendToServer(cc.jwt , userInput);
            }

        }
    }

}