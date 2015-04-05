package org.alunev.cubes.model.position;

import java.util.List;


public class FigureVertex {
    private final List<PlaceVertex> vertexList;

    public FigureVertex(List<PlaceVertex> vertexList) {
        this.vertexList = vertexList;
    }

    public List<PlaceVertex> getVertexList() {
        return vertexList;
    }

    @Override
    public String toString() {
        return "FigureVertex{" +
                "vertexList=" + vertexList +
                '}';
    }
}
