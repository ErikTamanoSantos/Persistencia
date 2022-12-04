package com.example.persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.saveButton);

        try {
            OutputStream os = openFileOutput("dades.txt", MODE_PRIVATE);
            String message= "Hello, World!";
            os.write(message.getBytes());
            os.close();
            readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    OutputStream os = openFileOutput("dades.txt", MODE_PRIVATE);
                    TextView text = findViewById(R.id.inputText);
                    String message = String.valueOf(text.getText());
                    os.write(message.getBytes());
                    os.close();
                    readFile();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


    }
    public void readFile(){
        File path = getFilesDir();
        File file = new File(path,"dades.txt");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                br.readLine();
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        TextView fileContent = (TextView)findViewById(R.id.fileContent);
        fileContent.setText(stringBuilder.toString());
    }
}