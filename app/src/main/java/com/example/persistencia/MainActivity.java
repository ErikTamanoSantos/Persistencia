package com.example.persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.saveFile);
        EditText fileContent = findViewById(R.id.fileContent);
        readFile();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    OutputStream os = openFileOutput("dades.txt", MODE_PRIVATE);
                    String message = String.valueOf(fileContent.getText());
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
        if (!file.exists()) {
            createFile();
        }
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
                line = br.readLine();
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        TextView fileContent = (TextView)findViewById(R.id.fileContent);
        fileContent.setText(stringBuilder.toString());
    }

    public void createFile() {
        try {
            OutputStream os = openFileOutput("dades.txt", MODE_PRIVATE);
            String message= "Hello, World!";
            os.write(message.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}