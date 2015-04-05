package org.alunev.cubes.model;

import org.alunev.cubes.manipulation.PieceTransformer;
import org.alunev.cubes.model.position.Angle;
import org.alunev.cubes.model.position.FlipSide;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class PieceTest {

    private Piece diagonalPiece;

    @Before
    public void setUp() throws Exception {
        diagonalPiece = new Piece(0, new byte[][]{
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
        });
    }

    @Test
    public void testGetSide() throws Exception {
        Piece piece = new Piece(1, new byte[][]{
                {0, 1, 0, 1, 1},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 0, 1, 0},
        });

        assertArrayEquals(new byte[]{0, 1, 0, 1, 1}, piece.getSide(Side.TOP));
        assertArrayEquals(new byte[]{1, 1, 0, 1, 0}, piece.getSide(Side.RIGHT));
        assertArrayEquals(new byte[]{0, 1, 0, 1, 1}, piece.getSide(Side.BOTTOM));
        assertArrayEquals(new byte[]{1, 1, 1, 0, 0}, piece.getSide(Side.LEFT));
    }

    @Test
    public void testConnectsOnSideTo() throws Exception {
        Piece pieceOne = new Piece(0, new byte[][]{
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 1, 0, 1, 0},
        });

        Piece pieceTwo = new Piece(1, new byte[][]{
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 0, 1, 0, 1},
        });

        assertTrue(pieceOne.connectsOnSidesTo(Side.TOP, Side.BOTTOM, pieceTwo));
        assertTrue(pieceOne.connectsOnSidesTo(Side.RIGHT, Side.BOTTOM, pieceTwo));
    }

    @Test
    public void testRotateClockwise90() throws Exception {
        assertThat(new PieceTransformer(diagonalPiece).rotateClockwise(Angle.ANGLE_90).result(),
                is(equalTo(new Piece(0, new byte[][]{
                                                                                     {1, 1, 1, 1, 1},
                                                                                     {1, 1, 1, 1, 0},
                                                                                     {1, 1, 1, 0, 0},
                                                                                     {1, 1, 0, 0, 0},
                                                                                     {1, 0, 0, 0, 0},
                                                                             }
                                                                             )
                                                                     )
                   )
        );
    }

    @Test
    public void testRotateClockwise180() throws Exception {
        assertThat(new PieceTransformer(diagonalPiece).rotateClockwise(Angle.ANGLE_180).result(), is(equalTo(
                                                                             new Piece(0, new byte[][]{
                                                                                     {1, 1, 1, 1, 1},
                                                                                     {0, 1, 1, 1, 1},
                                                                                     {0, 0, 1, 1, 1},
                                                                                     {0, 0, 0, 1, 1},
                                                                                     {0, 0, 0, 0, 1},
                                                                             }))
                   ));
    }

    @Test
    public void testRotateClockwise270() throws Exception {
        assertThat(new PieceTransformer(diagonalPiece).rotateClockwise(Angle.ANGLE_270).result()
                , is(equalTo(
                                                                              new Piece(0, new byte[][]{
                                                                                      {0, 0, 0, 0, 1},
                                                                                      {0, 0, 0, 1, 1},
                                                                                      {0, 0, 1, 1, 1},
                                                                                      {0, 1, 1, 1, 1},
                                                                                      {1, 1, 1, 1, 1},
                                                                              }))
                   ));
    }

    @Test
    public void flipCorrectly() throws Exception {
        Piece piece = new Piece(0, new byte[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 1, 0, 0},
                {1, 1, 1, 0, 0},
        });

        assertThat(new PieceTransformer(piece).flip(FlipSide.OTHER).result(),
                   is(equalTo(new Piece(0, new byte[][]{
                             {0, 0, 0, 0, 0},
                             {0, 0, 0, 0, 0},
                             {0, 0, 1, 0, 0},
                             {0, 0, 1, 1, 0},
                             {0, 0, 1, 1, 1},
                     }))
                   )
        );
    }
}
