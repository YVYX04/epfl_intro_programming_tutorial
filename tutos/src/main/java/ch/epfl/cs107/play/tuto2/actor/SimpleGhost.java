package ch.epfl.cs107.play.tuto2.actor;
import ch.epfl.cs107.play.engine.actor.Entity;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

public class SimpleGhost extends Entity {
    // ___ static variables ___
    static private final float BASE_HP = 10;
    static private final double MOVEMENT_DISTANCE = 0.1;

    // ___member variables___
    Sprite img; // // graphical representation of the actor
    float hp; // level of energy (health points)
    private TextGraphics hpText; // print of energyLvl

    // ___ CONSTRUCTORS ___
    public SimpleGhost (Vector position , String spriteName ) {

        // super Entity
        super(position);

        // build Sprite attribute
        img = new Sprite(spriteName, 1f, 1f, this);

        // assign energy level
        hp = BASE_HP;

        // assign hpText
        hpText = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
        hpText.setParent(this); // text must follow SimpleGhost
        this.hpText.setAnchor(new Vector(-0.3f,0.1f)); // text offset for clean render

    }


    // ___ GETTERS ___
    /**
     * Return the energy level of this.
     * @return true if this.hp <= 0
     */
    public boolean isWeak() {
        return (hp <= 0);
    }

    // ___ SETTERS ___
    /**
     * Reset this.hp at 10
     */
    public void strengthen() {
        hp = BASE_HP;
    }

    // ___ MOVEMENTS ___
    public void moveUp() {
        setCurrentPosition(getPosition().add(0f, MOVEMENT_DISTANCE));
    }

    public void moveDown() {
        setCurrentPosition(getPosition().add(0f, -MOVEMENT_DISTANCE));
    }

    public void moveLeft() {
        setCurrentPosition(getPosition().add(-MOVEMENT_DISTANCE, 0f));
    }

    public void moveRight() {
        setCurrentPosition(getPosition().add(MOVEMENT_DISTANCE, 0f));
    }
    // ___ OVERRIDE ___
    @Override
    public void draw(Canvas canvas) {
        img.draw(canvas);    // draw img
        hpText.draw(canvas); // draw hpText
    }

    @Override
    /**
     * By default, the hp level cannot drop below 0.
     */
    public void update(float deltaTime) {
        float new_hp = hp - deltaTime;
        if(new_hp < 0) {
            hp = 0;
        } else hp = new_hp;

        // update hpText
        hpText.setText(Integer.toString((int) hp));
    }
}