# Beach Volleyball Tournament — Java Collections

A small **Java (console, no frameworks)** project to demonstrate practical usage of **Collections**:

- `Set` — enforce uniqueness of athletes  
- `Map` (`HashMap`/`LinkedHashMap`) — quick lookup by ID and points table  
- `List` (`ArrayList`) — ordered lists of teams and matches  
- `LinkedList` — waitlist/queue for substitutes  

---

## 🚀 Features
- Register athletes (no duplicates by ID)
- Create teams from athletes
- Build a round-robin schedule
- Register match scores
- Ranking table (3 pts win, 1 draw)
- Substitute waitlist (queue)

---

## 🛠️ Tech Stack
- Java 21  
- Maven  
- JUnit 5 (unit tests)  
- Pure console app (**no Spring, no frameworks**)  

---

## ▶️ How to Run

```bash
# Run tests
mvn clean test

# Package without running tests
mvn -q -DskipTests package

# Execute JAR
java -jar target/torneio-volei-java-1.0.0.jar
```

---

## 📂 Project Structure
```
src/
 ├─ main/java/com/lenon/tournament/
 │   ├─ Main.java
 │   ├─ model/
 │   │   ├─ Athlete.java
 │   │   ├─ Team.java
 │   │   └─ Match.java
 │   ├─ service/
 │   │   └─ TournamentService.java
 │   └─ util/
 │       └─ ConsoleMenu.java
 └─ test/java/com/lenon/tournament/service/
     └─ TournamentServiceTest.java
```

---

## 📌 Next Steps (ideas for evolution)
- Validate team min/max size  
- Prevent athlete from joining multiple teams  
- Add option to list athletes/teams in the menu  
- Expand scoring to best-of-3 sets (21/15)  
- Convert to REST API (Spring Boot)  

---

👨‍💻 Author: **Lenon Merlo**  
