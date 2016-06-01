package com.ipl.util;


import com.ipl.CallbackInterface.CallbackDownloadImg;
import com.ipl.constants.Constants;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bridgelabz on 21/04/16.
 */
public class HttpManager {
    public static String getData(String uri){
        URL url= null;
        try {
            url = new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader reader=null;
        try {
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();

            //StringBuilder is for to read the string line by line
            StringBuilder stringBuilder=new StringBuilder();

            InputStream in = new BufferedInputStream(connection.getInputStream());
            // InputStreamReader is=new InputStreamReader(connection.getInputStream());
            //reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String s=convertStreamToString(in);
            String line=null;
            /*while ((reader.readLine()) != null){
                stringBuilder.append(line);
            }*/
            //return  stringBuilder.toString();
            return  s;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void getImageBitmap(String imageUrl, final CallbackDownloadImg callbackDownloadImg)throws  Exception{
        DownloadImageBitmap imageBitmap=null;
        URL url=new URL(imageUrl);
        imageBitmap=new DownloadImageBitmap(url) {
            @Override
            protected void onPostExecute(byte[] result) {
            if (result != null)
                callbackDownloadImg.onSuccess(result);
                else
                callbackDownloadImg.onError(Constants.WEB_SERVICE_RESPONSE_NULL);
            }
        };
        imageBitmap.execute();
    }
    /*public static String getData(String uri){
        AndroidHttpClient client= AndroidHttpClient.newInstance("AndroidAgent");
        HttpGet request=new HttpGet(uri);
        HttpResponse response;

        try {
            response=client.execute(request);
            return EntityUtils.toString(response.getEntity());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            client.close();
        }
    }*/
    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
