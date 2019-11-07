package Hangman.view;

import Hangman.net.GameStateDTO;

public class View {
    public View(){
        System.out.println("Welcome to HangMan!\nGuess a letter or the full word. Type 'quit' to close the game.\n");
    }

    public void UpdateGameView(GameStateDTO gameState){

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
}
