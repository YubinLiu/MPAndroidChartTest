package com.example.mpandroidcharttest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mpandroidcharttest.R;

import java.util.ArrayList;
import java.util.List;

public class TestPlayerActivity extends AppCompatActivity {

    private Button mStartPlayerAnalysis;

    private Button mStartTeamAnalysis;

    private List<Bundle> playerBundleList = new ArrayList<>();

    private List<Bundle> teamBundleList = new ArrayList<>();

    private String[] playerBundleKeys = {"points", "games", "average", "season"};

    private String[] teamBundleKeys = {"team_names", "win_rate"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setPlayerBundleData();
        setTeamBundleData();

        mStartPlayerAnalysis = (Button) findViewById(R.id.start_player_analysis);
        mStartPlayerAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatisticsActivity.actionStart(
                        TestPlayerActivity.this,
                        playerBundleList,
                        StatisticsActivity.PLAYER_STATISTICS,
                        playerBundleKeys);
            }
        });

        mStartTeamAnalysis = (Button) findViewById(R.id.start_team_analysis);
        mStartTeamAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatisticsActivity.actionStart(
                        TestPlayerActivity.this,
                        teamBundleList,
                        StatisticsActivity.TEAM_STATISTICS,
                        teamBundleKeys);
            }
        });

    }

    public void setPlayerBundleData() {
        for (int i = 0; i < 20; i++) {
            Bundle bundle = new Bundle();
            bundle.putFloat("points", 10 * i + 1000);
            bundle.putFloat("games", i + 40);
            bundle.putFloat("average", (5 * i + 1000) / (i + 40));
            bundle.putString("season", Integer.toString(2000 + i));
            playerBundleList.add(bundle);
        }
    }

    public void setTeamBundleData() {
        for (int i = 0; i < 30; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("team_names", "team" + i);
            bundle.putFloat("win_rate", (float) (i + 3.0) / 30);

            teamBundleList.add(bundle);
        }
    }
}
