package com.example.mathias.birdwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BirdList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_list);

        TextView listHeader = new TextView(this);
        listHeader.setText("Birds");
        listHeader.setTextAppearance(this, android.R.style.TextAppearance);
        ListView listView = findViewById(R.id.main_birds_listview);
        listView.addHeaderView(listHeader);
    }

    @Override
    protected void onStart(){
        super.onStart();
        ReadTask task = new ReadTask();
        task.execute("http://birdobservationservice.azurewebsites.net/service1.svc/help/operations/GetBirds");

    }
    public void addBird(View view){
        Intent intent = new Intent(this, AddBirdActivity.class);
        startActivity(intent);

    }

    private class ReadTask extends ReadHttpTask{
        @Override
        protected void onPostExecute(CharSequence jsonStaring){
            TextView messageTextView = findViewById(R.id.main_message_textview);

           final List<Bird> birds = new ArrayList<>();
           try{
               JSONArray array = new JSONArray(jsonStaring.toString());
               for(int i = 0; i < array.length(); i++){
                   JSONObject obj = array.getJSONObject(i);
                   String NameEnglish = obj.getString("English Name");
                   String NameDanish = obj.getString("Danish Name");
                   String PhotoUrl = obj.getString("Photo Url");
                   String UserId = obj.getString("User Id");
                   String Created = obj.getString("Created");
                   int Id = obj.getInt("Id");
                   Bird bird = new Bird(Id, NameEnglish, NameDanish, PhotoUrl, UserId, Created);
                   birds.add(bird);
               }
               ListView listView = findViewById(R.id.main_birds_listview);
               ArrayAdapter<Bird> adapter = new ArrayAdapter<Bird>(getBaseContext(), R.layout.birdlist_item, birds);
               listView.setAdapter(adapter);
               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                       Intent intent = new Intent(getBaseContext(), BirdActivity.class);
                       Bird bird = (Bird) adapterView.getItemAtPosition(10);
                       intent.putExtra("BIRD", bird);
                       startActivity(intent);
                   }
               });

           }catch (JSONException ex){
               messageTextView.setText(ex.getMessage());
               Log.e("BIRDS", ex.getMessage());
           }

        }
        @Override
        protected void onCancelled(CharSequence message){
            TextView messageTextView = findViewById(R.id.main_message_textview);
            messageTextView.setText(message);
            Log.e("BIRDS", message.toString());
        }


    }
}
