package org.alunev.cubes;

import org.alunev.cubes.solver.CubeSolver;
import org.alunev.cubes.solver.Figure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(CubeSolver.class);

    public static void main(String[] args) {
        log.info("Solving blue cube...");
        List<Figure> blueCubes = new CubeSolver(InputCubes.createBlueCube().getPieces()).solve();

        log.info("Solving red cube...");
        List<Figure> redCubes = new CubeSolver(InputCubes.createRedCube().getPieces()).solve();

        log.info("Solving purple cube...");
        List<Figure> purpleCubes = new CubeSolver(InputCubes.createPurpleCube().getPieces()).solve();

        log.info("Solving green cube...");
        List<Figure> greenCubes = new CubeSolver(InputCubes.createGreenCube().getPieces()).solve();

        logCubeSolutions("Blue", blueCubes);
        logCubeSolutions("Red", redCubes);
        logCubeSolutions("Purple", purpleCubes);
        logCubeSolutions("Green", greenCubes);

        log.info("Totals: ");
        log.info("Blue cube unique solutions: {}", blueCubes.size());
        log.info("Red cube unique solutions: {}", redCubes.size());
        log.info("Purple cube unique solutions: {}", purpleCubes.size());
        log.info("Green cube unique solutions: {}", greenCubes.size());

    }

    private static void logCubeSolutions(String name, List<Figure> cubes) {
        log.info("{} cubes:", name);
        for (Figure cube : cubes) {
            log.info("{}", cube);
        }
    }
}
