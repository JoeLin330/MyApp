package com.example.joe.finalcode_randomplayer;

/**
 * Created by USER on 2018/2/13.
 */

public class GamePlaying {

    private int computerNumber;
    private int round;
    private int range1;
    private int range2;
    private int destiny;
    private int punishNumber;
    private String titleMessage;

    public GamePlaying() {
        restartGame();
    }

    public int getComputerNumber() {
        return computerNumber;
    }

    public int getRange1() {
        return range1;
    }

    public void setRange1(int range1) {
        this.range1 = range1;
    }

    public int getRange2() {
        return range2;
    }

    public void setRange2(int range2) {
        this.range2 = range2;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getDestiny() {
        return destiny;
    }

    public String getTitleMessage() {
        return titleMessage;
    }

    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
    }

    public String getTitleMessage(int guessNumber) {

        if (guessNumber <= range1 || guessNumber >= range2) {
            titleMessage = "錯誤！必須輸入" + range1 + "到" + range2 + "之間的數字。";

        } else if (guessNumber < computerNumber && guessNumber > range1) {
            setRange1(guessNumber);
            titleMessage = "範圍: " + range1 + " ~ " + range2;
            setRound(round + 1);

        } else if (guessNumber > computerNumber && guessNumber < range2) {
            setRange2(guessNumber);
            titleMessage = "範圍: " + range1 + " ~ " + range2;
            setRound(round + 1);
        }

        return titleMessage;
    }

    public void restartGame() {
        computerNumber = (int)(Math.random() * 99 + 1);
        round = 1;
        range1 = 0;
        range2 = 100;
        destiny = (int)(Math.random() * 2);
        punishNumber = (int)(Math.random() * 16 + 1);
    }

    public int punishRange() {

        int punishRange = -1;

        if (punishNumber >= 1 && punishNumber <= 7) {
            punishRange = 0;

        } else if (punishNumber >= 8 && punishNumber <= 12) {
            punishRange = 1;

        } else if (punishNumber == 13) {
            punishRange = 2;

        } else if (punishNumber >= 14 && punishNumber <= 16) {
            punishRange = 3;
        }

        return punishRange;
    }
}
