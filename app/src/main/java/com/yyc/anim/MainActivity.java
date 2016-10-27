package com.yyc.anim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    AnimView levelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        levelView = (AnimView) findViewById(R.id.v_level);
        levelView.setIcon(LevelUtil.getProgress(this,  4,2500), 4);
    }
}
