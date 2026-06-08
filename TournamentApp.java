import java.io.IOException;
import java.util.Scanner;

public class TournamentApp {

    // --- Scanner declared once at class level ---
    private static Scanner scanner = new Scanner(System.in);
    private static TeamManager manager = new TeamManager();

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("   WELCOME TO SOCCER TOURNAMENT MANAGER  ");
        System.out.println("==========================================");

        boolean running = true;

        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addTeamManually();
                    break;
                case 2:
                    manager.displayAllTeams();
                    break;
                case 3:
                    manager.sortTeamsByRating();
                    System.out.println("Teams sorted by rating successfully.");
                    manager.displayAllTeams();
                    break;
                case 4:
                    loadFromFile();
                    break;
                case 5:
                    saveToFile();
                    break;
                case 6:
                    displayStats();
                    break;
                case 7:
                    running = false;
                    System.out.println("\nThank you for using Soccer Tournament Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }

        scanner.close();
    }

    // --- Print the menu ---
    private static void printMenu() {
        System.out.println("\n==========================================");
        System.out.println("            MAIN MENU                    ");
        System.out.println("==========================================");
        System.out.println("  1. Add a team manually");
        System.out.println("  2. Display all teams");
        System.out.println("  3. Sort teams by rating");
        System.out.println("  4. Load teams from file");
        System.out.println("  5. Save teams to file");
        System.out.println("  6. Display statistics");
        System.out.println("  7. Exit");
        System.out.println("==========================================");
    }

    // --- Add a team manually ---
    private static void addTeamManually() {
        System.out.println("\n--- ADD A NEW TEAM ---");
        System.out.println("Team type: Enter A for Amateur or P for Professional");
        String type = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine().trim();

        System.out.print("Enter coach name: ");
        String coachName = scanner.nextLine().trim();

        int players = getIntInput("Enter number of players (11-30): ");

        try {
            if (type.equals("A")) {
                int communitySupport = getIntInput("Enter community support value: ");
                manager.addTeam(new AmateurTeam(teamName, coachName, players, communitySupport));
                System.out.println("Amateur team '" + teamName + "' added successfully.");

            } else if (type.equals("P")) {
                int leaguePoints = getIntInput("Enter league points: ");
                manager.addTeam(new ProfessionalTeam(teamName, coachName, players, leaguePoints));
                System.out.println("Professional team '" + teamName + "' added successfully.");

            } else {
                System.out.println("Invalid type entered. Please enter A or P.");
            }
        } catch (InvalidPlayersException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- Load teams from file ---
    private static void loadFromFile() {
        System.out.print("Enter filename to load from (e.g. teams.txt): ");
        String filename = scanner.nextLine().trim();

        try {
            manager.loadTeamsFromFile(filename);
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    // --- Save teams to file ---
    private static void saveToFile() {
        System.out.print("Enter filename to save to (e.g. output.txt): ");
        String filename = scanner.nextLine().trim();

        try {
            manager.saveTeamsToFile(filename);
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    // --- Display basic statistics ---
    private static void displayStats() {
        if (manager.getTeams().isEmpty()) {
            System.out.println("No teams available for statistics.");
            return;
        }

        int amateur = 0;
        int professional = 0;
        double totalRating = 0;
        Team highest = manager.getTeams().get(0);
        Team lowest = manager.getTeams().get(0);

        for (Team t : manager.getTeams()) {
            if (t instanceof AmateurTeam) amateur++;
            else if (t instanceof ProfessionalTeam) professional++;

            totalRating += t.calculateRating();

            if (t.calculateRating() > highest.calculateRating()) highest = t;
            if (t.calculateRating() < lowest.calculateRating()) lowest = t;
        }

        double average = totalRating / manager.getTeams().size();

        System.out.println("\n===== TOURNAMENT STATISTICS =====");
        System.out.println("Total teams       : " + manager.getTeams().size());
        System.out.println("Amateur teams     : " + amateur);
        System.out.println("Professional teams: " + professional);
        System.out.printf( "Average rating    : %.2f%n", average);
        System.out.println("Highest rated     : " + highest.getTeamName() +
                           " (" + highest.calculateRating() + ")");
        System.out.println("Lowest rated      : " + lowest.getTeamName() +
                           " (" + lowest.calculateRating() + ")");
        System.out.println("=================================");
    }

    // --- Safe integer input helper ---
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
}