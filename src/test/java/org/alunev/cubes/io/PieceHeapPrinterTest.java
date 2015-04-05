package org.alunev.cubes.io;

import org.alunev.cubes.CubesConstants;
import org.alunev.cubes.InputCubes;
import org.alunev.cubes.model.Piece;
import org.alunev.cubes.model.PieceHeap;
import org.alunev.cubes.solver.Figure;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class PieceHeapPrinterTest {
    private final static Logger log = LoggerFactory.getLogger(PieceHeapPrinterTest.class);
    private Figure referenceFigure;

    @Before
    public void setUp() throws Exception {
        referenceFigure = InputCubes.createSingleBlueSolution();
    }

    @Test
    public void testPrintReferenceCubeToString() throws Exception {
        CubePrinter cubePrinter = new CubePrinter();

        log.info(cubePrinter.printCubeToString(InputCubes.createBlueCube()));
    }

    @Test
    public void testPrintReferenceSolutionToString() throws Exception {
        log.info(new CubePrinter().printCubeToString(referenceFigure));
    }

    @Test
    public void testPrintCharsToStream() throws Exception {
        CubePrinter cubePrinter = new CubePrinter();

        char f = CubesConstants.FILLED_CHAR;
        char e = CubesConstants.EMPTY_CHAR;

        char[][] chars = new char[][]{
                {f, e, e},
                {f, f, e},
                {f, f, f}
        };

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        cubePrinter.printCharsToStream(new PrintStream(out), chars);

        log.info("chars array " + chars + " printed as:\n" + out.toString());

        assertThat(out.toString(), is("o  " + System.lineSeparator() +
                                              "oo " + System.lineSeparator() +
                                              "ooo" + System.lineSeparator()
                   )
        );
    }

    @Test
    public void testPrintPieceToChars() throws Exception {
        CubePrinter cubePrinter = new CubePrinter();

        Piece piece = new Piece(0, new byte[][]{
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
        });

        String s = cubePrinter.printPieceToString(piece);

        assertThat(s, is("o    " + System.lineSeparator() +
                          "oo   " + System.lineSeparator() +
                          "ooo  " + System.lineSeparator() +
                          "oooo " + System.lineSeparator() +
                          "ooooo" + System.lineSeparator()
                   )
        );
    }

    @Test
    public void testPrintDisconnectedCubeToString() throws Exception {
        PieceHeap referencePieceHeap = InputCubes.createBlueCube();

        CubePrinter cubePrinter = new CubePrinter();

        String string = cubePrinter.printCubeToString(referencePieceHeap);

        log.info(string);
    }

}
