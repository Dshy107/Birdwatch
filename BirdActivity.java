package com.example.mathias.birdwatch;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class BirdActivity extends AppCompatActivity {
    private Bird bird;
    private EditText engnameView, dennameView, photoView, userView, createView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);

        Intent intent = getIntent();
        bird = (Bird) intent.getSerializableExtra("Bird");

        TextView headingView = findViewById(R.id.bird_heading_textview);
        headingView.setText("Bird Id=" + bird.getId());

        engnameView = findViewById(R.id.bird_engname_edittext);
        engnameView.setText(bird.getNameEnglish());

        dennameView = findViewById(R.id.bird_danname_edittext);
        dennameView.setText(bird.getNameDanish());

        photoView = findViewById(R.id.bird_photo_edittext);
        photoView.setText(bird.getPhotoUrl());

        userView = findViewById(R.id.bird_user_edittext);
        userView.setText(bird.getUserId());

        createView = findViewById(R.id.bird_create_edittext);
        createView.setText(bird.getCreated());
    }

    public void deleteBird(View view){
        DeleteTask task = new DeleteTask();
        task.execute("http://birdobservationservice.azurewebsites.net/service1.svc/help/operations/RemoveObservation");
        finish();
    }

    public void back(View view){
        finish();
    }

    private class DeleteTask extends AsyncTask<String, Void, CharSequence>{
        @Override
        protected CharSequence doInBackground(String... urls){
            String urlString = urls[0];
            try{
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("DELETE");
                int responseCode = connection.getResponseCode();
                if (responseCode % 100 != 2){
                    throw new IOException("Response code: " + responseCode);
                }
                return "Nothing";
            } catch (MalformedURLException e){
                return e.getMessage() + " " + urlString;
            } catch (IOException e){
                return e.getMessage();
            }
        }

        @Override
        protected void onCancelled(CharSequence charSequence){
            super.onCancelled(charSequence);
            TextView messageView = findViewById(R.id.bird_message_textview);
            messageView.setText(charSequence);
        }
    }
}
