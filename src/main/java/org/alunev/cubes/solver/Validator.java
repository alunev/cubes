package org.alunev.cubes.solver;

import org.alunev.cubes.model.CubeConstraints;
import org.alunev.cubes.model.FigureEdge;
import org.alunev.cubes.model.Piece;
import org.alunev.cubes.model.position.FigureVertex;
import org.alunev.cubes.model.position.PlaceEdge;
import org.alunev.cubes.model.position.PlaceVertex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validator {
    private static final Logger log = LoggerFactory.getLogger(Validator.class);

    private final Figure figure;


    public Validator(Figure figure) {
        this.figure = figure;
    }

    public boolean validateCubeConnections() {
        if (!validateCubeEdges()) return false;

        if (!validateCubeVertices()) return false;

        return true;
    }

    private boolean validateCubeEdges() {
        for (FigureEdge figureEdge : CubeConstraints.REQUIRED_CUBE_EDGES) {
            PlaceEdge placeEdge1 = figureEdge.getPlaceEdge1();
            PlaceEdge placeEdge2 = figureEdge.getPlaceEdge2();

            if (figure.isPlaceOccupied(placeEdge1.getPlace()) && figure.isPlaceOccupied(placeEdge2.getPlace())) {
                Piece positionedPiece1 = figure.getPositionedPiece(placeEdge1.getPlace());
                Piece positionedPiece2 = figure.getPositionedPiece(placeEdge2.getPlace());

                if (!positionedPiece1.connectsOnSidesTo(placeEdge1.getSide(), placeEdge2.getSide(), positionedPiece2)) {
                    log.trace("Pieces not matched: {} <-> {}:", placeEdge1, placeEdge2);
                    log.trace("\n{}", positionedPiece1);
                    log.trace("\n{}", positionedPiece2);

                    return false;
                }
            }
        }

        return true;
    }

    private boolean validateCubeVertices() {
        for (FigureVertex figureVertex : CubeConstraints.REQUIRED_CUBE_VERTICES) {
            if (!isCubeVertexOccupied(figureVertex)) {
                continue;
            }

            int sum = 0;
            for (PlaceVertex placeVertex : figureVertex.getVertexList()) {
                sum += getPlaceVertexValue(placeVertex);
            }

            if (sum != 1) {
                return false;
            }
        }

        return true;
    }

    private boolean isCubeVertexOccupied(FigureVertex figureVertex) {
        for (PlaceVertex placeVertex : figureVertex.getVertexList()) {
            if (!figure.isPlaceOccupied(placeVertex.getPlace())) {
                return false;
            }
        }

        return true;
    }

    private byte getPlaceVertexValue(PlaceVertex placeVertex) {
        Piece positionedPiece1 = figure.getPositionedPiece(placeVertex.getPlace());
        return positionedPiece1.getVertex(placeVertex.getVertexNum());
    }

}
