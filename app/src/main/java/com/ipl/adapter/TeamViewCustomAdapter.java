package com.ipl.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ipl.R;
import com.ipl.databinding.IplTeamRecyclerviewBinding;
import com.ipl.viewmodel.TeamViewModel;

import java.util.ArrayList;

/**
 * Created by bridgeit007 on 23/5/16.
 */
public class TeamViewCustomAdapter extends RecyclerView.Adapter<TeamViewCustomAdapter.TeamViewHolder> {
    Context context;
    ArrayList<TeamViewModel> mTeamData;

    public TeamViewCustomAdapter(Context context, ArrayList<TeamViewModel> mTeamData) {
        this.context=context;
        this.mTeamData=mTeamData;
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IplTeamRecyclerviewBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.ipl_team_recyclerview,parent,false);
        return new TeamViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        holder.bindConnection(mTeamData.get(position));
    }

    @Override
    public int getItemCount() {
        return mTeamData.size();
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        IplTeamRecyclerviewBinding binding;
        public TeamViewHolder(IplTeamRecyclerviewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bindConnection(TeamViewModel viewModel){
            binding.setInformation(viewModel);
        }
    }
}
