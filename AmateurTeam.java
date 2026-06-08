public class AmateurTeam extends Team {

    // --- Additional field for AmateurTeam ---
    private int communitySupport;

    // --- Constructor ---
    public AmateurTeam(String teamName, String coachName, int players, int communitySupport) {
        super(teamName, coachName, players); // calls Team's constructor
        this.communitySupport = communitySupport;
    }

    // --- Getter ---
    public int getCommunitySupport() {
        return communitySupport;
    }

    // --- Setter ---
    public void setCommunitySupport(int communitySupport) {
        this.communitySupport = communitySupport;
    }

    // --- Implementing the abstract method from Team ---
    // Rating formula: (players * 2) + communitySupport
    public double calculateRating() {
        return (getPlayers() * 2) + communitySupport;
    }

    // --- Override toString to show amateur-specific info ---
    public String toString() {
        return "[AMATEUR] " + super.toString() +
               " | Community Support: " + communitySupport;
    }
}