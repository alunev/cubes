package org.alunev.cubes.solver;

import org.alunev.cubes.ByteUtils;
import org.alunev.cubes.model.CubeConstraints;
import org.alunev.cubes.model.FigureEdge;
import org.alunev.cubes.model.Piece;
import org.alunev.cubes.model.position.Place;
import org.alunev.cubes.model.position.PlaceEdge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class InvariantGenerator {

    private final Figure figure;

    public InvariantGenerator(Figure figure) {
        this.figure = figure;
    }

    /**
     * 12 Pairs of connected sides combined is a cube invariant.
     */
    public List<Integer> generate() {
        if (!figure.isComplete()) {
            throw new RuntimeException("Can't calculate invariant for incomplete figure: " + figure);
        }

        List<Integer> edgeCodes = new ArrayList<>();
        for (FigureEdge figureEdge : CubeConstraints.REQUIRED_CUBE_EDGES) {
            PlaceEdge placeEdge1 = figureEdge.getPlaceEdge1();
            PlaceEdge placeEdge2 = figureEdge.getPlaceEdge2();

            if (figure.isPlaceOccupied(placeEdge1.getPlace()) && figure.isPlaceOccupied(placeEdge2.getPlace())) {
                Piece positionedPiece1 = figure.getPositionedPiece(placeEdge1.getPlace());
                Piece positionedPiece2 = figure.getPositionedPiece(placeEdge2.getPlace());

                byte[] side1 = positionedPiece1.getSide(placeEdge1.getSide());
                byte[] side2 = positionedPiece2.getSide(placeEdge2.getSide());

                edgeCodes.add(getEdgeCode(side1, side2));
            }
        }

        Collections.sort(edgeCodes);

        return edgeCodes;
    }

    private static Integer getEdgeCode(byte[] side1, byte[] side2) {
        int int1 = sideToInt(side1);
        int int2 = sideToInt(side2);

        return (int1 < int2) ? (int1 * 100 + int2) : (int2 * 100 + int1);
    }

    private static int sideToInt(byte[] bytes) {
        int val = 0;
        for (int i = 0; i < bytes.length; i++) {
            val += bytes[i] << i;
        }

        return val;
    }
}
