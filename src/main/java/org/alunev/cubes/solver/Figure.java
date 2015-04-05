package org.alunev.cubes.solver;

import org.alunev.cubes.CubesConstants;
import org.alunev.cubes.io.CubePrinter;
import org.alunev.cubes.manipulation.PieceTransformer;
import org.alunev.cubes.model.Piece;
import org.alunev.cubes.model.position.Angle;
import org.alunev.cubes.model.position.FlipSide;
import org.alunev.cubes.model.position.Place;
import org.alunev.cubes.model.position.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Figure {
    private static final Logger log = LoggerFactory.getLogger(Figure.class);

    private final Map<Place, Piece> placePieceMap = new HashMap<>();
    private final Map<Piece, Position> piecePositionMap = new HashMap<>();

    public Figure() {

    }

    private Figure(Figure figure) {
        this.placePieceMap.putAll(figure.getPlacePieceMap());
        this.piecePositionMap.putAll(figure.getPiecePositionMap());
    }

    public Map<Place, Piece> getPlacePieceMap() {
        return placePieceMap;
    }

    public Map<Piece, Position> getPiecePositionMap() {
        return piecePositionMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Figure figure = (Figure) o;

        InvariantGenerator thisGenerator = new InvariantGenerator(this);
        InvariantGenerator otherGenerator = new InvariantGenerator(figure);

        List<Integer> thisInvariant = thisGenerator.generate();
        List<Integer> otherInvariant = otherGenerator.generate();

        if (thisInvariant.equals(otherInvariant)) {
            return true;
        }

        return false;
    }

    /**
     * PieceHeap invariant is in pair mapping:
     * 0 - 4
     * 1 - 2
     * 3 - 5
     * <p/>
     * Here we construct unique number which preserves invariant.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(new InvariantGenerator(this).generate());
    }

    public boolean isComplete() {
        return placePieceMap.size() == CubesConstants.PIECES_COUNT;
    }

    public boolean isPlaceOccupied(Place place) {
        return placePieceMap.containsKey(place);
    }

    public Figure createCubeWithNextPiece(Piece piece, Position position) {
        log.trace("Adding piece\n{} to {}", piece, position);

        Figure figure = new Figure(this);

        figure.getPlacePieceMap().put(position.getPlace(), piece);
        figure.getPiecePositionMap().put(piece, position);

        return figure;
    }


    public List<Piece> getRemainingPieces(List<Piece> sourcePieces) {
        List<Piece> pieces = new ArrayList<>();

        for (Piece piece : sourcePieces) {
            if (!containsPiece(piece)) {
                pieces.add(piece);
            }
        }

        return pieces;
    }

    public List<Position> getVacantPositions() {
        List<Position> positions = new ArrayList<>();

        for (Place place : Place.traverseOrder()) {
            if (!isPlaceOccupied(place)) {
                for (Angle angle : Angle.values()) {
                    for (FlipSide flipSide : FlipSide.values()) {
                        positions.add(new Position(place, flipSide, angle));
                    }
                }
            }
        }

        return positions;
    }

    public List<Position> getNextVacantPositions() {
        Place place = getNextVacantPlace();
        return getPositionsForPlace(place);
    }

    public List<Position> getPositionsForPlace(Place place) {
        List<Position> positions = new ArrayList<>();

        for (Angle angle : Angle.values()) {
            for (FlipSide flipSide : FlipSide.values()) {
                positions.add(new Position(place, flipSide, angle));
            }
        }

        return positions;
    }

    public Piece getPositionedPiece(Place place) {
        Piece piece = placePieceMap.get(place);

        if (piece == null) {
            return null;
        }

        Position position = piecePositionMap.get(piece);

        return new PieceTransformer(piece).flipAndRotate(position.getFlipSide(), position.getAngle()).result();
    }

    @Override
    public String toString() {
        return "Figure{count=" + getPiecePositionMap().size() + ",\n" +
                new CubePrinter().printCubeToString(this) + "\n" +
                "}";
    }

    private boolean containsPiece(Piece piece) {
        return placePieceMap.containsValue(piece);
    }

    private Place getNextVacantPlace() {
        for (Place place : Place.traverseOrder()) {
            if (!isPlaceOccupied(place)) {
                return place;
            }
        }

        return Place.NONE;
    }
}
