package com.example.mathias.birdwatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int LoginActivityRequestCode = 47;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void loginButtonClicked(View view){
        Intent intent = new Intent(this, LogInActivity.class);
        startActivityForResult(intent, LoginActivityRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        TextView messageView = findViewById(R.id.mainMessageTextView);
        if(requestCode == LoginActivityRequestCode){
            if(resultCode == RESULT_CANCELED){
                messageView.setText("You cancelled. Try again");
            } else {
                String username = data.getStringExtra(LogInActivity.USERNAME);
                messageView.setText("Welcome " + username);
            }

        } else {
            messageView.setText("Strange requestCode !?");
        }
    }
}
