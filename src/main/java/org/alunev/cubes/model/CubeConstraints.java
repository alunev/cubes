package org.alunev.cubes.model;

import org.alunev.cubes.model.position.FigureVertex;
import org.alunev.cubes.model.position.Place;
import org.alunev.cubes.model.position.PlaceEdge;
import org.alunev.cubes.model.position.PlaceVertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CubeConstraints {

    public static final List<FigureEdge> REQUIRED_CUBE_EDGES = new ArrayList<>();

    private static final Map<Place, Map<Side, PlaceEdge>> CUBE_PLACE_CONNECTIONS = new HashMap<>();

    static {
        // 12 edges in total

        addFigureEdge(Place.PLACE_0, Side.TOP, Place.PLACE_5, Side.BOTTOM);
        addFigureEdge(Place.PLACE_0, Side.LEFT, Place.PLACE_1, Side.RIGHT);
        addFigureEdge(Place.PLACE_0, Side.RIGHT, Place.PLACE_2, Side.LEFT);
        addFigureEdge(Place.PLACE_0, Side.BOTTOM, Place.PLACE_3, Side.TOP);

        addFigureEdge(Place.PLACE_1, Side.BOTTOM, Place.PLACE_3, Side.LEFT);
        addFigureEdge(Place.PLACE_1, Side.LEFT, Place.PLACE_4, Side.LEFT);
        addFigureEdge(Place.PLACE_1, Side.TOP, Place.PLACE_5, Side.LEFT);

        addFigureEdge(Place.PLACE_2, Side.BOTTOM, Place.PLACE_3, Side.RIGHT);
        addFigureEdge(Place.PLACE_2, Side.RIGHT, Place.PLACE_4, Side.RIGHT);
        addFigureEdge(Place.PLACE_2, Side.TOP, Place.PLACE_5, Side.RIGHT);

        addFigureEdge(Place.PLACE_3, Side.BOTTOM, Place.PLACE_4, Side.TOP);

        addFigureEdge(Place.PLACE_4, Side.BOTTOM, Place.PLACE_5, Side.TOP);
    }

    public static final List<FigureVertex> REQUIRED_CUBE_VERTICES = Arrays.asList(
            new FigureVertex(Arrays.asList(new PlaceVertex(Place.PLACE_0, 0), new PlaceVertex(Place.PLACE_1, 1), new PlaceVertex(Place.PLACE_5, 3))),
            new FigureVertex(Arrays.asList(new PlaceVertex(Place.PLACE_0, 3), new PlaceVertex(Place.PLACE_1, 2), new PlaceVertex(Place.PLACE_3, 0))),
            new FigureVertex(Arrays.asList(new PlaceVertex(Place.PLACE_0, 1), new PlaceVertex(Place.PLACE_2, 0), new PlaceVertex(Place.PLACE_5, 2))),
            new FigureVertex(Arrays.asList(new PlaceVertex(Place.PLACE_0, 2), new PlaceVertex(Place.PLACE_2, 3), new PlaceVertex(Place.PLACE_3, 1))),

            new FigureVertex(Arrays.asList(new PlaceVertex(Place.PLACE_4, 1), new PlaceVertex(Place.PLACE_3, 2), new PlaceVertex(Place.PLACE_2, 2))),
            new FigureVertex(Arrays.asList(new PlaceVertex(Place.PLACE_4, 0), new PlaceVertex(Place.PLACE_3, 3), new PlaceVertex(Place.PLACE_1, 3))),
            new FigureVertex(Arrays.asList(new PlaceVertex(Place.PLACE_4, 2), new PlaceVertex(Place.PLACE_5, 1), new PlaceVertex(Place.PLACE_2, 1))),
            new FigureVertex(Arrays.asList(new PlaceVertex(Place.PLACE_4, 3), new PlaceVertex(Place.PLACE_5, 0), new PlaceVertex(Place.PLACE_1, 0)))
    );

    private static void addFigureEdge(Place place1, Side side1, Place place2, Side side2) {
        PlaceEdge placeEdge2 = new PlaceEdge(place2, side2);
        PlaceEdge placeEdge1 = new PlaceEdge(place1, side1);

        REQUIRED_CUBE_EDGES.add(new FigureEdge(placeEdge1, placeEdge2));

        addPlaceConnection(place1, side1, placeEdge2);
        addPlaceConnection(place2, side2, placeEdge1);
    }

    private static void addPlaceConnection(Place place, Side placeSide, PlaceEdge otherPlaceEdge) {
        Map<Side, PlaceEdge> place1Connections = CUBE_PLACE_CONNECTIONS.get(place);
        if (place1Connections == null) {
            place1Connections = new HashMap<>();
            CUBE_PLACE_CONNECTIONS.put(place, place1Connections);
        }

        place1Connections.put(placeSide, otherPlaceEdge);
    }
}
