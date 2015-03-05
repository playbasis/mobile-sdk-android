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
import com.playbasis.android.playbasissdk.model.Reward;

import java.util.List;


public class RewardActivity extends FragmentActivity {

    ListView listView;
    RewardAdapter rewardAdapter;
    ProgressBar progressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        
        //Create view
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.listView_reward);
        rewardAdapter = new RewardAdapter(this);
        listView.setAdapter(rewardAdapter);

        //Get the list of goods
        GoodsApi.listInfo(SampleApplication.playbasis, new OnResult<List<Goods>>() {
            @Override
            public void onSuccess(List<Goods> result) {
                rewardAdapter.setrewards(result); //Send the list of goods to the listView adapter
                showProgress(false); // Dismiss progress bar
            }

            @Override
            public void onError(HttpError error) {
                //Show error
                Toast.makeText(RewardActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                showProgress(false);// Dismiss progress bar
            }
        });
        showProgress(true); // Show progress bar
        
        //If a item redeem button on the listView has been click
        rewardAdapter.setOnRewardClickListener(new RewardAdapter.OnRewardClick() {
            @Override
            public void onRewardClick(String goodId) {
                showProgress(true);
                //Redeem the goods
                RedeemApi.goods(SampleApplication.playbasis, goodId, "gregtestuser", 1, new OnResult<List<RedeemGood>>() {
                    @Override
                    public void onSuccess(List<RedeemGood> result) {

                        RewardWidget rewardWidget = new RewardWidget(); // Create redeem dialogFragment
                        for (RedeemGood redeemGood : result) { // Loop for every goods
                            //Check if the good is empty
                            if(result.size()<=0 || result.get(0) == null || result.get(0).getGoodsData() == null) {
                                rewardWidget.setCoupon("No goods available");
                            }else {
                                //If good event is a "Goods_RECEIVED" event
                                if (redeemGood.getEventType().equals("GOODS_RECEIVED")) {
                                    // if good have points
                                    if (redeemGood.getGoodsData().getRedeem().getPoint() != null) {
                                        //Send the number of points to the redeem dialogFragment
                                        rewardWidget.setPoints(String.valueOf(String.valueOf(redeemGood.getGoodsData()
                                                .getRedeem()
                                                .getPoint()
                                                .getValue()))); 
                                    }
                                    //If good have badge
                                    else if (redeemGood.getGoodsData().getRedeem().getBadge() != null) {
                                        //Send the badge to the redeem dialogFragment
                                        rewardWidget.setBadge(redeemGood.getGoodsData().getRedeem().getBadge().get(0));
                                    }
                                }
                                // Send the coupon code to the redeem dialogFragment
                                rewardWidget.setCoupon(result.get(0).getGoodsData().getCode());
                            }
                        }
                        rewardWidget.show(getSupportFragmentManager(), "fragment_reward_widget");

                        showProgress(false); // Hide progress bar
                    }

                    @Override
                    public void onError(HttpError error) {
                        // Show error
                        Toast.makeText(RewardActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        showProgress(false); //Hide progress bar
                    }
                } );
            }
        });
        
    }


    //Show or hide the progress bar
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
