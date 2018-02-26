package com.example.joe.finalcode_randomplayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SecondActivity extends Activity {

    private ListView list_view;
    private ArrayList<String> players = new ArrayList<>();
    private Bundle bundle;

    private SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        processViews();
        processControllers();

        Intent intent = getIntent();

        bundle = intent.getExtras();

        if (bundle != null) {
            players = bundle.getStringArrayList("players");
        }

        setSampleAdapter();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listview_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.second_menu_delete) {
            final String name = players.get(info.position);

            AlertDialog.Builder d = new AlertDialog.Builder(SecondActivity.this,
                    android.R.style.Theme_Holo_Dialog);

            d.setTitle("刪除玩家...").setMessage("確定要刪除" + name + " ?").setCancelable(false);

            d.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    players.remove(name);

                    setSampleAdapter();

                }
            });

            d.setNegativeButton(android.R.string.cancel, null);

            d.show();
        }

        return super.onContextItemSelected(item);
    }

    private void setSampleAdapter() {

        ArrayList<HashMap<String, String>> view = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            packageView(players.get(i), view);
        }

        String[] keys = {"name"};
        int[] viewsIds = {R.id.name_text};

        sa = new SimpleAdapter(SecondActivity.this, view, R.layout.listview_item1, keys, viewsIds);

        list_view.setAdapter(sa);
    }

    private void packageView(String playerName, ArrayList<HashMap<String, String>> view) {

        HashMap<String, String> record;

        record = new HashMap<>();

        record.put("name", playerName);

        view.add(record);
    }

    private void processViews() {
        list_view = findViewById(R.id.list_view);

        registerForContextMenu(list_view);
    }

    private void processControllers() {
        list_view.setOnItemClickListener(new MyItem());
    }

    private class MyItem implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                       int position, long id) {

            String action = "com.example.joe.FinalCode_RandomPlayer.intent01.ACTION.THIRD";

            int[] array = new int[5];
            String name;
            name = players.get(position);

            for (int i = 0; i < MainActivity.Player.size(); i++) {

                if (players.get(position).equals(MainActivity.Player.get(i).getName())) {

                    array[0] = MainActivity.Player.get(i).getPush_up();
                    array[1] = MainActivity.Player.get(i).getSplit_jump();
                    array[2] = MainActivity.Player.get(i).getSit_up();
                    array[3] = MainActivity.Player.get(i).getRun();
                    array[4] = MainActivity.Player.get(i).getPlank();
                    break;
                }
            }

            Intent intent = new Intent(action);

            bundle.putIntArray("array", array);
            bundle.putString("name", name);

            intent.putExtras(bundle);

            startActivity(intent);
        }
    }

    public void clickButton(View view) {

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.dialog_playerjoining, null);

        AlertDialog.Builder d =
                new AlertDialog.Builder(SecondActivity.this);

        d.setView(dialogView).setTitle("請輸入名字...");

        d.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                AlertDialog.Builder d01 =
                        new AlertDialog.Builder(SecondActivity.this);

                if (players.size() >= 8) {

                    d01.setTitle("人數已滿").setCancelable(false);

                    d01.setPositiveButton(android.R.string.ok, null);

                    d01.show();

                    return;
                }

                EditText playerName = dialogView.findViewById(R.id.input_playername);
                String name = playerName.getText().toString();

                if (name.isEmpty() || name.length() > 5) {

                    d01.setTitle("名字錯誤...").setMessage("尚未輸入名字/名字最多5個字元").setCancelable(false);

                    d01.setPositiveButton(android.R.string.ok, null);

                    d01.show();

                    return;
                }

                for (int i = 0; i < players.size(); i++) {
                    if (name.equals(players.get(i))) {

                        d01.setTitle("名字錯誤...").setMessage("已被使用。").setCancelable(false);

                        d01.setPositiveButton(android.R.string.ok, null);

                        d01.show();

                        return;
                    }
                }

                players.add(name);
                setSampleAdapter();
            }
        });

        d.setNegativeButton(android.R.string.cancel, null);

        d.show();
    }

    public void clickButton01(View view) {

        Intent result = new Intent();

        result.putExtra("players", players);

        setResult(Activity.RESULT_OK, result);

        finish();
    }
}
