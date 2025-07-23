package com.example.manholemonitor;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private TextView humidityTextView, temperatureTextView, irStatusTextView, tiltStatusTextView, waterLevelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextViews
        humidityTextView = findViewById(R.id.humidityTextView);
        temperatureTextView = findViewById(R.id.temperatureTextView);
        irStatusTextView = findViewById(R.id.irStatusTextView);
        tiltStatusTextView = findViewById(R.id.tiltStatusTextView);
        waterLevelTextView = findViewById(R.id.waterLevelTextView);

        // Subscribe to Firebase topic for receiving notifications
        FirebaseMessaging.getInstance().subscribeToTopic("manhole")
                .addOnCompleteListener(task -> {
                    String msg = task.isSuccessful() ? "Subscribed to Manhole Alerts!" : "Subscription failed.";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                });

        // Reference to Firebase Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("manhole_status");

        // Listen for data changes
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String humidity = snapshot.child("humidity").getValue().toString();
                    String irStatus = snapshot.child("ir_status").getValue(String.class);
                    String temperature = snapshot.child("temperature").getValue().toString();
                    String tiltStatus = snapshot.child("tilt_status").getValue(String.class);
                    String waterLevel = snapshot.child("water_level").getValue(String.class);

                    // Update the UI with data
                    humidityTextView.setText("Humidity: " + humidity + "%");
                    temperatureTextView.setText("Temperature: " + temperature + "°C");
                    irStatusTextView.setText("IR Status: " + irStatus);
                    tiltStatusTextView.setText("Tilt: " + tiltStatus);
                    waterLevelTextView.setText("Water Level: " + waterLevel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to read data!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
