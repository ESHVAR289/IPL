package com.ipl.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ipl.adapter.TeamViewCustomAdapter;
import com.ipl.constants.Constants;
import com.ipl.localdatabase.DBHandler;
import com.ipl.model.TeamModel;
import com.ipl.util.HttpManager;
import com.ipl.util.InternetConnection;
import com.ipl.util.JSONParser;
import com.ipl.view.TeamView;
import com.ipl.viewmodel.TeamViewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by bridgeit007 on 25/5/16.
 * *
 * purpose:
 *
 * 1.It is data controller in MVVM.
 * 2.interact with local db and cloud interaction.
 * 3.it provide interface for VM to interact with DbController,it abstract the
 * DBHandler,model and service.
 * 4.Controller controls the flow of data between the models,DB handler and
 * services.it will acts as manager.
 * 5.controller will get the data from server then controller will handle database call .
 * then controller will call database and give required attributes to viewModel.
 *
 */
public class TeamViewController {
    File directory;
    Context context;
    RecyclerView mRecyclerView;
    TeamViewCustomAdapter mAdapter;
    ArrayList<TeamModel> mTeamModelList;
    ArrayList<TeamViewModel> mTeamDataList;

    TeamViewModel mTeamViewModel;
    TeamModel mTeamModel;

    DBHandler mDbHandler;

    public TeamViewController(Context context) {
        this.context = context;
        mDbHandler=new DBHandler(context);
        mTeamModelList=new ArrayList<>();
        mTeamDataList=new ArrayList<>();
    }

    public ArrayList<TeamViewModel> getTeamViewModelData(){
        if ((! InternetConnection.checkInternetConnection(context)) && mDbHandler.dataCheckFromDB()){
            ArrayList<TeamModel> teamInfoModel=getTeamInfoDataFromDB();

                for (int i=0;i<teamInfoModel.size();i++) {
                    mTeamViewModel = new TeamViewModel();
                    mTeamViewModel.setmTeamName(teamInfoModel.get(i).mTeamName);
                    mTeamViewModel.setmTeamOwner(teamInfoModel.get(i).mTeamOwner);
                    mTeamViewModel.setmTeamCaptain(teamInfoModel.get(i).mTeamCaptain);
                    mTeamViewModel.setmTeamCoach(teamInfoModel.get(i).mTeamCoach);
                    mTeamViewModel.setmTeamHomeVenue(teamInfoModel.get(i).mTeamHomeVenue);

                    mTeamDataList.add(mTeamViewModel);
                }
            return mTeamDataList;
        }else {
            ArrayList<TeamModel> teamInfoList = getTeamInfoData();
            for (int i = 0; i < teamInfoList.size(); i++) {
                mTeamViewModel = new TeamViewModel();
                mTeamViewModel.setmTeamName(teamInfoList.get(i).mTeamName);
                mTeamViewModel.setmTeamOwner(teamInfoList.get(i).mTeamOwner);
                mTeamViewModel.setmTeamCaptain(teamInfoList.get(i).mTeamCaptain);
                mTeamViewModel.setmTeamCoach(teamInfoList.get(i).mTeamCoach);
                mTeamViewModel.setmTeamHomeVenue(teamInfoList.get(i).mTeamHomeVenue);
                mTeamViewModel.setmImageUrl(teamInfoList.get(i).mTeamLogoUrl);
                mTeamDataList.add(mTeamViewModel);
            }
            return mTeamDataList;
        }

    }

    //--Getting the ArrayList<TeamModel> by passing the url and content to the parseTeamModelContent();
    private ArrayList<TeamModel> getTeamInfoData() {

        mTeamModelList=JSONParser.parseTeamModelContent(HttpManager.getData(Constants.TEAM_VIEW_URL),context);
        return mTeamModelList;
    }

    private ArrayList<TeamModel> getTeamInfoDataFromDB(){
        mTeamModelList=JSONParser.jsonParserForDB(mDbHandler.getTeamInfoDatabase());
        return mTeamModelList;
    }

    //Convert imageUrl to Image
    public void urlToImageConversion(String imageLink, String ImageName) {
        try {
            URL url = new URL(imageLink);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection()
                    .getInputStream());
            storeImageInFile(bitmap, ImageName);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //store image in sdcard
    public void storeImageInFile(Bitmap bitmap, String ImageName) {
        String root = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        try {
            directory = new File(root + "/Images");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            OutputStream fOut = null;

            File myPath = new File(directory, ImageName);
            if (myPath.exists())
                myPath.delete();
            myPath.createNewFile();
            fOut = new FileOutputStream(myPath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, fOut);
            fOut.flush();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
