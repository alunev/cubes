package org.alunev.cubes.model;

import java.util.ArrayList;
import java.util.List;


public class PieceHeap {
    private final List<Piece> pieces = new ArrayList<>();

    public PieceHeap(List<Piece> pieces) {
        this.pieces.addAll(pieces);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return "PieceHeap{" +
                "pieces=" + pieces +
                '}';
    }
}
