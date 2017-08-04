package com.example.mounika.project;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Mounika on 8/1/2017.
 */

public class Filesinterface {
    final static String fileName = "data.txt";
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com/example/mounika/project" ;
    final static String TAG = Filesinterface.class.getName();

    public static  String ReadFile( Context context)
    {
        String textentered = null;

        try {
            FileInputStream fileinput = new FileInputStream (new File(path+ fileName));
            InputStreamReader inputread = new InputStreamReader(fileinput);
            BufferedReader readingbuffer = new BufferedReader(inputread);
            StringBuilder builderstring = new StringBuilder();

            while ( (textentered = readingbuffer.readLine()) != null )
            {
                builderstring.append(textentered+ System.getProperty("textentered.separator"));
            }
            fileinput.close();
            textentered = builderstring.toString();

            readingbuffer.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return textentered;
    }

    public static boolean saveToFile( String data){
        try {
            new File(path  ).mkdir();
            File file_name = new File(path+ fileName);
            if (!file_name.exists())
            {
                file_name.createNewFile();
            }
            FileOutputStream opstream = new FileOutputStream(file_name,true);
            opstream.write((data + System.getProperty("textentered.separator")).getBytes());

            return true;
        }

        catch(FileNotFoundException ex)
        {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;


    }

}
