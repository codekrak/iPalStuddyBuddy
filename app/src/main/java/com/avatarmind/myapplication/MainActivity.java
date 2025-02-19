package com.avatarmind.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.robot.speech.SpeechManager;
import android.robot.motion.RobotMotion;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private SpeechManager mSpeechManager;
    private RobotMotion mRobotMotion;
    private Button mBtnBack;
    private Button mBtnNodHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SpeechManager voice
        mSpeechManager = (SpeechManager) this.getSystemService("speech");
        mSpeechManager.forceStartSpeaking("Red light!!! Green Light!!!");

        // Initialize RobotMotion
        mRobotMotion = new RobotMotion();

        // Initialize the back button
        mBtnBack = (Button) findViewById(R.id.btn_left);
        mBtnBack.setOnClickListener(this);

        // Initialize the button to nod the head movement
        mBtnNodHead = (Button) findViewById(R.id.btn_nod_head);
        mBtnNodHead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                finish();
                break;
            case R.id.btn_nod_head:
                // Make the robot nod its head
                mRobotMotion.nodHead();
                break;
        }
    }
}

// Test

