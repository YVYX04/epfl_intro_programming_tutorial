<div align="center">

# 🎮 CS-107 · Game Engine Tutorial

**EPFL · Introduction to Programming · Year I, Semester I**

*Learning object-oriented Java by building a small 2D grid-based game on top of the course's `play` engine.*

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apachemaven&logoColor=white)
![EPFL](https://img.shields.io/badge/EPFL-CS--107-FF0000?style=flat)

</div>

---

## 📖 Overview

This repository follows the CS-107 game-engine tutorial (`tuto_26.pdf`). Step by step, we extend the provided engine to create a playable game: areas (maps), actors, keyboard input and switching between scenes.

## 🗂️ Structure

| Module | Contents |
|---|---|
| `game-engine/` | The course-provided engine (`ch.epfl.cs107.play`): window, rendering, areas, actors, I/O |
| `tutos/` | Our tutorial code and resources (sprites, backgrounds, fonts) |
| `tutos/.../tuto1/` | **Tuto 1**: `Tuto1` game, `SimpleGhost` player, `Ferme` & `Village` areas |

## 🚀 Run

Open the root `pom.xml` in IntelliJ IDEA and run `ch.epfl.cs107.play.Play` (module `tutos`).

## 🕹️ Tuto 1 at a glance

- Two areas, **Ferme** and **Village**, built on `SimpleArea`
- A `SimpleGhost` player moved with the arrow keys
- Switching between areas transfers the player and *strengthens* it
