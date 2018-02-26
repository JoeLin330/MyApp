package com.example.joe.finalcode_randomplayer;

public class Punish extends GamePlaying {

    private String message;
    private int punishTimes;

    private int times;
    private int meters;

    private String[] luckyCard;
    private int randomToLuckyCard;

    public static final String PUSH_UP = "伏地挺身";
    public static final String SPLIT_JUMP = "交互蹲跳";
    public static final String SIT_UP = "仰臥起坐";
    public static final String RUN = "操場";
    public static final String PLANK = "棒式";

    public Punish(String[] luckyCard) {
        this.luckyCard = luckyCard;
        this.randomToLuckyCard = (int)(Math.random() * 2);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPunishTimes() {
        return punishTimes;
    }

    public void setPunishTimes(int punishTimes) {
        this.punishTimes = punishTimes;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getMeters() {
        return meters;
    }

    public void setMeters(int meters) {
        this.meters = meters;
    }

    public void luckyPunish(String punishChoice) {

        switch (punishChoice) {
            case PUSH_UP: packageLuckyPunishMessage1(); break;
            case SPLIT_JUMP: packageLuckyPunishMessage1(); break;
            case SIT_UP: packageLuckyPunishMessage1(); break;
            case RUN: packageLuckyPunishMessage2(); break;
            case PLANK: packageLuckyPunishMessage3(); break;
        }
    }

    public void unluckyPunish(String punishChoice) {

        switch (punishChoice) {
            case PUSH_UP: packageUnluckyPunishMessage1(); break;
            case SPLIT_JUMP: packageUnluckyPunishMessage1(); break;
            case SIT_UP: packageUnluckyPunishMessage1(); break;
            case RUN: packageUnluckyPunishMessage2(); break;
            case PLANK: packageUnluckyPunishMessage3(); break;
        }

    }

    public void packageLuckyPunishMessage1() {

        int number = punishRange();

        switch (number) {
            case 0:
                message = "恭喜!!!\n機會命運選中\n次數 -3";
                punishTimes = times - 3;
                break;
            case 1:
                message = "恭喜你!!!\n機會命運選中\n次數 -5";
                punishTimes = times - 5;
                break;
            case 2:
                message = "太好運了!!\n機會命運選中\n不用懲罰\n左右兩位懲罰 X2"
                        + "\n(各獲得一張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = times * 2;
                break;
            case 3:
                message = "懲罰你左邊那位!!!\n次數 +5"
                        + "\n(被懲罰者獲得一張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = times + 5;
        }

        setPunishTimes(punishTimes);
        setMessage(message);
    }

    public void packageLuckyPunishMessage2() {

        int number = punishRange();

        switch (number) {
            case 0:
                message = "恭喜!!!\n機會命運選中\n-50公尺";
                punishTimes = meters - 50;
                break;
            case 1:
                message = "恭喜你!!!\n機會命運選中\n-100公尺";
                punishTimes = meters - 100;
                break;
            case 2:
                message = "太好運了!!\n機會命運選中\n不用懲罰\n左右兩位懲罰 X2"
                        + "\n(各獲得一張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = meters * 2;
                break;
            case 3:
                message = "懲罰你左邊那位!!!\n+100公尺"
                        + "\n(被懲罰者獲得一張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = meters + 100;
        }

        setPunishTimes(punishTimes);
        setMessage(message);
    }

    public void packageLuckyPunishMessage3() {

        int number = punishRange();

        switch (number) {
            case 0:
                message = "恭喜!!!\n機會命運選中\n秒數 -3";
                punishTimes = (times * 2) - 3;
                break;
            case 1:
                message = "恭喜你!!!\n機會命運選中\n秒數 -5";
                punishTimes = (times * 2) - 5;
                break;
            case 2:
                message = "太好運了!!\n機會命運選中\n不用懲罰\n左右兩位懲罰 X2"
                        + "\n(各獲得一張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = (times * 2) * 2;
                break;
            case 3:
                message = "懲罰你左邊那位!!!\n秒數 +5"
                        + "\n(被懲罰者獲得一張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = (times * 2) + 5;
        }

        setPunishTimes(punishTimes);
        setMessage(message);
    }

    public void packageUnluckyPunishMessage1() {

        int number = punishRange();

        switch (number) {
            case 0:
                message = "真衰!!!\n機會命運選中\n次數 +3";
                punishTimes = times + 3;
                break;
            case 1:
                message = "真衰!!!\n機會命運選中\n次數 +5";
                punishTimes = times + 5;
                break;
            case 2:
                message = "衰爆了!!!\n機會命運選中\n懲罰 X 2\n(被懲罰者獲得兩張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = times * 2;
                break;
            case 3:
                message = "懲罰你右邊那位!!!\n次數 +5"
                        + "\n(被懲罰者獲得一張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = times + 5;
        }

        setPunishTimes(punishTimes);
        setMessage(message);
    }

    public void packageUnluckyPunishMessage2() {

        int number = punishRange();

        switch (number) {
            case 0:
                message = "真衰!!!\n機會命運選中\n+50公尺";
                punishTimes = meters + 50;
                break;
            case 1:
                message = "真衰!!!\n機會命運選中\n+100公尺";
                punishTimes = meters + 100;
                break;
            case 2:
                message = "衰爆了!!!\n機會命運選中\n懲罰 X 2\n(被懲罰者獲得兩張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = meters * 2;
                break;
            case 3:
                message = "懲罰你右邊那位!!!\n+100公尺"
                        + "\n(被懲罰者獲得一張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = meters + 100;
        }

        setPunishTimes(punishTimes);
        setMessage(message);
    }

    public void packageUnluckyPunishMessage3() {

        int number = punishRange();

        switch (number) {
            case 0:
                message = "真衰!!!\n機會命運選中\n秒數 +3";
                punishTimes = (times * 2) + 3;
                break;
            case 1:
                message = "真衰!!!\n機會命運選中\n秒數 +5";
                punishTimes = (times * 2) + 5;
                break;
            case 2:
                message = "衰爆了!!!\n機會命運選中\n懲罰 X 2\n(被懲罰者獲得兩張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = (times * 2) * 2;
                break;
            case 3:
                message = "懲罰你右邊那位!!!\n秒數 +5"
                        + "\n(被懲罰者獲得一張" + luckyCard[randomToLuckyCard] + ")";
                punishTimes = (times  * 2) + 5;
        }

        setPunishTimes(punishTimes);
        setMessage(message);
    }

    @Override
    public void restartGame() {
        super.restartGame();
        setPunishTimes(0);
        randomToLuckyCard = (int)(Math.random() * 2);
    }

}
