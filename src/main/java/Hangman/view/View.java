package Hangman.view;

import Hangman.net.GameStateDTO;

public class View {
    public View(){
        System.out.println("Welcome to HangMan!\nGuess a letter or the full word. Type 'quit' to close the game.\n");
    }

    public void UpdateGameView(GameStateDTO gameState){


        // TODO: Här spottas jämförelse mellan content length ut. Hur ska vi göra?
        //Control: Are the lengths the same?
        //System.out.println(contentLengthControl(gameInfo));

        // CHOOSE TEMPLATES depending on GAME STATE
        if (gameState.state.equals("LETTER_GUESS")){
            String view = createLetterGuessView(gameState);
            System.out.println(view);
        }
        else if (gameState.equals("LOST"))
            printYouLoose(gameState);

        else if (gameState.equals("WIN"))
            printYouWin(gameState);

        else
            System.out.println("Nothing got caught in UpdateGameView");
    }

    private void printYouWin(GameStateDTO gameState) {
        String view = createLetterGuessView(gameState);
        System.out.println("\nYOU WON! The word was: '" + gameState.previousWord + "'");
        System.out.println("\n======= NEW ROUND ======\n");
        System.out.println(view);
    }

    private void printYouLoose(GameStateDTO gameState) {
        String view = createLetterGuessView(gameState);
        System.out.println("\nYOU LOST... The word was: '" + gameState.previousWord + "'");
        System.out.println("\n======= NEW ROUND ======\n");
        System.out.println(view);
    }

    private String createLetterGuessView(GameStateDTO state) {
        StringBuilder sb = new StringBuilder();
        sb.append("Score: " + state.gameScore + "  Lives: " + state.livesLeft + "/" + 3 +
                "   Correct: "+state.numCorrectGuesses + "  Hidden:"+ state.theHiddenWord + "  Guessed: " + state.guessedLetters);
        return sb.toString();
    }

    private String contentLengthControl(String[] gameInfo) {
        int lengthHeader = Integer.parseInt(gameInfo[0]);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < gameInfo.length;i++){
            sb.append(gameInfo[i] + "-");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
        int checkBodyLength = sb.toString().getBytes().length;
        if (checkBodyLength == lengthHeader) return "SAME: " + checkBodyLength + " & " + lengthHeader;
        else return "NOT SAME: " + checkBodyLength + " & " + lengthHeader;

    }
}
