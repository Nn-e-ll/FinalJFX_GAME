package org.example.finalprojectjfx_game.leaderbord;

import org.example.finalprojectjfx_game.leaderbord.Leader;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LeaderRepository {
     private ArrayList<String> Name = new ArrayList<>();
     private ArrayList<Long> Score = new ArrayList<>();

     private String fileWay;

    public LeaderRepository(String way) throws IOException {
        try {
            this.fileWay = way;
            BufferedReader br = new BufferedReader(new FileReader(way));
            if (br.readLine() != null) {
                this.Score = SCORE(way);
                this.Name = NAME(way, new Scanner(new File(way)).nextInt());
                Sorting();
            }
        } catch (IOException ignored) {

        }

    }


    private ArrayList<String> NAME(String way, int size) throws IOException {
        try {
            ArrayList<String> NAME = new ArrayList<>();
            String name;
            BufferedReader reader = new BufferedReader(new FileReader(way));
            for(int i = 0; i < 2; i++){
                name = reader.readLine();
            }
            for (long l = 0; l < size; l++){
                name = reader.readLine();
                NAME.add(name);
            }
            return NAME;
        } catch (Exception e) {
            return null;
        }

    }
    private ArrayList<Long> SCORE(String way) throws IOException {
        try {
            ArrayList<Long> SCORE = new ArrayList<>();
            long record;
            Scanner input = new Scanner(new File(way));
            int size = input.nextInt();
            for (int l = 0; l < size; l++){
                record = input.nextLong();
                SCORE.addLast(record);
            }
            return SCORE;
        } catch (Exception e) {
            return null;
        }

    }
    private void Sorting(){
        for (int i = 0; i < Score.size(); i ++)
            for (int j = 0; j < Score.size(); j ++){
                if (Score.get(i) > Score.get(j)){
                    long buf1 = Score.get(i); long buf2 = Score.get(j);
                    Score.set(i, buf2); Score.set(j, buf1);
                    String buf3 = Name.get(i); String buf4 = Name.get(j);
                    Name.set(i, buf4); Name.set(j, buf3);
                }
            }
    }


    public Leader getLeaderPosition(int index){
        return new Leader(index+1, Name.get(index), Score.get(index));
    }
    public int TableSize(){
        return Score.size();
    }


    public void addLeaderFile(long score, String wayName) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(wayName));
            String name = reader.readLine();
            boolean Found = true;
            for (int i = 0; i < Name.size(); i++){
                if(Objects.equals(Name.get(i), name)) {
                    if (Score.get(i) <= score) {
                        this.Name.set(i, name);
                        this.Score.set(i, score);
                    }
                    Found = false;
                    break;
                }
            }
            if (Found){this.Score.add(score); this.Name.add(name);}

            PrintWriter writer = new PrintWriter(fileWay);
            writer.println(Score.size());
            for (Long aLong : Score) {
                writer.print(aLong);
                writer.print(" ");
            }
            for (String bString : Name) {
                writer.print("\n");
                writer.print(bString);
            }
            writer.close();

        } catch (Exception e) {
            BufferedReader reader = new BufferedReader(new FileReader(wayName));
            String name = reader.readLine();
            this.Score.add(score); this.Name.add(name);

            PrintWriter writer = new PrintWriter(fileWay);
            writer.println(Score.size());
            for (Long aLong : Score) {
                writer.print(aLong);
                writer.print(" ");
            }
            for (String bString : Name) {
                writer.print("\n");
                writer.print(bString);
            }
            writer.close();
        }

    }

    public void printArrays() {
        System.out.println(Score);
        System.out.println(Name);
    }


}
