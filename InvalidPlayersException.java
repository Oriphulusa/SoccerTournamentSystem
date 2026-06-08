public class InvalidPlayersException extends Exception {

    // --- Constructor with custom error message ---
    public InvalidPlayersException(String message) {
        super(message); // passes the message up to Exception
    }

    // --- Constructor with invalid player count included in message ---
    public InvalidPlayersException(int players) {
        super("Invalid number of players: " + players +
              ". A team must have between 11 and 30 players.");
    }
}