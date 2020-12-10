package com.weather.monitor;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class WeatherService {

    Weather weather;

    public Weather getWeatherDetails(String name) throws InterruptedException, ExecutionException {
        
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("WEATHER");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                weather = dataSnapshot.getValue(Weather.class);
                System.out.println(weather);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return weather;
    }


}