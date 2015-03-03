package com.playbasis.android.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.BadgeApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Badge;

import java.util.List;


public class BadgeActivity extends FragmentActivity {
    
    ListView listView;
    BadgeAdapter badgeAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listView_badge);
        badgeAdapter = new BadgeAdapter(this);
        listView.setAdapter(badgeAdapter);

        
        BadgeApi.badges(SampleApplication.playbasis, new OnResult<List<Badge>>() {
            @Override
            public void onSuccess(List<Badge> result) {
                badgeAdapter.setBadges(result);
                showProgress(false);
            }

            @Override
            public void onError(HttpError error) {
                Toast.makeText(BadgeActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                showProgress(false);
            }
        });
        showProgress(true);
    }


    private void showProgress(Boolean show){
        if(show){
            progressBar.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
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
