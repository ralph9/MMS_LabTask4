package com.example.abstractlake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Sensor data label
    TextView mSensorsTot;

    //Adapter and lists for the sensor data in raw form and formatted into a string
    static RecyclerViewAdapter adapter;
    static List<Sensor> sensorList = new ArrayList<>();
    static List<String> sensorDataList = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the text field of the layout
        mSensorsTot   = findViewById(R.id.textSensorsAvailable);

        //Get the SensorManager
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //List of all sensors available (no matter the type) retrieved from the manager
        sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        //Extract the data to be displayed from each sensor and insert it into the list
        for(Sensor sensor: sensorList){
            String sensorNameFormatted = sensor.getName();
            //System.out.println(sensor.toString());
            //We eliminate 'Goldfish', since it doesn't provide useful data (it's the name of the
            //Linux kernel that the emulator uses)
            sensorNameFormatted =sensorNameFormatted.replaceFirst("Goldfish ", "");
            String sensorDataFormatted = sensorNameFormatted + " \n Vendor: " + sensor.getVendor() + " \n Power: " + sensor.getPower();
            sensorDataList.add(sensorDataFormatted);
        }

        //Set up the RecyclerView with the layout file
        RecyclerView recyclerView = findViewById(R.id.rvSensors);

        //Create a layout manager for the recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        //Adapter specification and linking with the data list
        adapter = new RecyclerViewAdapter(this, sensorDataList);
        recyclerView.setAdapter(adapter);


    }
}


