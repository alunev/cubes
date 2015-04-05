package org.alunev.cubes.solver;

import org.alunev.cubes.InputCubes;
import org.alunev.cubes.io.CubePrinter;
import org.alunev.cubes.model.PieceHeap;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;


public class PieceHeapSolverTest {
    private final static Logger log = LoggerFactory.getLogger(PieceHeapSolverTest.class);

    private PieceHeap referencePieceHeap;

    @Before
    public void setUp() throws Exception {
        referencePieceHeap = InputCubes.createBlueCube();
    }

    @Test
    public void returnsSomeSolutionForReferenceCube() throws Exception {
        CubeSolver cubeSolver = new CubeSolver(referencePieceHeap.getPieces());
        List<Figure> figures = cubeSolver.solve();

        assertThat(figures, is(not(nullValue())));
        assertThat(figures, is(not(empty())));

        Figure figure = figures.get(0);
        assertThat(figure.isComplete(), is(true));

        log.info(System.lineSeparator() + new CubePrinter().printCubeToString(figure));
    }

}