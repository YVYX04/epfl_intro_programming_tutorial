package ch.epfl.cs107.play.tuto1;

import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.tuto1.actor.SimpleGhost;
import ch.epfl.cs107.play.tuto1.area.maps.Ferme;
import ch.epfl.cs107.play.tuto1.area.maps.Village;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Tuto1 extends AreaGame {

    // ___ member variables ___
    private SimpleGhost player;

    // ___ AREA MANAGEMENT ___
    private void createAreas() {
        // add the relevant scenes
        this.addArea(new Ferme());
        this.addArea(new Village());
    }

    private void switchArea() {

        // deregister from current area
        getCurrentArea().unregisterActor(player);

        if(getCurrentArea().getTitle().equals("zelda/Ferme")) {
            // 1. switch area
            setCurrentArea("zelda/Village", true);
        } else {
            setCurrentArea("zelda/Ferme", true);
        }

        // 2. register in this new area
        getCurrentArea().registerActor(player);
        // 3. strengthen
        player.strengthen();
        // 4. set camera on player
        getCurrentArea().setViewCandidate(player);
    }

    // ___ OVERRIDE ___
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if(super.begin(window, fileSystem)) {
            // assign player
            player = new SimpleGhost(new Vector(18, 7), "ghost.1");

            // create the areas
            createAreas();
            setCurrentArea("zelda/Village", true).registerActor(player);

            // set camera
            getCurrentArea().setViewCandidate(player);

            return true ;
        }
        else return false ;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // listen to keyboard
        Keyboard keyboard = getWindow().getKeyboard();
        Button keyUp = keyboard.get(Keyboard.UP);
        Button keyDown = keyboard.get(Keyboard.DOWN);
        Button keyLeft = keyboard.get(Keyboard.LEFT);
        Button keyRight = keyboard.get(Keyboard.RIGHT);

        // action movement
        if(keyUp.isDown()) player.moveUp();
        if(keyDown.isDown()) player.moveDown();
        if(keyLeft.isDown()) player.moveLeft();
        if(keyRight.isDown()) player.moveRight();

        // check if the player should be changed of area
        if(player.isWeak()) switchArea();
    }

    @Override
    public void end() {
        // TODO save the game states somewhere
    }

    @Override
    public String getTitle() {
        return "Tuto1";
    }


}