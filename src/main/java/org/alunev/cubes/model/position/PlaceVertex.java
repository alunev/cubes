package org.alunev.cubes.model.position;


public class PlaceVertex {
    private final Place place;

    /**
     *    0 -- 1
     *    |    |
     *    3 -- 2
     */
    private final int vertexNum;

    public PlaceVertex(Place place, int vertexNum) {
        this.place = place;
        this.vertexNum = vertexNum;
    }

    public Place getPlace() {
        return place;
    }

    public int getVertexNum() {
        return vertexNum;
    }

    @Override
    public String toString() {
        return "PlaceVertex{" +
                "place=" + place +
                ", vertexNum=" + vertexNum +
                '}';
    }
}
