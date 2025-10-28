# FPS-RPG Project Report

Author: Aryan Akhare , Chaitanya Zunzurkar and Himanshu Kanjwani.

## 1. Project overview

This project is a small CLI-based FPS-inspired RPG demonstrating three design patterns from the syllabus:

- Creational: Factory Method (player & enemy creation)
- Structural: Decorator (weapon attachments)
- Behavioral: Strategy (attack behaviors)

The demo lives under `fps_rpg/` and is intentionally compact to make the design patterns clear for educational / assignment use.

## 2. Goals and scope

- Provide a runnable demo that compiles with `javac` and runs with `java`.
- Clean separation of responsibilities by package: `creational`, `structural`, `behavioral`, `game`.
- Provide diagrams and explanations suited for a course report.

## 3. Folder structure

fps_rpg/

- Main.java — entrypoint (package `fps_rpg`)
- game/
  - GameManager.java — top-level flow: create player, equip weapon, run encounters
  - CombatSystem.java — combat loop and action selection
- creational/
  - CharacterFactory.java — Factory Method
  - PlayerCharacter.java, Assault.java, Sniper.java, Engineer.java — player model
  - Enemy.java — enemy model
- structural/
  - Weapon.java — component interface
  - BaseRifle.java — concrete component
  - WeaponDecorator.java — abstract decorator
  - ScopeAttachment.java, ExtendedMagAttachment.java, SilencerAttachment.java — concrete decorators
- behavioral/
  - AttackStrategy.java — Strategy interface
  - HipFireStrategy.java, AimStrategy.java, SuppressiveStrategy.java — concrete strategies
- report/
  - REPORT.md (this file)
  - diagrams/
    - class_diagram.png
    - sequence_combat.png
  

## 4. Design pattern mapping and contracts

### Factory Method (Creational)

- Class: `CharacterFactory`
- Responsibility: centralize object creation for `PlayerCharacter` and `Enemy`.
- Contract: `createPlayer(String type, String name) -> PlayerCharacter`; `createEnemy(String type) -> Enemy`.
- Rationale: callers (GameManager) depend on abstractions, not concrete types. Adding a new player class only requires adding a class and a factory branch.

### Decorator (Structural)

- Component interface: `Weapon`
- Concrete component: `BaseRifle`
- Decorator base: `WeaponDecorator`
- Concrete decorators: `ScopeAttachment`, `ExtendedMagAttachment`, `SilencerAttachment`
- Contract: Decorators implement `Weapon` and wrap another `Weapon` instance. `getBonusDamage()`, `getBonusAccuracy()` etc. compose across wrappers.

### Strategy (Behavioral)

- Context: `CombatSystem` (and player chooses which strategy to use)
- Strategy interface: `AttackStrategy` with `boolean attack(PlayerCharacter, Enemy)`
- Concrete strategies: `HipFireStrategy`, `AimStrategy`, `SuppressiveStrategy`
- Contract: Strategies encapsulate alternative attack behaviors; `CombatSystem` uses the interface and can switch strategies at runtime.

## 5. UML diagrams

PlantUML sources are included under `report/diagrams/`:

- `class_diagram.png` — class relationships for the 3 patterns (Factory classes, Weapon decorator tree, Strategy interfaces)
- `sequence_combat.png` — sequence diagram showing GameManager -> CombatSystem -> Player / Strategy / Enemy interactions during a combat turn


## 6. Example outputs and notes

- The code compiles with `javac` and runs with `java -cp bin fps_rpg.Main`.
- The report includes diagrams to illustrate the design decisions.

## 7. Appendix — quick commands

Compile project (from workspace root):

```powershell
if (!(Test-Path bin)) { New-Item -ItemType Directory -Path bin }
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object {$_.FullName}
javac -d bin $files
java -cp bin fps_rpg.Main
```

--- End of report
