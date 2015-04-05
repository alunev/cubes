package org.alunev.cubes.solver;

import org.alunev.cubes.InputCubes;
import org.alunev.cubes.io.CubePrinter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ValidatorTest {
    private final static Logger log = LoggerFactory.getLogger(FigureTest.class);

    @Test
    public void requiredConnectionsMatchedForReferenceSolution() {
        Figure referenceFigure = InputCubes.createSingleBlueSolution();

        log.info(new CubePrinter().printCubeToString(referenceFigure));

        assertThat(new Validator(referenceFigure).validateCubeConnections(), is(true));
    }
}