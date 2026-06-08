public class ProfessionalTeam extends Team {

    // --- Additional field for ProfessionalTeam ---
    private int leaguePoints;

    // --- Constructor ---
    public ProfessionalTeam(String teamName, String coachName, int players, int leaguePoints) {
        super(teamName, coachName, players); // calls Team's constructor
        this.leaguePoints = leaguePoints;
    }

    // --- Getter ---
    public int getLeaguePoints() {
        return leaguePoints;
    }

    // --- Setter ---
    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    // --- Implementing the abstract method from Team ---
    // Rating formula: (players * 3) + leaguePoints
    public double calculateRating() {
        return (getPlayers() * 3) + leaguePoints;
    }

    // --- Override toString to show professional-specific info ---
    public String toString() {
        return "[PROFESSIONAL] " + super.toString() +
               " | League Points: " + leaguePoints;
    }
}