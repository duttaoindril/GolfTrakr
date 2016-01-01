package com.drillin.oindrildutta.golftrakr;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;

public class MainActivity extends ListActivity { //AppCompatActivity {
    private final String[] COLORS = {"#1abc9c", "#2ecc71", "#3498db", "#9b59b6", "#34495e", "#16a085", "#27ae60", "#2980b9", "#8e44ad", "#2c3e50", "#f1c40f", "#e67e22", "#e74c3c", "#f39c12", "#d35400", "#c0392b", "#fc970b"};
    private static final String PREFS_FILE = "com.drillin.golftrakrapp.preferences";
    private static final String KEY_NAME = "holez";
    private SharedPreferences.Editor prefEditor;
    private final String COLORA_DATA = "A";
    private final String COLORB_DATA = "B";
    private Random gen = new Random();
    private int[] holes = new int[18];
    private ListAdapter lAdapter;
    private int colorA;
    private int colorB;

    //https://romannurik.github.io/AndroidAssetStudio/icons-launcher.html#foreground.type=image&foreground.space.trim=0&foreground.space.pad=0.1&foreColor=fff%2C0&crop=0&backgroundShape=circle&backColor=2196f3%2C100&effects=shadow

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < holes.length; i++)
                    holes[i] = 0;
                lAdapter.notifyDataSetChanged();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        prefEditor = sharedPreferences.edit();
        prefEditor.apply();
        for(int i = 0; i < holes.length; i++)
            holes[i] = sharedPreferences.getInt(KEY_NAME+i, 0);
        lAdapter = new ListAdapter(this, holes);
        setListAdapter(lAdapter);
        colorA = Color.parseColor(COLORS[gen.nextInt(COLORS.length)]);
        do {
            colorB = Color.parseColor(COLORS[gen.nextInt(COLORS.length)]);
        } while(colorB == colorA);
        if(savedInstanceState != null) {
            colorA = savedInstanceState.getInt(COLORA_DATA, colorA);
            colorB = savedInstanceState.getInt(COLORB_DATA, colorB);
        }
        RelativeLayout background = (RelativeLayout) findViewById(R.id.background);
        background.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{colorA, colorB}));
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(int i = 0; i < holes.length; i++)
            prefEditor.putInt(KEY_NAME+i, holes[i]);
        prefEditor.apply();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COLORA_DATA, colorA);
        outState.putInt(COLORB_DATA, colorB);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clear) {
            for(int i = 0; i < holes.length; i++)
                prefEditor.putInt(KEY_NAME+i, 0);
            lAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }*/
}
