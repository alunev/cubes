package org.alunev.cubes.model.position;

import java.util.Objects;


public class Position {
    /**
     * Place of piece in unfolded cube form:
     *     1 0 2
     *       3
     *       4
     *       5
     */
    private final Place place;

    /**
     * Flip is applied before rotation. Flip is applied from left to right(around vertical median).
     */
    private final FlipSide flipSide;

    /**
     * Clockwise rotation from initial state.
     */
    private final Angle angle;


    public Position(Place place, FlipSide flipSide, Angle angle) {
        this.place = place;
        this.flipSide = flipSide;
        this.angle = angle;
    }

    public Place getPlace() {
        return place;
    }

    public FlipSide getFlipSide() {
        return flipSide;
    }

    public Angle getAngle() {
        return angle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return Objects.equals(place, position.place) &&
                Objects.equals(angle, position.angle) &&
                Objects.equals(flipSide, position.flipSide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, angle, flipSide);
    }

    @Override
    public String toString() {
        return "Position{" +
                "place=" + place +
                ", angle=" + angle +
                ", flipSide=" + flipSide +
                '}';
    }
}
