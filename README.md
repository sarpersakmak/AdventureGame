[README.md](https://github.com/user-attachments/files/30377232/README.md)
# Adventure Game

A refactored console-based adventure game written in Java. The player selects a character, explores dangerous locations, fights enemies, buys equipment, collects three survival rewards, and returns to the Safe House to win.

## Features

- Three playable character classes with different damage, health, and starting money
- Three battle locations with randomized enemy counts
- Turn-based combat with fight-or-escape decisions
- Weapons and armor sold through an in-game store
- Health restoration at the Safe House
- Food, water, and firewood reward collection
- Input validation that prevents invalid text or menu numbers from crashing the game
- Automated unit tests for the core player, inventory, store, and healing rules

## Game Objective

Clear all three battle locations and collect:

| Location | Enemy | Reward |
|---|---|---|
| Cave | Zombie | Food |
| Forest | Vampire | Firewood |
| River | Bear | Water |

After collecting all rewards, return to the **Safe House** to win.

## Character Classes

| Character | Damage | Health | Starting Money |
|---|---:|---:|---:|
| Samurai | 5 | 21 | 15 |
| Archer | 7 | 18 | 20 |
| Knight | 8 | 24 | 5 |

## Equipment

### Weapons

| Weapon | Bonus Damage | Price |
|---|---:|---:|
| Pistol | 2 | 25 |
| Sword | 3 | 35 |
| Rifle | 7 | 45 |

### Armor

| Armor | Damage Block | Price |
|---|---:|---:|
| Light Armor | 1 | 15 |
| Medium Armor | 3 | 25 |
| Heavy Armor | 5 | 40 |

## Requirements

- Java Development Kit (JDK) 11 or newer
- Apache Maven 3.8 or newer for the recommended workflow

Verify the tools:

```bash
java -version
mvn -version
```

## Run with Maven

Clone the repository and enter the project directory:

```bash
git clone <repository-url>
cd Adventure-Game
```

Compile and run:

```bash
mvn clean compile exec:java
```

## Run without Maven

On macOS or Linux:

```bash
mkdir -p out
javac --release 11 -d out $(find src/main/java -name "*.java")
java -cp out com.adventuregame.Main
```

On Windows PowerShell:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
$files = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object FullName
javac --release 11 -d out $files
java -cp out com.adventuregame.Main
```

## Run Tests

```bash
mvn clean test
```

The unit tests cover:

- Character initialization
- Damage and armor calculations
- Exact-price and insufficient-money purchases
- Correct equipment prices
- Inventory reward collection
- Read-only reward exposure
- Safe House healing

## Project Structure

```text
Adventure-Game/
├── pom.xml
├── README.md
├── REFACTORING_NOTES.md
├── LICENSE
├── src/
│   ├── main/java/com/adventuregame/
│   │   ├── Main.java
│   │   ├── game/Game.java
│   │   ├── io/ConsoleIO.java
│   │   ├── location/
│   │   │   ├── BattleLocation.java
│   │   │   ├── Cave.java
│   │   │   ├── Forest.java
│   │   │   ├── Location.java
│   │   │   ├── NormalLocation.java
│   │   │   ├── River.java
│   │   │   ├── SafeHouse.java
│   │   │   └── ToolStore.java
│   │   └── model/
│   │       ├── Armor.java
│   │       ├── CharacterType.java
│   │       ├── Enemy.java
│   │       ├── Inventory.java
│   │       ├── Player.java
│   │       ├── Reward.java
│   │       └── Weapon.java
│   └── test/java/com/adventuregame/
│       ├── location/
│       └── model/
└── .gitignore
```

## Design Overview

The refactor separates the application into four responsibilities:

- `game`: controls the main game loop and location selection
- `io`: validates console input and handles output through one shared object
- `location`: implements Safe House, store, and battle behavior
- `model`: stores player, enemy, equipment, and reward state

Enums are used for character classes, rewards, weapons, and armor. This removes fragile string comparisons and keeps item statistics in one place.

## Important Rules

- Armor reduces incoming damage, but damage never becomes negative.
- A player may buy an item when their money exactly matches its price.
- Escaping a battle does not grant the location reward.
- Revisiting a cleared location can earn enemy money again, but the location reward is stored only once.
- Winning is checked when the player visits the Safe House with all three rewards.

## Known Limitations

- Game progress is not saved between sessions.
- Combat order is fixed: the player attacks first.
- Equipment replacement does not refund the previously equipped item.
- The game is text-only and supports one local player.

## Possible Improvements

- Add save/load support using JSON
- Add critical hits, dodge chance, and status effects
- Add a graphical interface
- Add difficulty levels and boss encounters
- Add integration tests for a complete scripted playthrough
- Add localization support

## Refactoring Details

See [`REFACTORING_NOTES.md`](REFACTORING_NOTES.md) for the full list of structural and behavioral changes.

## License

This project is available under the MIT License. See [`LICENSE`](LICENSE).
