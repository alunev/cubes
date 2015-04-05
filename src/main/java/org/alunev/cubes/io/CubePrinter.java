package org.alunev.cubes.io;

import org.alunev.cubes.manipulation.PieceTransformer;
import org.alunev.cubes.model.PieceHeap;
import org.alunev.cubes.CubesConstants;
import org.alunev.cubes.model.Piece;
import org.alunev.cubes.model.position.Place;
import org.alunev.cubes.model.position.Position;
import org.alunev.cubes.solver.Figure;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;


public class CubePrinter {

    public String printCubeToString(PieceHeap pieceHeap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Piece[][] printing = new Piece[1][6];
        pieceHeap.getPieces().toArray(printing[0]);

        char[][] chars = printPieceMatrixToChars(printing);
        printCharsToStream(new PrintStream(baos), chars);

        return new String(baos.toByteArray());
    }

    public String printCubeToString(Figure figure) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Piece[][] printing = getSolutionLayout(figure);

        char[][] chars = printPieceMatrixToChars(printing);

        PrintStream printStream = new PrintStream(baos);
        printStream.println();
        printCharsToStream(printStream, chars);

        return new String(baos.toByteArray());
    }

    public void printCharsToStream(PrintStream printStream, char[][] chars) {
        for (char[] row : chars) {
            for (char c : row) {
                printStream.print(c);
            }

            printStream.println();
        }
    }

    public String printPieceToString(Piece piece) {
        char[][] chars = new char[5][5];
        fillWithSpaces(chars);

        printPieceToChars(piece, chars, 0, 0);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        printCharsToStream(new PrintStream(out), chars);

        return out.toString();
    }

    private void printPieceToChars(Piece piece, char[][] chars, int x, int y) {
        byte[][] encoding = piece.getEncoding();

        for (int i = 0; i < encoding.length; i++) {
            for (int j = 0; j < encoding[0].length; j++) {
                if (encoding[i][j] == 1) {
                    chars[x * CubesConstants.SIZE + i][y * CubesConstants.SIZE + j] = CubesConstants.FILLED_CHAR;
                }
            }
        }
    }

    private void fillWithSpaces(char[][] output) {
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                output[i][j] = CubesConstants.EMPTY_CHAR;
            }
        }
    }

    private char[][] printPieceMatrixToChars(Piece[][] printing) {
        char[][] chars = new char[(printing.length + 1) * CubesConstants.SIZE][(printing[0].length + 1) * CubesConstants.SIZE];

        fillWithSpaces(chars);

        for (int x = 0; x < printing.length; x++) {
            for (int y = 0; y < printing[0].length; y++) {
                Piece piece = printing[x][y];

                if (piece != null) {
                    printPieceToChars(piece, chars, x, y);
                }
            }
        }

        return chars;
    }

    private Piece[][] getSolutionLayout(Figure figure) {
        Piece[][] printing = new Piece[4][3];

        Map<Piece, Position> piecePositionMap = figure.getPiecePositionMap();

        for (Map.Entry<Place, Piece> entry : figure.getPlacePieceMap().entrySet()) {
            Place place = entry.getKey();
            Piece piece = entry.getValue();
            Position position = piecePositionMap.get(piece);

            Piece rotatedPiece = new PieceTransformer(piece)
                    .flipAndRotate(position.getFlipSide(), position.getAngle())
                    .result();

            switch (place) {
                case PLACE_0:
                    printing[0][1] = rotatedPiece;
                    break;
                case PLACE_1:
                    printing[0][0] = rotatedPiece;
                    break;
                case PLACE_2:
                    printing[0][2] = rotatedPiece;
                    break;
                case PLACE_3:
                    printing[1][1] = rotatedPiece;
                    break;
                case PLACE_4:
                    printing[2][1] = rotatedPiece;
                    break;
                case PLACE_5:
                    printing[3][1] = rotatedPiece;
                    break;
            }
        }

        return printing;
    }
}
