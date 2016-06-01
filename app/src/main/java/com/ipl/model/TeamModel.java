package com.ipl.model;

import com.ipl.constants.Constants;

import org.json.JSONObject;

/**
 * Created by bridgeit007 on 25/5/16.
 */
public class TeamModel {
    public String mTeamName;
    public String mTeamCaptain;
    public String mTeamCoach;
    public String mTeamOwner;
    public String mTeamHomeVenue;
    public String mTeamLogoUrl;

    public void setTeamViewData(JSONObject jsonObject){
        //--Returns the value mapped by name if it exists, coercing it if necessary,
        // or the empty string if no such mapping exists
        mTeamName=jsonObject.optString(Constants.KEY_TEAM_NAME);
        mTeamCaptain=jsonObject.optString(Constants.KEY_TEAM_CAPTAIN);
        mTeamCoach=jsonObject.optString(Constants.KEY_TEAM_COACH);
        mTeamOwner=jsonObject.optString(Constants.KEY_TEAM_OWNER);
        mTeamHomeVenue=jsonObject.optString(Constants.KEY_TEAM_HOME_VENUE);
        mTeamLogoUrl=jsonObject.optString(Constants.KEY_TEAM_IMG_URL);
    }
}
