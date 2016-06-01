package com.ipl.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ipl.R;
import com.ipl.adapter.TeamViewCustomAdapter;
import com.ipl.controller.TeamViewController;
import com.ipl.localdatabase.DBHandler;
import com.ipl.util.InternetConnection;
import com.ipl.viewmodel.TeamViewModel;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 23/5/16.
 */
public class TeamView extends AppCompatActivity {
    RecyclerView mRecyclerView;
    TeamViewCustomAdapter mAdapter;
    DBHandler mDbHandler;
    TeamViewController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialization of instances
        mRecyclerView = (RecyclerView) findViewById(R.id.team_recycler_view);

        mDbHandler = new DBHandler(TeamView.this);
        controller = new TeamViewController(TeamView.this);

        LoadContentData contentData = new LoadContentData();
        contentData.execute();
    }

    class LoadContentData extends AsyncTask<Void, Void, ArrayList<TeamViewModel>> {

        public ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(TeamView.this);
            if (InternetConnection.checkInternetConnection(TeamView.this)) {
                mProgressDialog.setMessage("Loading");
                mProgressDialog.setTitle("please wait");
                mProgressDialog.show();
            }else {
                InternetConnection.noInternetAlertDialog(TeamView.this);
            }

        }

        @Override
        protected ArrayList<TeamViewModel> doInBackground(Void... params) {
            ArrayList<TeamViewModel> teamViewModels = controller.getTeamViewModelData();
            return teamViewModels;
        }

        @Override
        protected void onPostExecute(ArrayList<TeamViewModel> teamViewModels) {

            //creating an IPLTeamViewCustomAdapter instance and set the adapter with dummy data
            mAdapter = new TeamViewCustomAdapter(TeamView.this, teamViewModels);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(TeamView.this));
            mRecyclerView.setAdapter(mAdapter);
            mProgressDialog.hide();
        }
    }
}
//Check for data is available in DB
       /* if (mDbHandler.dataCheckFromDB()){
            mTeamData=JSONParser.jsonParserForDB(mDbHandler.getTeamInfoDatabase());

            //creating an IPLTeamViewCustomAdapter instance and set the adapter with dummy data
            mAdapter = new TeamViewCustomAdapter(TeamView.this,mTeamData);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(TeamView.this));
            mRecyclerView.setAdapter(mAdapter);
        }else {
        CustomAsyncTask customAsyncTask=new CustomAsyncTask();
        customAsyncTask.execute(Constants.TEAM_VIEW_URL);
        }*/
       /* CustomAsyncTask customAsyncTask=new CustomAsyncTask();
        customAsyncTask.execute(Constants.TEAM_VIEW_URL);*/


    /*public class CustomAsyncTask extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String content= HttpManager.getData(params[0]);
            return content;
            //return convertStreamToString(content);
        }

        @Override
        protected void onPostExecute(String result) {
            mTeamData= JSONParser.parseTeamViewModelContent(result,getApplicationContext());

            //creating an IPLTeamViewCustomAdapter instance and set the adapter with dummy data
            mAdapter = new TeamViewCustomAdapter(TeamView.this,mTeamData);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(TeamView.this));
            mRecyclerView.setAdapter(mAdapter);

           *//* mAdapterWithoutBinding = new TeamViewCustomAdapterWithoutDataBinding(TeamView.this,mTeamData);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(TeamView.this));
            mRecyclerView.setAdapter(mAdapterWithoutBinding);*//*
        }

    }*/


