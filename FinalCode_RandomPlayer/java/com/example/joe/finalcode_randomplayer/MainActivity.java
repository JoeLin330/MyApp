package com.example.joe.finalcode_randomplayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private String[] items;
    private int choice = -1;
    private String punishChoice;

    private String[] luckyCard;

    private TextView info, punish_title;
    private Button confirm_button, restart_button, plus_button, questionmark_button, bepunishers_button;
    private EditText number_edit;

    private Punish punish;
    private int guessNumber;

    private ArrayList<String> players = new ArrayList<>();

    private ArrayList<String> bePunishers;
    private boolean[] bePunishSelection;
    private String[] bePunishList;

    public static ArrayList<Players> Player = new ArrayList<>();

    private Players player;

    private int vibrateSecond;
    private SoundPool soundPool;
    private int soundId01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processViews();
        processControllers();

        //取得懲罰和幸運卡的資源
        Resources r = getResources();
        items = r.getStringArray(R.array.punish_text);
        luckyCard = r.getStringArray(R.array.luckyCard);

        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        soundId01 = soundPool.load(this, R.raw.boom_sound, 0);

        punish = new Punish(luckyCard);
        player = new Players();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            players = data.getStringArrayListExtra("players");

            //判斷玩家是否已經再ArrayList<Players> Player裡
            boolean exist = false;
            for (int i = 0; i < players.size(); i++) {

                for (int j = 0; j < Player.size(); j++) {

                    if (Player.get(j).getName().equals(players.get(i))) {
                        exist = true;
                    } else {
                        exist = false;
                    }
                }

                if (!exist) {
                    Player.add(new Players(players.get(i), 0, 0, 0, 0, 0));
                }
            }
        }
    }

    private void processViews() {
        info = findViewById(R.id.info);
        confirm_button = findViewById(R.id.confirm_button);
        restart_button = findViewById(R.id.restart_button);
        punish_title = findViewById(R.id.punish_title);
        number_edit = findViewById(R.id.number_edit);
        plus_button = findViewById(R.id.plus_button);
        questionmark_button = findViewById(R.id.questionmark_button);
        bepunishers_button = findViewById(R.id.bepunishers_button);
    }

    public void BepunisherButton(View view) {

        bePunishSelection = new boolean[players.size()];

        bePunishList = new String[players.size()];

        for (int i = 0; i < players.size(); i++) {
            bePunishList[i] = players.get(i);
        }

        AlertDialog.Builder d =
                new AlertDialog.Builder(this, android.R.style.Theme_Holo_Dialog);

        d.setTitle("被懲罰的人...").setCancelable(false);

        d.setMultiChoiceItems(bePunishList, bePunishSelection, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                bePunishSelection[which] = isChecked;
            }
        });

        d.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                bePunishers = new ArrayList<>();

                for (int i = 0; i < bePunishSelection.length; i++) {
                    if (bePunishSelection[i]) {
                        if (!(bePunishers.contains(bePunishList[i])))
                            bePunishers.add(bePunishList[i]);
                    }
                }

                packageInfo();

                restartGame();
            }
        });

        d.show();
    }

    private void packageInfo() {

        for (int i = 0; i < bePunishers.size(); i++) {
            for (int j = 0; j < Player.size(); j++) {
                if (bePunishers.get(i).equals(Player.get(j).getName())) {
                    player.savePunishTimes(Player.get(j), punishChoice, punish);
                }
            }
        }
    }

    private void processControllers() {
        confirm_button.setOnClickListener(new ConfirmButton());

        restart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { restartGame();}});

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                Bundle bundle = new Bundle();

                bundle.putStringArrayList("players", players);

                intent.putExtras(bundle);

                startActivityForResult(intent, 0);
            }
        });

        questionmark_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (guessNumber == punish.getComputerNumber()) {

                    final AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this,
                            android.R.style.Theme_Holo_Dialog);

                    setBingo(d);

                    dialogBuilder(d);
                }
            }
        });
    }

    private class ConfirmButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (number_edit.getText().toString().isEmpty()) {
                String message = "請輸入數字!!!\n"+ punish.getRange1() + " ~ " + punish.getRange2() + punish.getComputerNumber();
                punish_title.setText(message);

                return;
            }

            guessNumber = Integer.parseInt(number_edit.getText().toString());

            if (guessNumber != punish.getComputerNumber()) {

                String message = punish.getTitleMessage(guessNumber);

                number_edit.setText("");
                punish_title.setText(message);
            }

            if (guessNumber == punish.getComputerNumber()) {

                bepunishers_button.setVisibility(View.VISIBLE);
                soundPool.play(soundId01, 5.0F, 5.0F, 0, 0, 1.0F);

                final AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this,
                        android.R.style.Theme_Holo_Dialog);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(number_edit.getWindowToken(), 0);

                setBingo(d);

                setVibrate(vibrateSecond);

                dialogBuilder(d);
            }
        }
    }

    //設定猜中數字後的資訊
    private void setBingo(AlertDialog.Builder d) {

        if (punish.getRound() == 1) {
            vibrateSecond = 5000;
            d.setTitle("恭喜你!!!一次就猜中了。");
            punish.setTimes(50);
            punish.setMeters(1000);

        } else if (punish.getRound() <= 3 && punish.getRound() > 1) {
            vibrateSecond = 3000;
            d.setTitle("恭喜!3回合內猜中。");
            punish.setTimes(20);
            punish.setMeters(300);

        } else if (punish.getRound() > 3) {
            vibrateSecond = 2000;
            d.setTitle("猜中了!");
            punish.setTimes(10);
            punish.setMeters(100);
        }
    }

    //設定震動
    private void setVibrate(int time) {
        Vibrator myVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        myVibrator.vibrate(time);
    }

    //選擇懲罰和機會命運後的message
    private void dialogBuilder(AlertDialog.Builder d) {

        //懲罰選項的標題
        d.setSingleChoiceItems(items, choice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                choice = which;
                punishChoice = items[which];

                if (choice >= 0 && choice <= 2) {
                    punish.setTitleMessage(punishChoice + punish.getTimes() + "下");
                }
                else if (choice == 3) {
                    punish.setTitleMessage(punishChoice + punish.getMeters() + "公尺");
                }
                else if (choice == 4) {
                    punish.setTitleMessage(punishChoice + punish.getTimes() * 2 + "秒");
                }

                punish_title.setText(punish.getTitleMessage());
            }
        });

        //選擇命運後的message
        d.setPositiveButton("命運", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (choice != -1) {
                    switch (punish.getDestiny()) {
                        case 0: punish.unluckyPunish(punishChoice); break;
                        case 1: punish.luckyPunish(punishChoice);
                    }

                    info.setText(punish.getMessage());
                }
            }
        });

        //選擇機會後的message
        d.setNegativeButton("機會", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (choice != -1) {
                    switch (punish.getDestiny()) {
                        case 0: punish.luckyPunish(punishChoice); break;
                        case 1: punish.unluckyPunish(punishChoice);
                    }

                    info.setText(punish.getMessage());
                }
            }
        });

        d.show();
    }

    private void restartGame() {
        punish.restartGame();
        info.setText("");
        number_edit.setText("");
        choice = -1;
        punish_title.setText("");
        bepunishers_button.setVisibility(View.GONE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            AlertDialog.Builder d = new AlertDialog.Builder(MainActivity.this);

            d.setTitle("是否要離開遊戲...").setCancelable(false);

            d.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    finish();
                }
            });

            d.setNegativeButton("否", null);

            d.show();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        Player.clear();
        super.onDestroy();
    }
}