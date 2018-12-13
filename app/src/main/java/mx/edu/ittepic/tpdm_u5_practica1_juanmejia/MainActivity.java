package mx.edu.ittepic.tpdm_u5_practica1_juanmejia;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    Sensor gravedad, luz;
    SensorEventListener oyenteG, oyenteL;
    Lienzo lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Lienzo(this));

        lienzo = new Lienzo(this);
        setContentView(lienzo);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        gravedad = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        luz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (gravedad == null) {
            Toast.makeText(this, "No cuentas con el sensor de gravedad", Toast.LENGTH_LONG).show();
        } else {
            oyenteG = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    lienzo.nX = ((int)event.values[0]) * 10;
                    lienzo.nY = ((int)event.values[1]) * 10;
                    lienzo.invalidate();
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
        }

        if (luz == null) {
            Toast.makeText(this, "No No cuentas con el sensor de Luz", Toast.LENGTH_LONG).show();
        } else {
            oyenteL = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    int valor = (int)(event.values[0]);
                    if (valor == 0) {
                        lienzo.luz = false;
                    } else {
                        lienzo.luz =true;
                    }
                    lienzo.invalidate();
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
        }
    }

    protected void onResume(){
        super.onResume();
        if(gravedad != null){
            sensorManager.registerListener(oyenteG,gravedad,SensorManager.SENSOR_DELAY_NORMAL);
        }
        if(luz != null){
            sensorManager.registerListener(oyenteL,luz,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    protected void onPause(){
        super.onPause();
        if (gravedad != null){
            sensorManager.unregisterListener(oyenteG);
        }
        if (luz != null){
            sensorManager.unregisterListener(oyenteL);
        }
    }
}//class

