package ch.epfl.cs107.play.tuto2.area;

import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Cell;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.tuto2.area.Tuto2Behavior.Tuto2CellType;

public class Tuto2Cell extends Cell {

    // ___ member variables ___
    private final Tuto2CellType type;

    // ___ CONSTRUCTORS ___
    Tuto2Cell(int x, int y, Tuto2CellType type) {
        super(x, y);
        this.type = type;
    }


    @Override
    protected boolean canLeave(Interactable entity) {
        return true;
    }

    @Override
    protected boolean canEnter(Interactable entity) {
        // the cell type indicate if it is walkable
        return type.isWalkable;
    }

    @Override
    public boolean isCellInteractable() {
        // accept contact interaction
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {

    }
}