import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class TeamManager {

    // --- ArrayList to store all teams (uses polymorphism - stores both types) ---
    private ArrayList<Team> teams;

    // --- Constructor ---
    public TeamManager() {
        teams = new ArrayList<Team>();
    }

    // --- Add a team manually with validation ---
    public void addTeam(Team team) throws InvalidPlayersException {
        if (team.getPlayers() < 11 || team.getPlayers() > 30) {
            throw new InvalidPlayersException(team.getPlayers());
        }
        teams.add(team);
    }

    // --- Display all teams ---
    public void displayAllTeams() {
        if (teams.isEmpty()) {
            System.out.println("No teams registered.");
            return;
        }
        System.out.println("\n===== ALL TEAMS =====");
        for (Team t : teams) {
            System.out.println(t); // calls each team's toString() -- polymorphism
        }
        System.out.println("=====================\n");
    }

    // --- Sort teams by rating (highest first) ---
    public void sortTeamsByRating() {
        Collections.sort(teams, new Comparator<Team>() {
            public int compare(Team a, Team b) {
                return Double.compare(b.calculateRating(), a.calculateRating());
            }
        });
    }

    // --- Read teams from a text file ---
    public void loadTeamsFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue; // skip blank lines

            String[] parts = line.split(",");

            if (parts.length != 5) {
                System.out.println("Skipping invalid line: " + line);
                continue;
            }

            String type       = parts[0].trim();
            String teamName   = parts[1].trim();
            String coachName  = parts[2].trim();
            int players       = Integer.parseInt(parts[3].trim());
            int extraValue    = Integer.parseInt(parts[4].trim());

            try {
                if (type.equalsIgnoreCase("A")) {
                    addTeam(new AmateurTeam(teamName, coachName, players, extraValue));
                } else if (type.equalsIgnoreCase("P")) {
                    addTeam(new ProfessionalTeam(teamName, coachName, players, extraValue));
                } else {
                    System.out.println("Unknown team type: " + type + " — skipping.");
                }
            } catch (InvalidPlayersException e) {
                System.out.println("Skipping team '" + teamName + "': " + e.getMessage());
            }
        }

        reader.close();
        System.out.println("Teams loaded from file: " + filename);
    }

    // --- Save all teams to a text file ---
    public void saveTeamsToFile(String filename) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(filename));

        for (Team t : teams) {
            if (t instanceof AmateurTeam) {
                AmateurTeam a = (AmateurTeam) t;
                writer.println("A," + a.getTeamName() + "," + a.getCoachName() +
                               "," + a.getPlayers() + "," + a.getCommunitySupport());
            } else if (t instanceof ProfessionalTeam) {
                ProfessionalTeam p = (ProfessionalTeam) t;
                writer.println("P," + p.getTeamName() + "," + p.getCoachName() +
                               "," + p.getPlayers() + "," + p.getLeaguePoints());
            }
        }

        writer.close();
        System.out.println("Teams saved to file: " + filename);
    }

    // --- Getter for the teams list ---
    public ArrayList<Team> getTeams() {
        return teams;
    }
}