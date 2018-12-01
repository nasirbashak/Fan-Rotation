package com.nasirbashak007.fanrotation;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BaseInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button B1;
    Switch aSwitch;
    Boolean test = false;
    RotateAnimation rotate;
    SeekBar speed1, speed2;
    int progressvalue = 0;
    int FLAG = 0;
    RadioButton radio;
    Boolean toggleStatus;
    ToggleButton toggleButton;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        aSwitch = (Switch) findViewById(R.id.switch1);
        toggleButton = (ToggleButton) findViewById(R.id.toggle);

        speed1 = (SeekBar) findViewById(R.id.seekBar2);
        speed2 = (SeekBar) findViewById(R.id.seekBarDiscrete);
        //radio =(RadioButton)findViewById(R.id.radioButton);

        speed2.setEnabled(true);
        speed1.setEnabled(false);
        toggleButton.setText("OFF");

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    speed1.setEnabled(true);
                    speed2.setEnabled(false);
                    toggleButton.setText("ON");

                } else {
                    speed1.setEnabled(false);
                    speed2.setEnabled(true);
                    toggleButton.setText("OFF");
                }
            }
        });

        speed2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressvalue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                test = aSwitch.isChecked();
                if (test) {
                    if (progressvalue > 0) {
                        rotateTheFan(3000 / progressvalue);
                    } else {
                        rotate.cancel();
                        // Toast.makeText(getApplicationContext(), "Increase The Speed (SeekBar)", Toast.LENGTH_SHORT).show();
                    }
                    // Toast.makeText(getApplicationContext(), "Progress " + progressvalue, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Turn On The Switch", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getApplicationContext(), "Progress 2 " + progressvalue, Toast.LENGTH_SHORT).show();


            }
        });

        speed1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressvalue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                test = aSwitch.isChecked();
                if (test) {
                    if (progressvalue > 0) {
                        rotateTheFan(3000 / progressvalue);
                    } else {
                        rotate.cancel();
                        //      Toast.makeText(getApplicationContext(), "Increase The Speed (SeekBar)", Toast.LENGTH_SHORT).show();
                    }
                    // Toast.makeText(getApplicationContext(), "Progress " + progressvalue, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Turn On The Switch", Toast.LENGTH_SHORT).show();
                }
                //    Toast.makeText(getApplicationContext(), "Progress 2 " + progressvalue, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void rotateTheFan(int controlSpeed) {

        rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        //rotate.setFillAfter(false);
        rotate.setDuration(controlSpeed);

        rotate.setRepeatCount(RotateAnimation.INFINITE);
        //rotate.setRepeatMode(Animation.RESTART);

        rotate.setInterpolator(new LinearInterpolator());
        imageView.startAnimation(rotate);
        FLAG = 1;
    }

    public void checkForTheSwitch(View view) {
        test = aSwitch.isChecked();
        if (test) {
            if (progressvalue > 0) {

                rotateTheFan(3000 / progressvalue);
                Toast.makeText(getApplicationContext(), "On", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "Increase The Speed", Toast.LENGTH_SHORT).show();
            }

        } else {
            if (FLAG == 1) {
                rotate.cancel();
            } else {
                //  Toast.makeText(getApplicationContext(), "Here Was The Error", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getApplicationContext(), "Off", Toast.LENGTH_SHORT).show();
        }
    }
}
