package Hangman.view;

public class View {
    public View(){
        System.out.println("Welcome to HangMan!\nGuess a letter or the full word. Type 'quit' to close the game.\n");
    }

    public void UpdateGameView(String info){
        String[] gameInfo = info.split("-");

        // TODO: Här spottas jämförelse mellan content length ut. Hur ska vi göra?
        //Control: Are the lengths the same?
        //System.out.println(contentLengthControl(gameInfo));

        // Get GAME STATE
        String gameState = gameInfo[1];

        // CHOOSE TEMPLATES depending on GAME STATE
        if (gameState.equals("LETTER_GUESS")){
            String view = createLetterGuessView(gameInfo);
            System.out.println(view);
        }
        else if (gameState.equals("LOST"))
            printYouLoose(gameInfo);

        else if (gameState.equals("WIN"))
            printYouWin(gameInfo);

        else
            System.out.println("Nothing got caught in UpdateGameView");
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

    private void printYouWin(String[] gameInfo) {
        String view = createLetterGuessView(gameInfo);
        System.out.println("\nYOU WON! The word was: '" + gameInfo[7] + "'");
        System.out.println("\n======= NEW ROUND ======\n");
        System.out.println(view);
    }

    private void printYouLoose(String[] gameInfo) {
        String view = createLetterGuessView(gameInfo);
        System.out.println("\nYOU LOST... The word was: '" + gameInfo[7] + "'");
        System.out.println("\n======= NEW ROUND ======\n");
        System.out.println(view);
    }

    private String createLetterGuessView(String[] parts) {
        String gameScore = parts[2];
        String livesLeft = parts[3];
        String numCorrect = parts[4];
        String guesses = parts[5];
        String hiddenWord = parts[8];

        StringBuilder sb = new StringBuilder();
        sb.append("Score: " + gameScore + "  Lives: " + livesLeft + "/" + 3 +
                "   Correct: "+numCorrect + "  Hidden:"+ hiddenWord + "  Guessed: " + guesses);
        return sb.toString();
    }
}
