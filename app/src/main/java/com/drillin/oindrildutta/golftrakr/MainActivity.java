package com.drillin.oindrildutta.golftrakr;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

public class MainActivity extends ListActivity { //AppCompatActivity {
    private static final String PREFS_FILE = " com.drillin.golftrakrapp.preferences";
    private static final String KEY_NAME = "holez";
    private SharedPreferences sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
    private SharedPreferences.Editor prefEditor = sharedPreferences.edit();
    private ListAdapter lAdapter;
    private int[] holes = new int[18];

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

        for(int i = 0; i < holes.length; i++)
            holes[i] = sharedPreferences.getInt(KEY_NAME+i, 0);
        lAdapter = new ListAdapter(this, holes);
        setListAdapter(lAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(int i = 0; i < holes.length; i++)
            prefEditor.putInt(KEY_NAME+i, holes[i]);
        prefEditor.apply();
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
