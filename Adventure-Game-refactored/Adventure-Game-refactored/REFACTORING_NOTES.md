# Refactoring Notes

This document summarizes the most important changes made to the original project.

## Structural changes

- Replaced the Eclipse-specific layout with the standard Maven directory structure.
- Replaced the generic `test` package with focused `game`, `io`, `location`, and `model` packages.
- Removed committed `.class` files and Eclipse metadata.
- Added Maven configuration, JUnit 5 tests, `.gitignore`, and an MIT license.

## Readability improvements

- Replaced abbreviated field names such as `inv`, `cName`, `rHealthy`, `wName`, and `aName` with descriptive names.
- Replaced string-based equipment and reward checks with enums.
- Renamed the enemy classes to `Zombie`, `Vampire`, and `Bear` through reusable `Enemy` instances.
- Centralized all input handling in `ConsoleIO` instead of opening several scanners on `System.in`.
- Added English Javadoc only where it explains design intent or non-obvious behavior.

## Correctness fixes

- The player name entered at startup is now stored instead of being replaced with a hard-coded value.
- The victory check now uses `instanceof SafeHouse`; the previous package-qualified class-name comparison could never succeed.
- Location selection now accepts only values from 1 through 5.
- Character statistics shown in the menu now match the actual initialized statistics.
- Cave, forest, and river descriptions now match their actual enemy types and rewards.
- A weapon can now be purchased when the player has exactly the required amount of money.
- Medium armor now costs 25, matching the displayed store price.
- Corrected the rifle name.
- Armor can no longer turn incoming damage into accidental healing.
- Health values are clamped at zero, and enemy health is reset consistently between encounters.
- Invalid text entered into numeric menus no longer crashes the game.

## Deliberately preserved behavior

- The game remains a console application.
- The player chooses one of three character templates.
- Each battle location contains a random number of enemies.
- The player attacks first during combat.
- Visiting the Safe House restores all health.
- Food, water, and firewood are still required to win.
- The tool store still allows one equipment purchase per visit.
