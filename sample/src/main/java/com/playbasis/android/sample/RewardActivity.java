package com.playbasis.android.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.BadgeApi;
import com.playbasis.android.playbasissdk.api.GoodsApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.PlayerApi;
import com.playbasis.android.playbasissdk.api.RedeemApi;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.RedeemGood;

import java.util.List;


public class RewardActivity extends FragmentActivity {

    ListView listView;
    RewardAdapter rewardAdapter;
    ProgressBar progressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listView_reward);
        rewardAdapter = new RewardAdapter(this);
        listView.setAdapter(rewardAdapter);


        GoodsApi.listInfo(SampleApplication.playbasis, new OnResult<List<Goods>>() {
            @Override
            public void onSuccess(List<Goods> result) {
                rewardAdapter.setrewards(result);
                showProgress(false);
            }

            @Override
            public void onError(HttpError error) {
                Toast.makeText(RewardActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                showProgress(false);
            }
        });
        showProgress(true);
        
        rewardAdapter.setOnRewardClickListener(new RewardAdapter.OnRewardClick() {
            @Override
            public void onRewardClick(String goodId) {
                showProgress(true);
                RedeemApi.goods(SampleApplication.playbasis, goodId, "gregtestuser", 1, new OnResult<List<RedeemGood>>() {
                    @Override
                    public void onSuccess(List<RedeemGood> result) {

                        AlertDialog alertDialog = new AlertDialog.Builder(RewardActivity.this).create();
                        alertDialog.setTitle("Coupon code");
                        if(result.size()>=0 || result.get(0) == null || result.get(0).getGoodsData() == null){
                            alertDialog.setMessage("No goods available");

                        }else{
                            alertDialog.setMessage(result.get(0).getGoodsData().getCode());

                        }
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        showProgress(false);
                    }

                    @Override
                    public void onError(HttpError error) {
                        Toast.makeText(RewardActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        showProgress(false);
                    }
                } );
            }
        });
        
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
