package com.example.speech_to_text;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    //Text View and button reference strings
    TextView mTextTv;
    ImageButton mVoiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextTv = findViewById(R.id.textTV);
        mVoiceBtn = findViewById(R.id.voiceBtn);

        /* The function that will allow the button to be clicked
        and show speech to text dialog
         */

        mVoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();

            }
        });
    }

    private void speak() {
// Intent to show speech to text dialog
        Intent intent = new Intent (RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi, speak something");

//        start intent
        try{
            //if there was no error
            // show dialog
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
        }

        catch(Exception e) {
        //if there was an error
            //get message of error and show
            Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    //receive voice input and handle it

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null!=data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //set to text view
                    mTextTv.setText(result.get(0));
                }
                break;
            }

        }
    }
}
