package com.ipl.adapter;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ipl.R;
import com.ipl.constants.Constants;
import com.ipl.view.TeamView;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by bridgeit007 on 25/5/16.
 */
public class TeamBindingAdapter {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
       /* DownloadImagesTask asyncTask=new DownloadImagesTask();
        asyncTask.execute(imageUrl);*/
    }

    @BindingAdapter({"bind:playerImageUrl"})
    public static void loadPlayerImage(ImageView view, String playerImageUrl) {
        Picasso.with(view.getContext())
                .load(playerImageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
       /* DownloadImagesTask asyncTask=new DownloadImagesTask();
        asyncTask.execute(imageUrl);*/
    }
   /* @BindingAdapter({"bind:imageBitmap"})
    public static void loadImage(ImageView view, Bitmap imageBitmap) {
        view.setImageBitmap(imageBitmap);
    }


    @BindingAdapter({"bind:backgroundImgUrl"})
    public void loadBackgroundImg(LinearLayout layout, String backgroundImgUrl) {
        DownloadImagesTask asyncTask=new DownloadImagesTask();
        asyncTask.execute(backgroundImgUrl);
    }

    public static class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            return download_Image(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            Drawable dr=new BitmapDrawable(result);

        }


        private Bitmap download_Image(String url) {
            Bitmap bm = null;
            try {
                URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            } catch (IOException e) {
                Log.e("Hub", "Error getting the image from server : " + e.getMessage().toString());
            }
            return bm;
        }
    }*/
}
