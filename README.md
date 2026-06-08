# Soccer Tournament Management System

A Java OOP project built in BlueJ that simulates a soccer tournament management system.

## Project Overview
This system manages soccer teams in a tournament. It differentiates between 
Amateur and Professional teams, reads team data from a file, validates player 
counts, calculates ratings, and sorts teams by rating.

## OOP Concepts Demonstrated
- **Inheritance** — AmateurTeam and ProfessionalTeam extend the Team parent class
- **Polymorphism** — calculateRating() and toString() behave differently per subclass
- **Encapsulation** — All fields are private with getters and setters
- **Abstract Classes** — Team is abstract with an abstract calculateRating() method
- **Custom Exceptions** — InvalidPlayersException handles invalid player counts
- **ArrayList** — Used to store and manage all teams
- **File Reading** — Teams loaded from teams.txt
- **File Writing** — Sorted teams saved to output file
- **Sorting** — Teams sorted by rating using Collections.sort()

## Project Structure
