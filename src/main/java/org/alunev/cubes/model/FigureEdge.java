package org.alunev.cubes.model;


import org.alunev.cubes.model.position.PlaceEdge;

public class FigureEdge {
    private final PlaceEdge placeEdge1;
    private final PlaceEdge placeEdge2;

    public FigureEdge(PlaceEdge placeEdge1, PlaceEdge placeEdge2) {
        this.placeEdge1 = placeEdge1;
        this.placeEdge2 = placeEdge2;
    }

    public PlaceEdge getPlaceEdge1() {
        return placeEdge1;
    }

    public PlaceEdge getPlaceEdge2() {
        return placeEdge2;
    }

    @Override
    public String toString() {
        return "FigureEdge{" +
                "placeEdge1=" + placeEdge1 +
                ", placeEdge2=" + placeEdge2 +
                '}';
    }
}
