package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StepCounterActivity extends AppCompatActivity  implements SensorEventListener{

    TextView txtStepValue;
    Button btnReset;
    public int stepCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stepCount =0;
        txtStepValue = findViewById(R.id.txt_step_value);
        btnReset = findViewById(R.id.btn_reset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                txtStepValue.setText(" ");
            }
        });

        SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Sensor mSensorStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mSensorManager.registerListener((SensorEventListener) this, mSensorStepCounter, SensorManager.SENSOR_DELAY_UI);

        mSensorManager.registerListener(this, mSensorStepCounter,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        txtStepValue.setText(String.valueOf(event.values[0]));
        /*if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            //Only works with stepDetector
            //stepCount++;
            //txtStepValue.setText(stepCount);
            txtStepValue.setText(String.valueOf(event.values[0]));
        }*/

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
