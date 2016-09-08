package com.polostod.math.HighScore;

/**
 * Created by pratma.net on 8/4/2016.
 */
public class Score {

    private int id;
    private String name;
    private String level;
    private double score;


    public Score()
    {
        id = 0;
        name = "";
        level = "";
        score = 0;
    }

    public Score(int iD, String nAme, String lEvel, int sCore)
    {
        id = iD;
        name = nAme;
        level = lEvel;
        score = sCore;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
