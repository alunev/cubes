package org.alunev.cubes.solver;

import org.alunev.cubes.InputCubes;
import org.alunev.cubes.io.CubePrinter;
import org.alunev.cubes.model.*;
import org.alunev.cubes.model.position.Angle;
import org.alunev.cubes.model.position.FlipSide;
import org.alunev.cubes.model.position.Place;
import org.alunev.cubes.model.position.Position;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FigureTest {
    private final static Logger log = LoggerFactory.getLogger(FigureTest.class);

    private PieceHeap referencePieceHeap;
    private Figure referenceFigure;

    @Before
    public void setUp() throws Exception {
        referencePieceHeap = InputCubes.createBlueCube();
        referenceFigure = InputCubes.createSingleBlueSolution();
    }

    @Test
    public void checkVacantPositionsWork() {
        List<Piece> pieces = referencePieceHeap.getPieces();

        Figure figure = new Figure();
        figure = figure.createCubeWithNextPiece(pieces.get(0), new Position(Place.PLACE_0, FlipSide.ORIGINAL, Angle.ANGLE_0)
        );

        assertThat(figure.getVacantPositions().size(), is(40));
    }

    @Test
    public void checkRemainingPiecesWork() {
        List<Piece> pieces = referencePieceHeap.getPieces();

        Figure figure = new Figure();
        figure = figure.createCubeWithNextPiece(pieces.get(0), new Position(Place.PLACE_0, FlipSide.ORIGINAL, Angle.ANGLE_0)
        );

        assertThat(figure.getRemainingPieces(pieces).size(), is(5));
    }

    @Test
    public void isCompleteForReferenceSolution() {
        log.info(new CubePrinter().printCubeToString(referenceFigure));

        assertThat(referenceFigure.isComplete(), is(true));
    }

    @Test
    public void requiredConnectionsNotMatchedForWrongPartialSolution() {
        Figure figure = new Figure().createCubeWithNextPiece(referencePieceHeap.getPieces().get(1),
                                                       new Position(Place.PLACE_0, FlipSide.ORIGINAL, Angle.ANGLE_0
                                                       )
        ).createCubeWithNextPiece(referencePieceHeap.getPieces().get(2),
                                  new Position(Place.PLACE_1, FlipSide.ORIGINAL, Angle.ANGLE_180)
        ).createCubeWithNextPiece(referencePieceHeap.getPieces().get(0),
                                  new Position(Place.PLACE_2, FlipSide.ORIGINAL, Angle.ANGLE_0)
        ).createCubeWithNextPiece(referencePieceHeap.getPieces().get(5),
                                  new Position(Place.PLACE_3, FlipSide.OTHER, Angle.ANGLE_180)
        );

        log.info(new CubePrinter().printCubeToString(figure));

        assertThat(new Validator(figure).validateCubeConnections(), is(false));
    }
}