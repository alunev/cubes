package org.alunev.cubes.solver;

import org.alunev.cubes.model.Piece;
import org.alunev.cubes.model.position.Angle;
import org.alunev.cubes.model.position.FlipSide;
import org.alunev.cubes.model.position.Place;
import org.alunev.cubes.model.position.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Makes up all possible unique figures out of passed pieces.
 */
public class CubeSolver {
    private static final Logger log = LoggerFactory.getLogger(CubeSolver.class);

    private final List<Piece> sourcePieces;

    private final List<Figure> figures = new ArrayList<>();

    private long start;
    private long iterationCount = 0;
    private long treeEndsCount = 0;

    public CubeSolver(List<Piece> sourcePieces) {
        this.sourcePieces = sourcePieces;
    }

    public List<Figure> solve() {
        log.info("Start solving...");

        start = System.currentTimeMillis();

        Figure figure = new Figure();

        // put zero piece on zero place always
//        List<Position> positions = figure.getPositionsForPlace(Place.PLACE_0);
        Figure cubeWithZeroPiece = figure.createCubeWithNextPiece(
                sourcePieces.get(0),
                new Position(Place.PLACE_0, FlipSide.ORIGINAL, Angle.ANGLE_0)
        );
        figures.addAll(getCompleteSolutionsFor(cubeWithZeroPiece));

        log.info("Solving complete in {} ms", System.currentTimeMillis() - start);
        logStats();

        return figures;
    }

    private void logStats() {
        long end = System.currentTimeMillis();

        log.info("Solutions found: {}", figures.size());
        log.info("Iterations performed: {}", iterationCount);
        log.info("Time elapsed: {} ms", (end - start));
        log.info("Avg time per iteration: {} ms", (end - start) * 1.0 / iterationCount);
        log.info("Search tree ends count: {}", treeEndsCount);
    }

    private Set<Figure> getCompleteSolutionsFor(Figure incompleteFigure) {
        iterationCount++;

        Set<Figure> figures = new HashSet<>();
        List<Piece> remainingPieces = incompleteFigure.getRemainingPieces(sourcePieces);

        for (Position position : incompleteFigure.getNextVacantPositions()) {
            for (Piece piece : remainingPieces) {
                Figure newFigure = incompleteFigure.createCubeWithNextPiece(piece, position);

                if (new Validator(newFigure).validateCubeConnections()) {
                    if (newFigure.isComplete()) {
                        log.debug("Found complete solution: " + newFigure);

                        figures.add(newFigure);

                        return figures;
                    } else {
                        log.trace("Processing incomplete solution with right connections:");
                        log.trace("{}", newFigure);

                        figures.addAll(getCompleteSolutionsFor(newFigure));
                    }
                } else {
                    treeEndsCount++;
                }
            }
        }

        return figures;
    }
}
