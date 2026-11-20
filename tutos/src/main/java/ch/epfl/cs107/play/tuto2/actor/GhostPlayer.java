package ch.epfl.cs107.play.tuto2.actor;


import ch.epfl.cs107.play.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Button;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class GhostPlayer extends MovableAreaEntity {

    // ___ static variables ___
    static private final float BASE_HP = 10;
    private final static int ANIMATION_DURATION = 5;

    // ___member variables___
    private Sprite img; // // graphical representation of the actor
    private float hp; // level of energy (health points)
    private final TextGraphics hpText; // print of energyLvl

    // ___ CONSTRUCTORS ___
    public GhostPlayer(Area owner, Orientation orientation, DiscreteCoordinates position, String sprite) {
        super(owner, orientation, position);

        // build Sprite attribute
        img = new Sprite(sprite, 1f, 1f, this);

        // assign energy level
        hp = BASE_HP;

        // assign hpText
        hpText = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
        hpText.setParent(this); // text must follow SimpleGhost
        this.hpText.setAnchor(new Vector(-0.3f,0.1f)); // text offset for clean render
    }

    // ___ METHODS ___
    public boolean isWeak() {
        return (hp <= 0);
    }

    public void enterArea(Area area, DiscreteCoordinates position) {
        // register it in the new area and define this area as OwnerArea
        area.registerActor(this);
        this.setOwnerArea(area);

        // update position
        this.setCurrentPosition(position.toVector());

        // immobile
        resetMotion();

        // view
        area.setViewCandidate(this);
    }

    public void leaveArea() {
        // deregister from area
        this.getOwnerArea().unregisterActor(this);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return true; // GhostPlayer is solid
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {

    }

    @Override
    public void update(float deltaTime) {
        // update hp and its legend
        float new_hp = hp - deltaTime;
        if(new_hp < 0) {
            hp = 0;
        } else hp = new_hp;
        hpText.setText(Integer.toString((int) hp));

        // react to buttons
        Keyboard keyboard = getOwnerArea().getKeyboard();
        moveIfPressed(Orientation.LEFT,  keyboard.get(Keyboard.LEFT));
        moveIfPressed(Orientation.UP,    keyboard.get(Keyboard.UP));
        moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        moveIfPressed(Orientation.DOWN,  keyboard.get(Keyboard.DOWN));

        // super update
        super.update(deltaTime);
    }

    private void moveIfPressed(Orientation orientation, Button b) {
        if(b.isDown()) {
            // if already facing `orientation` → move(...)
            if(this.getOrientation().equals(orientation)) move(ANIMATION_DURATION);
            else orientate(orientation);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        img.draw(canvas);    // draw img
        hpText.draw(canvas); // draw hpText
    }

    public void strengthen() {
        hp = BASE_HP;
    }
}