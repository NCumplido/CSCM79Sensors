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

    private SensorManager msensorManager;
    private Sensor mSensorStepCounter;

    TextView txtStepValue;

    //public static final int TYPE_STEP_COUNTER;
   // public static final int TYPE_STEP_DETECTOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        txtStepValue = findViewById(R.id.txt_step_value);

        msensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensorStepCounter = msensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        msensorManager.registerListener((SensorEventListener) this, mSensorStepCounter, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            txtStepValue.setText("Steps: " + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
