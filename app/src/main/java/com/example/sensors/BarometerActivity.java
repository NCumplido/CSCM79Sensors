package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class BarometerActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager msensorManager;
    private Sensor mSensorBarom;

    TextView mtxt_barom_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barometer);

        mtxt_barom_val = findViewById(R.id.txt_barometer_value);

        msensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //linear acceleration = acceleration - acceleration due to gravity
        mSensorBarom = msensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        msensorManager.registerListener(this, mSensorBarom, SensorManager.SENSOR_DELAY_UI);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        mtxt_barom_val.setText("" + values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
