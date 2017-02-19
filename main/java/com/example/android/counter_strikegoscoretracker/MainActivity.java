package com.example.android.counter_strikegoscoretracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.android.counter_strikegoscoretracker.R.id.t5k;
import static com.example.android.counter_strikegoscoretracker.R.id.tip;

public class MainActivity extends AppCompatActivity {

    int scoreT = 0;
    int scoreCT = 0;
    int round = 1;
    int rowTRounds = 0;
    int rowCTRounds = 0;
    String team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pickT(View v) {
        team = "Terrorists1";
        View sidePick = findViewById(R.id.pick_side_layout);
        sidePick.setVisibility(View.GONE);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setVisibility(View.VISIBLE);
        View tPlayer = findViewById(R.id.t_player);
        tPlayer.setVisibility(View.VISIBLE);
        View ctPlayer = findViewById(R.id.ct_player);
        ctPlayer.setVisibility(View.GONE);
        displayTip(round);
        TextView tipTextView = (TextView) findViewById(R.id.tip);
        tipTextView.setText("gl hf");
        Button tNull = (Button) findViewById(R.id.t_null);
        tNull.setVisibility(View.INVISIBLE);
    }

    public void pickCT(View v) {
        team = "Counter-Terrorists1";
        View sidePick = findViewById(R.id.pick_side_layout);
        sidePick.setVisibility(View.GONE);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setVisibility(View.VISIBLE);
        View ctPlayer = findViewById(R.id.ct_player);
        ctPlayer.setVisibility(View.VISIBLE);
        View tPlayer = findViewById(R.id.t_player);
        tPlayer.setVisibility(View.GONE);
        displayTip(round);
        TextView tipTextView = (TextView) findViewById(tip);
        tipTextView.setText("gl hf");
        Button tNull = (Button) findViewById(R.id.t_null);
        tNull.setVisibility(View.INVISIBLE);
    }

    public void roundUpdate() {

        round += 1;
        String verdict;

        if (scoreT == 16) {
            String winner = "Terrorists2";
            if (winner == team) {
                verdict = "You Won! Congrats!";
            } else {
                verdict = "You Lost! Maybe Next Time!";
            }
            clearButtons(verdict);
            resetRoundHistory();
            return;
        }

        if (scoreCT == 16) {
            String winner = "Counter-Terrorists2";
            if (winner == team) {
                verdict = "You Won! Congrats!";
            } else {
                verdict = "You Lost! Maybe Next Time!";
            }
            clearButtons(verdict);
            resetRoundHistory();
            return;
        }

        if (round == 16) {
            if (team == "Terrorists1") {
                View ctPlayer = findViewById(R.id.ct_player);
                ctPlayer.setVisibility(View.VISIBLE);
                View tPlayer = findViewById(R.id.t_player);
                tPlayer.setVisibility(View.GONE);
                team = "Counter-Terrorists2";
            }
            if (team == "Counter-Terrorists1") {
                View tPlayer = findViewById(R.id.t_player);
                tPlayer.setVisibility(View.VISIBLE);
                View ctPlayer = findViewById(R.id.ct_player);
                ctPlayer.setVisibility(View.GONE);
                team = "Terrorists2";
            }

            TextView teamTScoreView = (TextView) findViewById(R.id.team_a_score);
            TextView teamCTScoreView = (TextView) findViewById(R.id.team_b_score);
            scoreT = Integer.valueOf(teamCTScoreView.getText().toString());
            scoreCT = Integer.valueOf(teamTScoreView.getText().toString());

            displayForTeamT(scoreT);
            displayForTeamCT(scoreCT);

            rowCTRounds = 0;
            rowTRounds = 0;
            resetBonus();
            resetRoundHistory();
        }

        if (round == 31) {
            verdict = "You Draw!";
            clearButtons(verdict);
            resetRoundHistory();
            return;
        }

        TextView roundView = (TextView) findViewById(R.id.roundCount);
        roundView.setText("Round: " + String.valueOf(round) + "/30");

        displayTip(round);
    }

    public void displayTip(int round) {

        String tip;

        switch (round) {
            case 15:
                tip = "Last round! Don't save money.";
                break;
            case 30:
                tip = "Last round! Don't save money.";
                break;
            default:
                tip = "";
        }

        TextView tipTextView = (TextView) findViewById(R.id.tip);
        tipTextView.setText(tip);
    }

    public void clearButtons(String message) {

        Button tBtn1 = (Button) findViewById(t5k);
        tBtn1.setVisibility(View.GONE);
        Button tBtn2 = (Button) findViewById(R.id.t_bomb);
        tBtn2.setVisibility(View.GONE);
        Button tBtn3 = (Button) findViewById(R.id.t_null);
        tBtn3.setVisibility(View.GONE);
        Button ctBtn1 = (Button) findViewById(R.id.ct5k);
        ctBtn1.setVisibility(View.GONE);
        Button ctBtn2 = (Button) findViewById(R.id.ct_defuse);
        ctBtn2.setVisibility(View.GONE);
        Button ctBtn3 = (Button) findViewById(R.id.ct_time);
        ctBtn3.setVisibility(View.GONE);
        TextView tBonusView = (TextView) findViewById(R.id.team_a_bonus);
        tBonusView.setVisibility(View.GONE);
        TextView ctBonusView = (TextView) findViewById(R.id.team_b_bonus);
        ctBonusView.setVisibility(View.GONE);

        TextView roundView = (TextView) findViewById(R.id.roundCount);
        roundView.setText(message);

        TextView tipTextView = (TextView) findViewById(tip);
        tipTextView.setText("gg wp");

        TextView teamAEcoBonusView = (TextView) findViewById(R.id.team_a_2eco_bonus);
        teamAEcoBonusView.setText("");
        teamAEcoBonusView.setVisibility(View.GONE);
        TextView teamBEcoBonusView = (TextView) findViewById(R.id.team_b_2eco_bonus);
        teamBEcoBonusView.setText("");
        teamBEcoBonusView.setVisibility(View.GONE);
        TextView teamAEcoView = (TextView) findViewById(R.id.team_a_eco_header);
        teamAEcoView.setVisibility(View.GONE);
        TextView teamBEcoView = (TextView) findViewById(R.id.team_b_eco_header);
        teamBEcoView.setVisibility(View.GONE);

    }

    public void displayForTeamT(int scoreT) {

        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreT));
    }

    public void displayForTeamCT(int scoreCT) {

        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreCT));
    }

    public void displayBonusT(int bomb, int bombPlant) {

        int tBonus;
        int t2EcoBonus;

        if (rowCTRounds == 0) {
            tBonus = 3250 + bomb;

            TextView dbEcoBonusView = (TextView) findViewById(R.id.team_a_2eco_bonus);
            dbEcoBonusView.setText("");
        } else if (rowCTRounds >= 5) {
            tBonus = 3400 + bombPlant;
            t2EcoBonus = tBonus + 3400;

            TextView dbEcoBonusView = (TextView) findViewById(R.id.team_a_2eco_bonus);
            dbEcoBonusView.setText("$" + String.valueOf(t2EcoBonus));
        } else {
            tBonus = 900 + 500 * rowCTRounds + bombPlant;
            t2EcoBonus = tBonus + (900 + 500 * (rowCTRounds + 1));

            TextView dbEcoBonusView = (TextView) findViewById(R.id.team_a_2eco_bonus);
            dbEcoBonusView.setText("$" + String.valueOf(t2EcoBonus));
        }

        TextView tBonusView = (TextView) findViewById(R.id.team_a_bonus);
        tBonusView.setText("$" + String.valueOf(tBonus));
    }

    public void updateRoundHistoryT(int bomb) {
        String source;
        int srcT;
        int historyRound = round;

        if (round > 15) {
            historyRound = round - 15;
        }


        if (bomb == 250) {
            source = "@drawable/icon_c4_wh";
            srcT = getResources().getIdentifier(source, "id", getPackageName());
        } else {
            source = "@drawable/icon_kill_wh";
            srcT = getResources().getIdentifier(source, "id", getPackageName());
        }

        String roundImageResId = "t_round" + (historyRound);
        int resId = getResources().getIdentifier(roundImageResId, "id", getPackageName());

        ImageView roundImage = (ImageView) findViewById(resId);
        roundImage.setImageResource(srcT);
    }

    public void resetRoundHistory() {

        for (int i = 1; i < 16; i++) {
            String tRoundImageResId = "t_round" + (i);
            int tResId = getResources().getIdentifier(tRoundImageResId, "id", getPackageName());
            ImageView tRoundImage = (ImageView) findViewById(tResId);
            tRoundImage.setImageResource(0);

            String ctRoundImageResId = "ct_round" + (i);
            int ctResId = getResources().getIdentifier(ctRoundImageResId, "id", getPackageName());
            ImageView ctRoundImage = (ImageView) findViewById(ctResId);
            ctRoundImage.setImageResource(0);
        }
    }

    public void updateRoundHistoryCT(int defuse, boolean outOfTime) {
        String source;
        int srcCT;
        int historyRound = round;

        if (round > 15) {
            historyRound = round - 15;
        }


        if (defuse == 250) {
            source = "@drawable/icon_defuser_wh";
            srcCT = getResources().getIdentifier(source, "id", getPackageName());
        } else if (outOfTime == true) {
            source = "@drawable/icon_time_wh";
            srcCT = getResources().getIdentifier(source, "id", getPackageName());
        } else {
            source = "@drawable/icon_kill_wh";
            srcCT = getResources().getIdentifier(source, "id", getPackageName());
        }

        String roundImageResId = "ct_round" + (historyRound);
        int resId = getResources().getIdentifier(roundImageResId, "id", getPackageName());

        ImageView roundImage = (ImageView) findViewById(resId);
        roundImage.setImageResource(srcCT);
    }

    public void displayBonusCT(int defuse) {

        int ctBonus;
        int ct2EcoBonus;

        if (rowTRounds == 0) {
            ctBonus = 3250 + defuse;

            TextView dbEcoBonusView = (TextView) findViewById(R.id.team_b_2eco_bonus);
            dbEcoBonusView.setText("");
        } else if (rowTRounds >= 5) {
            ctBonus = 3400;
            ct2EcoBonus = ctBonus * 2;

            TextView dbEcoBonusView = (TextView) findViewById(R.id.team_b_2eco_bonus);
            dbEcoBonusView.setText("$" + String.valueOf(ct2EcoBonus));
        } else {
            ctBonus = 900 + 500 * rowTRounds;
            ct2EcoBonus = ctBonus * 2 + 500;

            TextView dbEcoBonusView = (TextView) findViewById(R.id.team_b_2eco_bonus);
            dbEcoBonusView.setText("$" + String.valueOf(ct2EcoBonus));
        }

        TextView scoreView = (TextView) findViewById(R.id.team_b_bonus);
        scoreView.setText("$" + String.valueOf(ctBonus));
    }

    public void resetBonus() {
        TextView tBonusView = (TextView) findViewById(R.id.team_a_bonus);
        tBonusView.setText("$800");
        TextView teamAEcoBonusView = (TextView) findViewById(R.id.team_a_2eco_bonus);
        teamAEcoBonusView.setText("");
        TextView ctBonusView = (TextView) findViewById(R.id.team_b_bonus);
        ctBonusView.setText("$800");
        TextView teamBEcoBonusView = (TextView) findViewById(R.id.team_b_2eco_bonus);
        teamBEcoBonusView.setText("");
    }

    public void visitWebGuide(View v) {

        Uri webpage = Uri.parse("http://clutchround.com/csgo-economy-system-explained/");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void t5k(View v) {
        scoreT += 1;
        rowTRounds += 1;
        rowCTRounds = 0;
        int bombBonus = 0;
        int bombDefuse = 0;
        int defuse = 0;

        updateRoundHistoryT(bombBonus);
        displayForTeamT(scoreT);
        displayBonusT(bombBonus, bombDefuse);
        displayBonusCT(defuse);
        roundUpdate();
    }

    public void tBomb(View v) {
        scoreT += 1;
        rowTRounds += 1;
        rowCTRounds = 0;
        int bombBonus = 250;
        int bombDefuse = 0;
        int defuse = 0;

        updateRoundHistoryT(bombBonus);
        displayForTeamT(scoreT);
        displayBonusT(bombBonus, bombDefuse);
        displayBonusCT(defuse);
        roundUpdate();
    }

    public void ct5k(View v) {
        scoreCT += 1;
        rowCTRounds += 1;
        rowTRounds = 0;
        int bombBonus = 0;
        int bombPlant = 0;
        int defuse = 0;
        boolean hasTimeRanOut = false;

        updateRoundHistoryCT(defuse, hasTimeRanOut);
        displayForTeamCT(scoreCT);
        displayBonusT(bombBonus, bombPlant);
        displayBonusCT(defuse);
        roundUpdate();
    }

    public void ctDefuse(View v) {
        scoreCT += 1;
        rowCTRounds += 1;
        rowTRounds = 0;
        int bombBonus = 0;
        int bombPlant = 800;
        int defuse = 250;
        boolean hasTimeRanOut = false;

        updateRoundHistoryCT(defuse, hasTimeRanOut);
        displayForTeamCT(scoreCT);
        displayBonusT(bombBonus, bombPlant);
        displayBonusCT(defuse);
        roundUpdate();
    }

    public void ctTime(View v) {
        scoreCT += 1;
        rowCTRounds += 1;
        rowTRounds = 0;
        int bombBonus = 0;
        int bombPlant = 0;
        int defuse = 0;
        boolean timeRanOut = true;

        updateRoundHistoryCT(defuse, timeRanOut);
        displayForTeamCT(scoreCT);
        displayBonusT(bombBonus, bombPlant);
        displayBonusCT(defuse);
        roundUpdate();
    }

    public void resetScore(View v) {
        scoreT = 0;
        scoreCT = 0;
        round = 0;
        rowCTRounds = 0;
        rowTRounds = 0;
        displayForTeamT(scoreT);
        displayForTeamCT(scoreCT);
        roundUpdate();
        resetBonus();
        resetRoundHistory();

        View sidePick = findViewById(R.id.pick_side_layout);
        sidePick.setVisibility(View.VISIBLE);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setVisibility(View.GONE);

        Button tBtn1 = (Button) findViewById(t5k);
        tBtn1.setVisibility(View.VISIBLE);
        Button tBtn2 = (Button) findViewById(R.id.t_bomb);
        tBtn2.setVisibility(View.VISIBLE);
        Button tBtn3 = (Button) findViewById(R.id.t_null);
        tBtn3.setVisibility(View.GONE);
        Button ctBtn1 = (Button) findViewById(R.id.ct5k);
        ctBtn1.setVisibility(View.VISIBLE);
        Button ctBtn2 = (Button) findViewById(R.id.ct_defuse);
        ctBtn2.setVisibility(View.VISIBLE);
        Button ctBtn3 = (Button) findViewById(R.id.ct_time);
        ctBtn3.setVisibility(View.VISIBLE);
        TextView tBonusView = (TextView) findViewById(R.id.team_a_bonus);
        tBonusView.setVisibility(View.VISIBLE);
        TextView ctBonusView = (TextView) findViewById(R.id.team_b_bonus);
        ctBonusView.setVisibility(View.VISIBLE);

        TextView teamAEcoBonusView = (TextView) findViewById(R.id.team_a_2eco_bonus);
        teamAEcoBonusView.setVisibility(View.VISIBLE);
        TextView teamBEcoBonusView = (TextView) findViewById(R.id.team_b_2eco_bonus);
        teamBEcoBonusView.setVisibility(View.VISIBLE);
        TextView teamAEcoView = (TextView) findViewById(R.id.team_a_eco_header);
        teamAEcoView.setVisibility(View.VISIBLE);
        TextView teamBEcoView = (TextView) findViewById(R.id.team_b_eco_header);
        teamBEcoView.setVisibility(View.VISIBLE);
    }

}