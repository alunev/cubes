package org.alunev.cubes;

import org.alunev.cubes.model.*;
import org.alunev.cubes.model.position.Angle;
import org.alunev.cubes.model.position.FlipSide;
import org.alunev.cubes.model.position.Place;
import org.alunev.cubes.model.position.Position;
import org.alunev.cubes.solver.*;

import java.util.ArrayList;
import java.util.List;


public class InputCubes {
    public static PieceHeap createBlueCube() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Piece(0, new byte[][]{
                           {0, 0, 1, 0, 0},
                           {0, 1, 1, 1, 0},
                           {1, 1, 1, 1, 1},
                           {0, 1, 1, 1, 0},
                           {0, 0, 1, 0, 0},
                   }));
        pieces.add(new Piece(1, new byte[][]{
                           {1, 0, 1, 0, 1},
                           {1, 1, 1, 1, 1},
                           {0, 1, 1, 1, 0},
                           {1, 1, 1, 1, 1},
                           {1, 0, 1, 0, 1},
                   }));
        pieces.add(new Piece(2, new byte[][]{
                           {0, 0, 1, 0, 0},
                           {0, 1, 1, 1, 1},
                           {1, 1, 1, 1, 0},
                           {0, 1, 1, 1, 1},
                           {0, 0, 1, 0, 0},
                   }));
        pieces.add(new Piece(3, new byte[][]{
                           {0, 1, 0, 1, 0},
                           {1, 1, 1, 1, 0},
                           {0, 1, 1, 1, 1},
                           {1, 1, 1, 1, 0},
                           {1, 1, 0, 1, 0},
                   }));
        pieces.add(new Piece(4, new byte[][]{
                           {0, 1, 0, 1, 0},
                           {1, 1, 1, 1, 1},
                           {0, 1, 1, 1, 0},
                           {1, 1, 1, 1, 1},
                           {1, 0, 1, 0, 0},
                   }));
        pieces.add(new Piece(5, new byte[][]{
                           {0, 1, 0, 1, 0},
                           {0, 1, 1, 1, 1},
                           {1, 1, 1, 1, 0},
                           {0, 1, 1, 1, 1},
                           {1, 1, 0, 1, 1},
                   }));

        return new PieceHeap(pieces);
    }

    public static Figure createSingleBlueSolution() {
        List<Piece> pieces = createBlueCube().getPieces();

        return new Figure().createCubeWithNextPiece(pieces.get(0),
                                                  new Position(Place.PLACE_1, FlipSide.ORIGINAL, Angle.ANGLE_0
                                                  )
        ).createCubeWithNextPiece(pieces.get(1), new Position(Place.PLACE_4, FlipSide.ORIGINAL, Angle.ANGLE_0)
        ).createCubeWithNextPiece(pieces.get(2), new Position(Place.PLACE_2, FlipSide.ORIGINAL, Angle.ANGLE_270)
        ).createCubeWithNextPiece(pieces.get(3), new Position(Place.PLACE_5, FlipSide.ORIGINAL, Angle.ANGLE_0)
        ).createCubeWithNextPiece(pieces.get(4), new Position(Place.PLACE_3, FlipSide.OTHER, Angle.ANGLE_180)
        ).createCubeWithNextPiece(pieces.get(5), new Position(Place.PLACE_0, FlipSide.OTHER, Angle.ANGLE_270));
    }

    public static PieceHeap createRedCube() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Piece(0, new byte[][]{
                {0, 0, 0, 1, 1},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 0},
                {0, 1, 0, 1, 1},
        }));
        pieces.add(new Piece(1, new byte[][]{
                {0, 1, 0, 1, 0},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0},
        }));
        pieces.add(new Piece(2, new byte[][]{
                {0, 1, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1},
        }));
        pieces.add(new Piece(3, new byte[][]{
                {0, 0, 1, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {0, 0, 1, 0, 0},
        }));
        pieces.add(new Piece(4, new byte[][]{
                {0, 0, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0},
        }));
        pieces.add(new Piece(5, new byte[][]{
                {0, 1, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 0},
                {1, 1, 0, 1, 1},
        }));

        return new PieceHeap(pieces);
    }

    public static PieceHeap createGreenCube() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Piece(0, new byte[][]{
                {0, 0, 1, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0},
        }));
        pieces.add(new Piece(1, new byte[][]{
                {0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0},
        }));
        pieces.add(new Piece(2, new byte[][]{
                {0, 0, 1, 0, 1},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0},
        }));
        pieces.add(new Piece(3, new byte[][]{
                {1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 0, 1, 0, 0},
        }));
        pieces.add(new Piece(4, new byte[][]{
                {0, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 1, 0, 1, 0},
        }));
        pieces.add(new Piece(5, new byte[][]{
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 0},
                {0, 1, 0, 1, 1},
        }));

        return new PieceHeap(pieces);
    }

    public static PieceHeap createPurpleCube() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Piece(0, new byte[][]{
                {1, 1, 0, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 1, 0, 0},
        }));
        pieces.add(new Piece(1, new byte[][]{
                {0, 0, 0, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 0},
                {0, 1, 0, 1, 0},
        }));
        pieces.add(new Piece(2, new byte[][]{
                {0, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {0, 0, 1, 0, 0},
        }));
        pieces.add(new Piece(3, new byte[][]{
                {1, 1, 0, 1, 1},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 0, 1, 0},
        }));
        pieces.add(new Piece(4, new byte[][]{
                {0, 0, 1, 0, 1},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 0, 1, 1, 0},
        }));
        pieces.add(new Piece(5, new byte[][]{
                {0, 1, 0, 1, 1},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
        }));

        return new PieceHeap(pieces);
    }
}
