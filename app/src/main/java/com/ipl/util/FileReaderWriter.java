package com.ipl.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by bridgelabz on 14/05/16.
 */
public class FileReaderWriter {
    public static File createCacheFile(Context context, String fileName, String json) {
        File cacheFile = new File(context.getFilesDir(), fileName);
        try {
            FileWriter fw = new FileWriter(cacheFile);
            /*BufferedWriter to write Text(Character data) to the file.
            *BufferedWriter can't communicate Directly with the file. It can communicate via some
            * writer object.
            * e.g. FileWriter
            */
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            // on exception null will be returned
            cacheFile = null;
        }

        return cacheFile;
    }

    public static String readFile(File file) {
        String fileContent = "";
        try {
            String currentLine;
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((currentLine = br.readLine()) != null) {
                fileContent += currentLine + '\n';
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            // on exception null will be returned
            fileContent = null;
        }
        return fileContent;
    }
}
