/**
 * Represents a fox.
 *
 * @author Benjamin Blouin
 * @version August 30, 2019
 */

public class Rabbit extends Animal {
    private int directionToFox;
    private int distanceToFox;
    private boolean haveSeenFox = false;
    private int distanceToEdge;
    private int[] bestMoves = {5, 3, 6, 2, 7, 1, 4, 0};
    private int edgeAvoidanceFactor=1;

    /**
     * Constructs a rabbit in the given model, at the given position
     * in the field.
     *
     * @param model  the model that controls this rabbit.
     * @param row    the row of the field containing this rabbit.
     * @param column the column of the field containing this rabbit.
     */

    public Rabbit(Model model, int row, int column) {
        super(model, row, column);
    }

    /**
     * Controls the movement of the rabbit.
     *
     * @return the direction in which the rabbit wishes to move.
     */

    int decideMove() {

        haveSeenFox = false;
        for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) {
            if (look(i) == Model.FOX) {
                haveSeenFox = true;
                directionToFox = i;
                distanceToFox = distance(i);
            }
            if (look(i) == Model.EDGE) {
                distanceToEdge = i;
            }
        }

        if (haveSeenFox) {
            for (int t : bestMoves) {
                if (canMove(Model.turn(directionToFox, t)) && distanceToEdge > 1) {
                    return Model.turn(directionToFox, t);
                }
                else {
                    //edge detection; first section is looking to the right relative to fix sighting, second is left
                    for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) {
                        if (look(i) == Model.EDGE && distance(look(i)) < edgeAvoidanceFactor) {
                            if (look(Model.turn(i, 2)) == Model.EDGE && distance(look(Model.turn(i, 2))) < edgeAvoidanceFactor) {
                                if (canMove(Model.turn(i, 5))) return Model.turn(i, 5);
                                else if (canMove(Model.turn(i, 6))) return Model.turn(i, 6);
                                else return Model.turn(i, 4);
                            } else if (look(Model.turn(i, 6)) == Model.EDGE && distance(look(Model.turn(i, 6))) < edgeAvoidanceFactor) {
                                if (canMove(Model.turn(i, 3))) return Model.turn(i, 3);
                                else if (canMove(Model.turn(i, 2))) return Model.turn(i, 2);
                                else return Model.turn(i, 4);
                            }
                        }
                    }
                }
            }
        }
        // if all else fails, don't move, so you don't accidentally step into the fox's sight
        return Model.STAY;
    }
}