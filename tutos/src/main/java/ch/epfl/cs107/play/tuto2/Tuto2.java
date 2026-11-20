package ch.epfl.cs107.play.tuto2;

import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.tuto1.actor.SimpleGhost;
import ch.epfl.cs107.play.tuto2.actor.GhostPlayer;
import ch.epfl.cs107.play.tuto2.area.maps.Ferme;
import ch.epfl.cs107.play.tuto2.area.maps.Village;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Tuto2 extends AreaGame {

    // ___ member variables ___
    private GhostPlayer player;

    // ___ AREA MANAGEMENT ___
    private void createAreas() {
        // add the relevant scenes
        this.addArea(new Ferme());
        this.addArea(new Village());
    }

    private void switchArea() {

        // leave current area
        player.leaveArea();

        // enter new area
        if(getCurrentArea().getTitle().equals("zelda/Ferme")) {
            setCurrentArea("zelda/Village", true);
            player.enterArea(getCurrentArea(), new DiscreteCoordinates(5, 15));
        } else {
            setCurrentArea("zelda/Ferme", true);
            player.enterArea(getCurrentArea(), new DiscreteCoordinates(2, 10));
        }

        // strengthen player
        player.strengthen();
    }

    // ___ OVERRIDE ___
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if(super.begin(window, fileSystem)) {
            // create the areas
            createAreas();
            Area area = setCurrentArea("zelda/Ferme", true);

            // assign player
            DiscreteCoordinates start = new DiscreteCoordinates(2, 10);
            player = new GhostPlayer(getCurrentArea(), Orientation.DOWN, start, "ghost.1");

            // enter area
            player.enterArea(area, start);

            return true;
        }
        else return false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(player.isWeak()) switchArea();
    }

    @Override
    public void end() {
        // TODO save the game states somewhere
    }

    @Override
    public String getTitle() {
        return "Tuto2";
    }


}