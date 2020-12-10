package com.weather.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

@Controller
public class WeatherController {

    @Autowired
    WeatherService weather2Service;

    Weather weather2;

    @GetMapping("/homepage")
    public String homepage(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("WEATHER");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                weather2 = dataSnapshot.getValue(Weather.class);
                System.out.println(weather2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        model.addAttribute("temp", weather2.getTemperature());
        model.addAttribute("humi", weather2.getHumidity());
        model.addAttribute("rain", weather2.getRaindrop());
        return "homepage";
    }

}
