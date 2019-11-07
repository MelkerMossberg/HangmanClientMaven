package Hangman.net;

/**
 * ConnectionHandler is the thread that listens to the server and sends replies.
 */


import Hangman.controller.Client;
import Hangman.view.View;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler extends Thread {

    Socket socket;
    Client client;
    View view;
    BufferedReader din;
    PrintWriter dout;
    volatile boolean shouldRun = true;
    JSONParser jsonParser;


    public ConnectionHandler(Socket socket, Client client) {
        super("ConnectionHandler");
        this.socket = socket;
        this.client = client;
        this.view = new View();
        this.jsonParser = new JSONParser();
        try {
            din = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dout = new PrintWriter(socket.getOutputStream(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    // Listen for server input
    public void run() {
        while (shouldRun){
            try{
                String input = din.readLine();
                if (input != null){
                    if (input.equals("quit"))
                        closeConnection();
                    GameStateDTO gameState = parseJSONgameState(input);
                    view.UpdateGameView(gameState);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private GameStateDTO parseJSONgameState(String input) {
        GameStateDTO gameState = new GameStateDTO();
        Object JSONInput = null;
        try {
            JSONInput = jsonParser.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject wrapper = (JSONObject) JSONInput;
        JSONObject obj = (JSONObject) wrapper.get("gameState");
        gameState.state = (String) obj.get("state");
        gameState.gameScore = Integer.parseInt(obj.get("score").toString());
        gameState.totalLives = Integer.parseInt(obj.get("totalLives").toString());
        gameState.livesLeft = Integer.parseInt(obj.get("livesLeft").toString());
        gameState.numCorrectGuesses = Integer.parseInt(obj.get("numCorrect").toString());
        gameState.guessedLetters = (String)obj.get("prevGuesses");
        gameState.previousWord = (String)obj.get("prevWord");
        gameState.theHiddenWord = (String)obj.get("hiddenWord");

        return gameState;
    }

    private void closeConnection() {
        System.out.print("Quitting...");
        shouldRun = false;
        try {
            din.close();
            socket.close();
            dout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void sendToServer(String text){
        dout.println(text);
        dout.flush();
    }
}
