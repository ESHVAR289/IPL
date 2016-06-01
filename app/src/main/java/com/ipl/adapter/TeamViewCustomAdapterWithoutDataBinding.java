package com.ipl.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ipl.R;
import com.ipl.databinding
        .TeamRowLayoutWithoutDatabindingBinding;
import com.ipl.viewmodel.TeamViewModel;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 24/5/16.
 */
public class TeamViewCustomAdapterWithoutDataBinding extends RecyclerView.Adapter<TeamViewCustomAdapterWithoutDataBinding.TeamViewHolder> {
    LayoutInflater mLayoutInflator;
    Context context;
    ArrayList<TeamViewModel> mTeamData;
    public TeamViewCustomAdapterWithoutDataBinding(Context  context, ArrayList<TeamViewModel> mTeamData) {
        this.context=context;
        this.mTeamData=mTeamData;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TeamRowLayoutWithoutDatabindingBinding item= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.team_row_layout_without_databinding,parent,false);

        return  new TeamViewHolder(item);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        holder.onBindConnection(mTeamData.get(position));
    }

    @Override
    public int getItemCount() {
        return mTeamData.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        TeamRowLayoutWithoutDatabindingBinding binding;
        public TeamViewHolder(TeamRowLayoutWithoutDatabindingBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void onBindConnection(TeamViewModel teamViewModel){
            binding.setTeamDetails(teamViewModel);
        }
    }

}
