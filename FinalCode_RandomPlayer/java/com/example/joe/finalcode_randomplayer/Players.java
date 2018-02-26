package com.example.joe.finalcode_randomplayer;

/**
 * Created by USER on 2018/2/13.
 */

public class Players {

    private String name;
    private int push_up;
    private int split_jump;
    private int sit_up;
    private int run;
    private int plank;

    public  Players() {}

    public  Players(String name, int push_up, int split_jump, int sit_up, int run, int plank) {
        this.name = name;
        this.push_up = push_up;
        this.split_jump = split_jump;
        this.sit_up = sit_up;
        this.run = run;
        this.plank = plank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPush_up() {
        return push_up;
    }

    public void setPush_up(int push_up) {
        this.push_up = push_up;
    }

    public int getSplit_jump() {
        return split_jump;
    }

    public void setSplit_jump(int split_jump) {
        this.split_jump = split_jump;
    }

    public int getSit_up() {
        return sit_up;
    }

    public void setSit_up(int sit_up) {
        this.sit_up = sit_up;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getPlank() {
        return plank;
    }

    public void setPlank(int plank) {
        this.plank = plank;
    }

    public void savePunishTimes(Players Player, String punishChoice, Punish punish) {

        switch (punishChoice) {

            case Punish.PUSH_UP: Player.setPush_up(Player.getPush_up() + punish.getPunishTimes()); break;
            case Punish.SPLIT_JUMP: Player.setSplit_jump(Player.getSplit_jump() + punish.getPunishTimes()); break;
            case Punish.SIT_UP: Player.setSit_up(Player.getSit_up() + punish.getPunishTimes()); break;
            case Punish.RUN: Player.setRun(Player.getRun() + punish.getPunishTimes()); break;
            case Punish.PLANK: Player.setPlank(Player.getPlank() + punish.getPunishTimes()); break;
        }

    }
}
