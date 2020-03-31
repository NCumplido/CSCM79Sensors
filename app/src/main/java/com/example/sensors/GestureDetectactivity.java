package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/* Rotation vector:
Uses the Accelerometer, Magnetometer,and Gyroscope
and reports the orientation of the device
relative to the East-North-Up coordinates frame.
ØIt uses a gyroscope as the main orientation change
input, and uses the accelerometer and magnetometer
input to make up for gyroscope drift.
 */

public class GestureDetectactivity extends AppCompatActivity  implements SensorEventListener {


    private SensorManager msensorManager;
    private Sensor mSensorAccel;
    private long lastUpdate = 0;
    private float m_ZFlickThreshold = 10;
    private TextView m_txtIsComplete;

    private float orientx, orienty, orientz;


    Button m_btnRotate, m_btnFlick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detectactivity);

        m_txtIsComplete = findViewById(R.id.txt_is_complete);

        msensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //linear acceleration = acceleration - acceleration due to gravity
        mSensorAccel = msensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        msensorManager.registerListener(this, mSensorAccel, SensorManager.SENSOR_DELAY_NORMAL);

        m_btnFlick = findViewById(R.id.btnFlick);
        m_btnFlick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                m_txtIsComplete.setText("Do flick");
                Toast.makeText(getApplicationContext(), "Flick to complete", Toast.LENGTH_SHORT).show();
            }
        });

        m_btnRotate = findViewById(R.id.btn_rotate);
        m_btnRotate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                m_txtIsComplete.setText("Do rotate");
                Toast.makeText(getApplicationContext(), "Rotate to complete", Toast.LENGTH_SHORT).show();
            }
        });

        //m_txtIsComplete.setProgress(0);
        //m_txtIsComplete.setMax(100);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        /*if(sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];

            if(z>m_ZFlickThreshold){
                //m_txtIsComplete.setProgress(100);
                m_txtIsComplete.setText("Action complete");
            }
        } */

        synchronized (this) {
            switch (sensorEvent.sensor.getType()){
                case Sensor.TYPE_LINEAR_ACCELERATION:
                    float z = sensorEvent.values[2];
                    if(z>m_ZFlickThreshold){
                        //m_txtIsComplete.setProgress(100);
                        m_txtIsComplete.setText("Flick complete");
                    }
                    break;
                /*case Sensor.TYPE_ACCELEROMETER:
                    outputX.setText("Accelerationx:"+Float.toString(event.values[0]));
                    outputY.setText("Accelerationy:"+Float.toString(event.values[1]));
                    outputZ.setText("Accelerationz:"+Float.toString(event.values[2]));
                    break;*/
                case Sensor.TYPE_ORIENTATION:
                    orientx = sensorEvent.values[0];
                    orienty = sensorEvent.values[1];
                    orientz = sensorEvent.values[2];

                    if ( orientx > 60 && 70 > orientx){
                        m_txtIsComplete.setText("Rotated right complete");
                    }
                    else if (orientx > 280 && 290 > orientx)
                    {
                        m_txtIsComplete.setText("Rotated left complete");
                    }

                    /*outputX2.setText("Orientationx:"+Float.toString(event.values[0]));
                    outputY2.setText("Orientationy:"+Float.toString(event.values[1]));
                    outputZ2.setText("Orientationz:"+Float.toString(event.values[2])); */

                    break;
            }
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
