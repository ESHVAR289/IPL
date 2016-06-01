package com.ipl.util;

import android.content.Context;
import android.util.Log;

import com.ipl.CallbackInterface.CallbackDownloadImg;
import com.ipl.localdatabase.DBHandler;
import com.ipl.model.TeamModel;
import com.ipl.viewmodel.TeamViewModel;
import com.ipl.constants.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by bridgelabz on 14/05/16.
 *
 * 1)This is the utility class which we can use to get parsed json data
 *   from the JSONArray.
 * 2)There are two methods parseTeamViewModelContent() and parseTeamPlayerViewModelContent()
 *   to which we are passing the JSONArray as a parameter and it will return the
 *   List<IPLTeamViewModel> and List<IPLTeamPlayerViewModel> respectively.
 *
 */
public class JSONParser {
    public static ArrayList<TeamModel> parseTeamModelContent(String content, Context applicationContext){
        ArrayList<TeamModel> teamModelData= new ArrayList<>();
        final DBHandler handler=new DBHandler(applicationContext);
        try {
            //--get the JSONArray from the content
            JSONArray jsonArray=new JSONArray(content);

            handler.deleteDB();
            //--getting all the JSONObject from the JSONArray by passing JSONArray position.
            for (int i=0;i<jsonArray.length();i++){
                TeamModel teamModel=new TeamModel();
                final JSONObject jsonObject=jsonArray.getJSONObject(i);
                //--setting the TeamModel data by using setTeamViewData()
                teamModel.setTeamViewData(jsonObject);

                //Method to convert image data into byte[] and stored it into the database
                HttpManager.getImageBitmap(jsonObject.getString(Constants.KEY_TEAM_IMG_URL), new CallbackDownloadImg() {
                    @Override
                    public void onSuccess(byte[] result) {
                        //Inserting data into the DB
                        try {
                            handler.insertTeamDataIntoDB(jsonObject.getString(Constants.KEY_TEAM_NAME),
                            jsonObject.getString(Constants.KEY_TEAM_OWNER),
                            jsonObject.getString(Constants.KEY_TEAM_CAPTAIN),
                            jsonObject.getString(Constants.KEY_TEAM_COACH),
                            jsonObject.getString(Constants.KEY_TEAM_HOME_VENUE)
                            ,result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(String result) {
                        Log.e("Error","Error, while storing the database information.");
                    }
                });
                //--adding the teamData inside the List
                teamModelData.add(teamModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }finally {
            return teamModelData;
        }
    }
    public static ArrayList<TeamViewModel> parseTeamViewModelContent(String content, Context applicationContext){
        ArrayList<TeamViewModel> teamData= new ArrayList<>();
        final DBHandler handler=new DBHandler(applicationContext);
        try {
            //--get the JSONArray from the content
            JSONArray jsonArray=new JSONArray(content);


            //--getting all the JSONObject from the JSONArray by passing JSONArray position.
            for (int i=0;i<jsonArray.length();i++){
                final JSONObject jsonObject=jsonArray.getJSONObject(i);
                TeamViewModel teamViewModel=new TeamViewModel();

                teamViewModel.setmTeamName(jsonObject.getString(Constants.KEY_TEAM_NAME));
                teamViewModel.setmTeamCaptain(jsonObject.getString(Constants.KEY_TEAM_CAPTAIN));
                teamViewModel.setmTeamCoach(jsonObject.getString(Constants.KEY_TEAM_COACH));
                teamViewModel.setmTeamHomeVenue(jsonObject.getString(Constants.KEY_TEAM_HOME_VENUE));
                teamViewModel.setmTeamOwner(jsonObject.getString(Constants.KEY_TEAM_OWNER));

                //Method to convert image data into byte[] and stored it into the database
                HttpManager.getImageBitmap(jsonObject.getString(Constants.KEY_TEAM_IMG_URL), new CallbackDownloadImg() {
                    @Override
                    public void onSuccess(byte[] result) {
                        //Inserting data into the DB
                        try {
                            handler.insertTeamDataIntoDB(jsonObject.getString(Constants.KEY_TEAM_NAME),
                                    jsonObject.getString(Constants.KEY_TEAM_OWNER),
                                    jsonObject.getString(Constants.KEY_TEAM_CAPTAIN),
                                    jsonObject.getString(Constants.KEY_TEAM_COACH),
                                    jsonObject.getString(Constants.KEY_TEAM_HOME_VENUE)
                                    ,result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(String result) {
                        Log.e("Error","Error, while storing the database information.");
                    }
                });
                //--adding the teamData inside the List
                teamData.add(teamViewModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }finally {
            return teamData;
        }
    }

    //--Method to Retrieving ArrayList<TeamViewModel> by passing the jsonArray
    public static ArrayList<TeamModel> jsonParserForDB(JSONArray jsonArray){
        ArrayList<TeamModel> teamModelData= new ArrayList<>();
        try {
            //--getting all the JSONObject from the JSONArray by passing JSONArray position.
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);

                TeamModel teamModel=new TeamModel();
                //--setting the TeamModel data by using setTeamViewData()
                teamModel.setTeamViewData(jsonObject);

                //--adding the teamData inside the List
                teamModelData.add(teamModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }finally {
            return teamModelData;
        }
    }

   /* //--Method to Retrieving ArrayList<TeamViewModel> by passing the jsonArray
    public static ArrayList<TeamViewModel> jsonParserForDB(JSONArray jsonArray){
        ArrayList<TeamViewModel> teamData= new ArrayList<>();
        try {
             //--getting all the JSONObject from the JSONArray by passing JSONArray position.
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                TeamViewModel teamViewModel=new TeamViewModel();

                teamViewModel.setmTeamName(jsonObject.getString(Constants.KEY_TEAM_NAME));
                teamViewModel.setmTeamCaptain(jsonObject.getString(Constants.KEY_TEAM_CAPTAIN));
                teamViewModel.setmTeamCoach(jsonObject.getString(Constants.KEY_TEAM_COACH));
                teamViewModel.setmTeamHomeVenue(jsonObject.getString(Constants.KEY_TEAM_HOME_VENUE));
                teamViewModel.setmTeamOwner(jsonObject.getString(Constants.KEY_TEAM_OWNER));
               // byte[] imageBitmap=DBHandler.
               // teamViewModel.setImageBitmap();
               // teamViewModel.setmImageUrl(jsonObject.getString(Constants.KEY_TEAM_IMG_URL));

                //--adding the teamData inside the List
                teamData.add(teamViewModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }finally {
            return teamData;
        }
    }*/



}
