package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class StepCounterActivity extends AppCompatActivity  implements SensorEventListener{

    TextView txtStepValue;
    public int stepCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        stepCount =0;
        txtStepValue = findViewById(R.id.txt_step_value);

        SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Sensor mSensorStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mSensorManager.registerListener((SensorEventListener) this, mSensorStepCounter, SensorManager.SENSOR_DELAY_UI);

        mSensorManager.registerListener(this, mSensorStepCounter,
                SensorManager.SENSOR_DELAY_UI);
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