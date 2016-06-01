package com.ipl.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;

import com.ipl.BR;


/**
 * Created by bridgeit007 on 23/5/16.
 */
public class TeamViewModel extends BaseObservable{
    //view attributes
    public String mTeamName;
    public String mTeamOwner;
    public String mTeamCaptain;
    public String mTeamCoach;
    public String mTeamHomeVenue;
    public String mImageUrl;
    public Bitmap mImageBitmap;
    public String mTeamBackgroundImg;

    @Bindable
    public String getmTeamName() {
        return mTeamName;
    }

    public void setmTeamName(String mTeamName) {
        this.mTeamName = mTeamName;
        notifyPropertyChanged(BR.mTeamName);
    }

    @Bindable
    public String getmTeamOwner() {
        return mTeamOwner;
    }

    public void setmTeamOwner(String mTeamOwner) {
        this.mTeamOwner = mTeamOwner;
        notifyPropertyChanged(BR.mTeamOwner);
    }

    @Bindable
    public String getmTeamCaptain() {
        return mTeamCaptain;
    }

    public void setmTeamCaptain(String mTeamCaptain) {
        this.mTeamCaptain = mTeamCaptain;
        notifyPropertyChanged(BR.mTeamCaptain);
    }

    @Bindable
    public String getmTeamCoach() {
        return mTeamCoach;
    }

    public void setmTeamCoach(String mTeamCoach) {
        this.mTeamCoach = mTeamCoach;
        notifyPropertyChanged(BR.mTeamCoach);
    }

    @Bindable
    public String getmTeamHomeVenue() {
        return mTeamHomeVenue;
    }

    public void setmTeamHomeVenue(String mTeamHomeVenue) {
        this.mTeamHomeVenue = mTeamHomeVenue;
        notifyPropertyChanged(BR.mTeamHomeVenue);
    }

    @Bindable
    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
        notifyPropertyChanged(BR.mImageUrl);
    }

    @Bindable
    public String getmTeamBackgroundImg() {
        return mTeamBackgroundImg;
    }

    public void setmTeamBackgroundImg(String mTeamBackgroundImg) {
        this.mTeamBackgroundImg = mTeamBackgroundImg;
        notifyPropertyChanged(BR.mTeamBackgroundImg);
    }

    @Bindable
    public Bitmap getImageBitmap() {
        return mImageBitmap;
    }

    public void setImageBitmap(Bitmap mImageBitmap) {
        this.mImageBitmap = mImageBitmap;
        notifyPropertyChanged(BR.imageBitmap);
    }
}
