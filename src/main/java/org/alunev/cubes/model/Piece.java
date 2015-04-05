package org.alunev.cubes.model;

import org.alunev.cubes.ByteUtils;
import org.alunev.cubes.CubesConstants;

import java.util.Objects;

public class Piece {

    private final int id;

    /**
     * x is at [1][2] :
     *
     *    0 1 2 3
     *    1   x
     *    2
     *    3
     *
     */
    private final byte[][] encoding;

    public Piece(int id, byte[][] encoding) {
        this.id = id;
        this.encoding = encoding;
    }

    public int getId() {
        return id;
    }

    public byte[][] getEncoding() {
        return encoding;
    }

    public byte[] getSide(Side side) {
        byte[] bytes = new byte[0];

        switch (side) {
            case TOP:
                bytes = encoding[0];
                break;
            case BOTTOM:
                bytes = ByteUtils.getReversedArray(encoding[CubesConstants.SIZE - 1]);
                break;
            case LEFT:
                bytes = new byte[CubesConstants.SIZE];
                for (int i = 0; i < CubesConstants.SIZE; i++) {
                    bytes[CubesConstants.SIZE - 1 - i] = encoding[i][0];
                }
                break;
            case RIGHT:
                bytes = new byte[CubesConstants.SIZE];
                for (int i = 0; i < CubesConstants.SIZE; i++) {
                    bytes[i] = encoding[i][CubesConstants.SIZE - 1];
                }
                break;
        }

        return bytes;
    }

    public byte getVertex(int v) {
        if (v == 0) {
            return encoding[0][0];
        } else if (v == 1) {
            return encoding[0][CubesConstants.SIZE  - 1];
        } else if (v == 2) {
            return encoding[CubesConstants.SIZE  - 1][CubesConstants.SIZE  - 1];
        } else if (v == 3) {
            return encoding[CubesConstants.SIZE  - 1][0];
        }

        return -1;
    }

    public boolean connectsOnSidesTo(Side thisSide, Side thatSide, Piece thatPiece) {
        byte[] thisBytes = getSide(thisSide);
        byte[] thatBytes = thatPiece.getSide(thatSide);

        // check all but the corners cause corner may be filled by any of 3 neighbour sides
        for (int i = 1; i < CubesConstants.SIZE - 1; i++) {
            if (thisBytes[i] == thatBytes[CubesConstants.SIZE - 1 - i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        byte[][] encoding = this.getEncoding();

        for (int i = 0; i < encoding.length; i++) {
            for (int j = 0; j < encoding[i].length; j++) {
                if (encoding[i][j] == 1) {
                    stringBuilder.append("o");
                } else {
                    stringBuilder.append(" ");
                }
            }

            if (i < encoding.length - 1) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (id != piece.getId()) {
            return false;
        }

        for (int i = 0; i < encoding.length; i++) {
            for (int j = 0; j < encoding[0].length; j++) {
                if (encoding[i][j] != piece.getEncoding()[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, encoding);
    }

}
