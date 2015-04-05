package org.alunev.cubes.manipulation;


import org.alunev.cubes.model.Piece;
import org.alunev.cubes.model.position.Angle;
import org.alunev.cubes.model.position.Direction;
import org.alunev.cubes.model.position.FlipSide;


public class PieceTransformer {
    private Piece piece;

    public PieceTransformer(Piece piece) {
        this.piece = piece;
    }

    public Piece result() {
        return piece;
    }

    public PieceTransformer rotateClockwise(Angle angle) {
        return rotate(angle, Direction.CLOCKWISE);
    }

    public PieceTransformer flip(FlipSide flipSide) {
        if (flipSide == FlipSide.ORIGINAL) {
            return this;
        }

        int length = piece.getEncoding().length;

        byte[][] flippedEncoding = new byte[length][length];

        int maxIndex = length - 1;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                flippedEncoding[i][maxIndex - j] = piece.getEncoding()[i][j];
            }
        }

        piece = new Piece(piece.getId(), flippedEncoding);

        return this;
    }

    public PieceTransformer flipAndRotate(FlipSide flipSide, Angle angle) {
        return flip(flipSide).rotateClockwise(angle);
    }

    private PieceTransformer rotate(Angle angle, Direction direction) {
        int length = piece.getEncoding().length;

        byte[][] rotatedEncoding = new byte[length][length];

        int maxIndex = length - 1;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (angle == Angle.ANGLE_0) {
                    rotatedEncoding[i][j] = piece.getEncoding()[i][j];
                } else if (direction == Direction.CLOCKWISE && angle == Angle.ANGLE_90
                        || direction == Direction.COUNTER_CLOCKWISE && angle == Angle.ANGLE_270) {
                    rotatedEncoding[j][maxIndex - i] = piece.getEncoding()[i][j];
                } else if (direction == Direction.CLOCKWISE && angle == Angle.ANGLE_180
                        || direction == Direction.COUNTER_CLOCKWISE && angle == Angle.ANGLE_180) {
                    rotatedEncoding[maxIndex - i][maxIndex - j] = piece.getEncoding()[i][j];
                } else if (direction == Direction.CLOCKWISE && angle == Angle.ANGLE_270
                        || direction == Direction.COUNTER_CLOCKWISE && angle == Angle.ANGLE_90) {
                    rotatedEncoding[maxIndex - j][i] = piece.getEncoding()[i][j];
                }
            }
        }

        piece = new Piece(piece.getId(), rotatedEncoding);

        return this;
    }
}
