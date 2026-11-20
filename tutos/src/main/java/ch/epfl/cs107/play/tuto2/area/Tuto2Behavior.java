package ch.epfl.cs107.play.tuto2.area;

import ch.epfl.cs107.play.areagame.area.AreaBehavior;
import ch.epfl.cs107.play.io.ResourcePath;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

public class Tuto2Behavior extends AreaBehavior {

    // ___ member variables ___
    public enum Tuto2CellType {
        NULL(0 , false),
        WALL(-16777216, false),
        IMPASSABLE(-8750470, false),
        INTERACT(-256, true),
        DOOR(-195580, true),
        WALKABLE(-1, true);

        final int type;
        final boolean isWalkable;

        Tuto2CellType(int type, boolean isWalkable) {
            this.type = type;
            this.isWalkable = isWalkable;
        }

        // ___ method of enum ___
        /**
         * Return the value of the enum type; e.g. toType(-195580) return DOOR.
         * @param type
         * @return
         */
        public static Tuto2CellType toType(int type) {
            for(Tuto2CellType t : Tuto2CellType.values()) {
                if(t.type == type) return t;
            }

            // if the type was not found, the default is:
            System.out.println("Unknown cell type: " + type);
            return NULL;
        }

    }

    // ___ CONSTRUCTORS ___
    /**
     * This constructor simply extracts the behaviorMap and then handle the grid fill to the next constructor
     * @param window
     * @param name
     */
    public Tuto2Behavior(Window window, String name) {
        this(window.getImage(ResourcePath.getBehavior(name), null, false));
    }

    public Tuto2Behavior(Image behaviorMap) {
        super(behaviorMap);

        // memory has been allocated for the grid, we need to assign the values of
        // each cell
        for(int x = 0; x < behaviorMap.getWidth(); ++x) {
            for(int y = 0; y < behaviorMap.getHeight(); ++y) {
                Tuto2CellType cellType = Tuto2CellType.toType(getRGB(getHeight() - 1 - y, x));
                setCell(x, y, new Tuto2Cell(x, y, cellType));
            }
        }
    }

}