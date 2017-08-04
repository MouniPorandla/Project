package com.example.mounika.project;
import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {
    Button readbutton;
    EditText inputtext;
    TextView content;
    Button savebutton;
    Button speak;
    TextToSpeech texttoaudioconversion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = (TextView) findViewById(R.id.content);
        inputtext = (EditText) findViewById(R.id.inputtext);
        readbutton = (Button) findViewById(R.id.readbutton);
        savebutton = (Button) findViewById(R.id.savebutton);
        speak = (Button) findViewById(R.id.button);
        readbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                content.setText(Filesinterface.ReadFile(MainActivity.this));
            }
        });

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Filesinterface.saveToFile(inputtext.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "File saved", Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(MainActivity.this, "File not saved error", Toast.LENGTH_SHORT).show();
                }
            }
        });


        texttoaudioconversion = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int checkingstatus) {
                if (checkingstatus != TextToSpeech.ERROR) {
                    texttoaudioconversion.setLanguage(Locale.US);
                }
            }
        });

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speaking = inputtext.getText().toString();
                Toast.makeText(getApplicationContext(), speaking, Toast.LENGTH_SHORT).show();
                texttoaudioconversion.speak(speaking, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    public void onPause() {
        if (texttoaudioconversion == null) {
            texttoaudioconversion.stop();
            texttoaudioconversion.shutdown();

        }

        super.onPause();
    }
}





