package org.example.finalprojectjfx_game.leaderbord;

public class Leader {
    private int rank;
    private String name;
    private long score;

    public Leader() {
        this.rank = 0;
        this.name = "";
        this.score = 0;
    }

    public Leader(int rank, String name, long score) {
        this.rank = rank;
        this.name = name;
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void print(){
        System.out.println(rank);
        System.out.println(name);
        System.out.println(score);
    }
}
