package com.rohit.android.data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView output;
    public static final String FILE_NAME = "rohit.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.outputText);

    }

    public void onCreateButtonClick(View view) {

        String string = getString(R.string.lorem_ipsum);

        // fileoutstream object for store the data in the internal or external storage.
        FileOutputStream fileOutputStream = null;

        // Creating the file here file name is rohit.txt
        File file = new File(FILE_NAME);
        try {
            // opening the file using fileOutputStream , First parameter is file name and second one is file mode
            fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);

            // writing the string data in the file
            fileOutputStream.write(string.getBytes());

            Toast.makeText(this, "FILE WRITTEN TO STORAGE" + FILE_NAME,
                    Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "EXEPTION" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();

        } finally {
            try {
                // closing the fileOutputStream
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onReadButtonClick(View view) {

        try {
            FileInputStream fis = openFileInput(FILE_NAME);

            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

            BufferedReader bufferedReader = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            output.append(sb.toString());
            Toast.makeText(this, "Text is : " + sb.toString(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public void onDeleteButtonClick(View view) {

        // getting the file directory using the file name
        File file = new File(getFilesDir(),FILE_NAME);
        // checking the existence of the file in internal storage
        if (file.exists()){

            // if exist delete it
            deleteFile(FILE_NAME);
            Toast.makeText(this, "File deleted", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "File does not exist", Toast.LENGTH_SHORT).show();
        }
    }
}
