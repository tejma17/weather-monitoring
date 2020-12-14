package com.weather.monitor;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.gson.Gson;
import java.nio.file.*;
import java.io.*; 
import com.google.firebase.database.ValueEventListener;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


@Controller
public class WeatherController {

    Weather weather2;

    //This line maps the location of address bar
    @GetMapping("/homepage")
    public String homepage(Model model){

        final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
        final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
        
        //Initialise Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("WEATHER");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //get items in the class Weather
                weather2 = dataSnapshot.getValue(Weather.class);

                if(Integer.parseInt(weather2.getHumidity().substring(0,2))>40){
                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(System.getenv("MY_NUMBER")),
                        new com.twilio.type.PhoneNumber(System.getenv("TWILIO_TEST_NO")),
                            "Dangerous Humidity!!! Alert !!!")
                        .create();
        
                }
                if(Integer.parseInt(weather2.getHumidity().substring(0,2))>28){
                            
                }
                if(Integer.parseInt(weather2.getTemperature().substring(0,2))>60){
                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(System.getenv("MY_NUMBER")),
                            new com.twilio.type.PhoneNumber(System.getenv("TWILIO_TEST_NO")),
                            "Dangerous Temperature!!! Alert !!! Something is getting really hott!! To check what?? Go checkout the spot!!")
                        .create();
        
                }

                System.out.println(weather2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        //this links the IDs in html file to attributes added below (temp, humi are IDs)
        model.addAttribute("temp", weather2.getTemperature().substring(0,2)+"°C");
        model.addAttribute("humi", weather2.getHumidity());

        if(weather2.getRaindrop().equals("Rain not Detected")){
            weather2.setRaindrop("NO");
        }else{
            weather2.setRaindrop("YES");
        }
        model.addAttribute("rain", weather2.getRaindrop());

        try{
            Writer writer = Files.newBufferedWriter(Paths.get("data.json"));

            new Gson().toJson(weather2,  writer);
            writer.close();

           // System.out.println(new Gson().toJson(weather2));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //return same name as of html filename
        return "homepage";
    }

}
