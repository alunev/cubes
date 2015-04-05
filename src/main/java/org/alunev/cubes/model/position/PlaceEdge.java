package org.alunev.cubes.model.position;

import org.alunev.cubes.model.Side;

import java.util.Objects;


public class PlaceEdge {
    private final Place place;
    private final Side side;

    public PlaceEdge(Place place, Side side) {
        this.place = place;
        this.side = side;
    }

    public Place getPlace() {
        return place;
    }

    public Side getSide() {
        return side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PlaceEdge placeEdge = (PlaceEdge) o;

        return Objects.equals(place, placeEdge.place) && Objects.equals(side, placeEdge.side);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, side);
    }

    @Override
    public String toString() {
        return "PlaceEdge{" +
                "place=" + place +
                ", side=" + side +
                '}';
    }
}
