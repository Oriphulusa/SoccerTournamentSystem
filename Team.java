public abstract class Team {

    // --- Fields (Encapsulation: private) ---
    private String teamName;
    private String coachName;
    private int players;

    // --- Constructor ---
    public Team(String teamName, String coachName, int players) {
        this.teamName = teamName;
        this.coachName = coachName;
        this.players = players;
    }

    // --- Getters ---
    public String getTeamName() {
        return teamName;
    }

    public String getCoachName() {
        return coachName;
    }

    public int getPlayers() {
        return players;
    }

    // --- Setters ---
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    // --- Abstract method (Polymorphism) ---
    // Every subclass MUST provide its own version of this
    public abstract double calculateRating();

    // --- Display method ---
    // This can be overridden by subclasses if needed
    public String toString() {
        return "Team: " + teamName +
               " | Coach: " + coachName +
               " | Players: " + players +
               " | Rating: " + calculateRating();
    }
}