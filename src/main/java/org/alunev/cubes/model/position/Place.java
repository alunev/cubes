package org.alunev.cubes.model.position;


import java.util.Arrays;
import java.util.List;

/**
 * Place of piece in unfolded cube form:
 *     1 0 2
 *       3
 *       4
 *       5
 *
 *  Place of piece in plain:
 *     0 1 2
 *     3 4 5
 *
 */
public enum Place {
    PLACE_0,
    PLACE_1,
    PLACE_2,
    PLACE_3,
    PLACE_4,
    PLACE_5,

    NONE;

    public static List<Place> traverseOrder() {
        return Arrays.asList(PLACE_0, PLACE_1, PLACE_2, PLACE_3, PLACE_4, PLACE_5);
    }
}
