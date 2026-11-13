package ch.epfl.cs107.play.tuto1.area.maps;
import ch.epfl.cs107.play.tuto1.actor.SimpleGhost;
import ch.epfl.cs107.play.tuto1.area.SimpleArea;
import ch.epfl.cs107.play.math.Vector;

public class Village extends SimpleArea {

    // ___ OVERRIDE ___
    @Override
    public void createArea() {
        // register a SimpleGhost
        registerActor(new SimpleGhost(new Vector(20,10),
                "ghost.2"));
    }

    @Override
    public String getTitle() {
        return "zelda/Village";
    }
}