package ch.epfl.cs107.play.tuto2.area.maps;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.tuto2.area.TutoArea;

public class Ferme extends TutoArea {

    // ___ OVERRIDE ___
    @Override
    public void createArea() {
        // add background
        registerActor(new Background(this));
        registerActor(new Foreground(this));
    }

    @Override
    public String getTitle() {
        return "zelda/Ferme";
    }
}