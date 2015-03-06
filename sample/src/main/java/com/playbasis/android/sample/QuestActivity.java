package com.playbasis.android.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.GoodsApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.QuestApi;
import com.playbasis.android.playbasissdk.api.RedeemApi;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.toolbox.NetworkImageView;
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.Quest;
import com.playbasis.android.playbasissdk.model.RedeemGood;

import java.util.List;


public class QuestActivity extends FragmentActivity {


    ListView listView;
    QuestAdapter questAdapter;
    ProgressBar progressBar;
    LinearLayout lQuestDetails;
    TextView vQuestName;
    TextView vQuestDescription;
    NetworkImageView vQuestImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        //Create view
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listView_quest);
        lQuestDetails = (LinearLayout) findViewById(R.id.linearLayout_quest_details);
        vQuestName = (TextView) findViewById(R.id.textView_quest_name);
        vQuestDescription = (TextView) findViewById(R.id.textView_quest_description);
        vQuestImage = (NetworkImageView) findViewById(R.id.imageView_quest);
        
        questAdapter = new QuestAdapter(this);
        listView.setAdapter(questAdapter);

        //Quet the quest
        QuestApi.info(SampleApplication.playbasis, "54f92c69be120b97658b45d0", new OnResult<Quest>() {
            @Override
            public void onSuccess(Quest result) {
                //Display quest information into view
                vQuestName.setText(result.getQuestName());
                vQuestDescription.setText(result.getDescription());
                vQuestImage.setImageUrl(result.getImage(), SampleApplication.playbasis.getHttpManager().getImageLoader());
                questAdapter.setMissions(result.getMissions());//Send the missions to the mission adapter
                showProgress(false); // dismiss progress dialog
            }

            @Override
            public void onError(HttpError error) {
                //Display error toast
                Toast.makeText(QuestActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                showProgress(false);// dismiss progress dialog
            }
        });
        showProgress(true);// show progress dialog
    }

    //Show or hide progress dialog
    private void showProgress(Boolean show){
        if(show){
            progressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
            lQuestDetails.setVisibility(View.INVISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            lQuestDetails.setVisibility(View.VISIBLE);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        SampleApplication.playbasis.setActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SampleApplication.playbasis.removeActivity();
    }

}
