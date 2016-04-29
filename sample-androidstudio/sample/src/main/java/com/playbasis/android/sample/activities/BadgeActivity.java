package com.playbasis.android.sample.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.ApiConst;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Rule;
import com.playbasis.android.playbasissdk.model.UIEvent;
import com.playbasis.android.sample.BadgeWidget;
import com.playbasis.android.sample.R;
import com.playbasis.android.sample.SampleApplication;


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
        
        //Set onClick action
        redeemBadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress(true); // Display the progress bar
                SampleApplication.playbasis.Do("gregtestuser", UIEvent.CLICK,  new OnResult<Rule>() {
                    @Override
                    public void onSuccess(Rule result) {
                        //Show the badge DialogFragment
                        
                        if(result.getEvents().size() > 0){
                            BadgeWidget badgeWidget = new BadgeWidget();
                            badgeWidget.setBadge(result.getEvents().get(0).getBadgeData());
                            badgeWidget.show(getSupportFragmentManager(), ApiConst.FRAGMENT_PLAYER_INFO);
                        }else{
                            Toast.makeText(BadgeActivity.this, "No badge receive", Toast.LENGTH_SHORT).show();
                        }

                        showProgress(false);// dismiss the progress bar
                    }

                    @Override
                    public void onError(HttpError error) {
                        //Show a toast error
                        Toast.makeText(BadgeActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        showProgress(false); // dismiss the progress bar
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
