package com.example.mpandroidcharttest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mpandroidcharttest.R;
import com.example.mpandroidcharttest.util.BarChartSytleSetter;
import com.example.mpandroidcharttest.util.DataSetter;
import com.example.mpandroidcharttest.util.LineChartStyleSetter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerGamesStatisticsFragment extends Fragment {

    private RecyclerView mAnalysisRecyclerView;

    private Button mPlayerGamesAnalysis;

    private Button mPlayerTotalPointsAnalysis;

    private Button mPlayerAveragePointsAnalysis;

    private int mYears;

    private int mPlayerDatas;

    private static final String PLAYER_GAMES = "出场次数";

    private static final String PLAYER_TOTAL_POINTS = "赛季总得分";

    private static final String PLAYER_AVERAGE_POINTS = "赛季平均得分";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mYears = 10;
        mPlayerDatas = 80;
        DataSetter.setType(PLAYER_GAMES);

        View v = inflater.inflate(
                R.layout.fragment_player_games_statistics,
                container,
                false);

        mAnalysisRecyclerView = (RecyclerView) v.findViewById(R.id.analysis_recycler_view);
        mAnalysisRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final AnalysisAdapter adapter = new AnalysisAdapter();
        mAnalysisRecyclerView.setAdapter(adapter);

        mPlayerGamesAnalysis = (Button) v.findViewById(R.id.player_games_analysis);
        mPlayerGamesAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayerDatas = 80;
                DataSetter.setType(PLAYER_GAMES);
                adapter.notifyDataSetChanged();
            }
        });

        mPlayerTotalPointsAnalysis = (Button) v.findViewById(R.id.player_total_points_analysis);
        mPlayerTotalPointsAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayerDatas = 1200;
                DataSetter.setType(PLAYER_TOTAL_POINTS);
                adapter.notifyDataSetChanged();
            }
        });

        mPlayerAveragePointsAnalysis = (Button) v.findViewById(R.id.player_average_points_analysis);
        mPlayerAveragePointsAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayerDatas = 25;
                DataSetter.setType(PLAYER_AVERAGE_POINTS);
                adapter.notifyDataSetChanged();
            }
        });

        return v;
    }

    private class BarChartAnalysisHolder extends RecyclerView.ViewHolder {

        private BarChart mBarChart;

        public BarChartAnalysisHolder(View itemView) {
            super(itemView);

            mBarChart = (BarChart) itemView.findViewById(R.id.player_games_bar_chart);
        }
    }

    private class LineChartAnalysisHolder extends RecyclerView.ViewHolder {

        private LineChart mLineChart;

        public LineChartAnalysisHolder(View itemView) {
            super(itemView);

            mLineChart = (LineChart) itemView.findViewById(R.id.player_games_line_chart);
        }
    }

    private class AnalysisAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_BAR_CHART = 0;

        private static final int TYPE_LINE_CHART = 1;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_BAR_CHART) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_bar_chart, parent, false);
                BarChartAnalysisHolder barChartAnalysisHolder = new BarChartAnalysisHolder(view);
                return barChartAnalysisHolder;
            }
            if (viewType == TYPE_LINE_CHART) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_line_chart, parent, false);
                LineChartAnalysisHolder lineChartAnalysisHolder = new LineChartAnalysisHolder(view);
                return lineChartAnalysisHolder;
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof BarChartAnalysisHolder) {
                BarChart chart = ((BarChartAnalysisHolder) holder).mBarChart;
                BarChartSytleSetter.setBarChartStyle(chart, mYears);
                DataSetter.setBarData(chart, mYears, mPlayerDatas);
            }
            if (holder instanceof LineChartAnalysisHolder) {
                LineChart chart = ((LineChartAnalysisHolder) holder).mLineChart;
                LineChartStyleSetter.setLineChartStyle((((LineChartAnalysisHolder) holder).mLineChart), mYears);
                DataSetter.setLineData(chart, mYears, mPlayerDatas);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 0 ? TYPE_BAR_CHART : TYPE_LINE_CHART;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
