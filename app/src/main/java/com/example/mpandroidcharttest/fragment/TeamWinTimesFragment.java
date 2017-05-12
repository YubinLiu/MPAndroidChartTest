package com.example.mpandroidcharttest.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mpandroidcharttest.R;
import com.example.mpandroidcharttest.util.DataSetter;
import com.example.mpandroidcharttest.util.HorizontalBarChartStyleSetter;
import com.github.mikephil.charting.charts.HorizontalBarChart;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamWinTimesFragment extends Fragment {

    private HorizontalBarChart mTeamWinsRateBarChart;

    private static final String TEAM_TOTAL_WIN_RATE = "球队总胜率";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataSetter.setType(TEAM_TOTAL_WIN_RATE);
        View v = inflater.inflate(
                R.layout.fragment_team_win_times,
                container,
                false);

        mTeamWinsRateBarChart = (HorizontalBarChart) v.findViewById(R.id.team_total_win_rate);
        HorizontalBarChartStyleSetter.setHorizontalBarChartStyle(mTeamWinsRateBarChart, 30);
        DataSetter.setBarData(mTeamWinsRateBarChart, 30, 1);

        return v;
    }

}
