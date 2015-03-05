package com.playbasis.android.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.BadgeApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.Rule;
import com.playbasis.android.playbasissdk.model.RuleAction;
import com.playbasis.android.playbasissdk.model.UIEvent;
import com.playbasis.android.playbasissdk.widget.PlayerView;

import java.util.List;


public class BadgeActivity extends FragmentActivity {
    
    ProgressBar progressBar;
    LinearLayout layout;
    Button redeemBadge;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);

        // Create the view
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        redeemBadge = (Button) findViewById(R.id.button_redeem_badge);
        layout = (LinearLayout) findViewById(R.id.linearLayout_badge);
        
        //Set
        redeemBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress(true);
                SampleApplication.playbasis.Do("gregtestuser", UIEvent.CLICK,  new OnResult<Rule>() {
                    @Override
                    public void onSuccess(Rule result) {
                        BadgeWidget badgeWidget = new BadgeWidget();
                        badgeWidget.setBadge(result.getEvents().get(0).getRewardData());
                        badgeWidget.show(getSupportFragmentManager(), "fragment_player_info");
                        showProgress(false);
                    }

                    @Override
                    public void onError(HttpError error) {
                        Toast.makeText(BadgeActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        showProgress(false);
                    }
                });
            }
        });
        



        
    }


    private void showProgress(Boolean show){
        if(show){
            progressBar.setVisibility(View.VISIBLE);
            layout.setVisibility(View.INVISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
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
