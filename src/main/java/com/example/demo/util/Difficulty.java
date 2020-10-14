package com.example.demo.util;

public enum Difficulty {

    PIECE_OF_CAKE("Piece of cake"),
    WALK_IN_THE_PARK("Walk in the park"),
    HMM("Hmmm...."),
    RISKY("Risky");

    private String difficulty;

    private Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
