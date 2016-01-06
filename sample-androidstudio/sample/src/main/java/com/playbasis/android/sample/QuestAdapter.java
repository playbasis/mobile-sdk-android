package com.playbasis.android.sample;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.toolbox.NetworkImageView;
import com.playbasis.android.playbasissdk.model.Event;
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.Mission;
import com.playbasis.android.playbasissdk.model.RedeemGood;
import com.playbasis.android.playbasissdk.model.Reward;
import com.playbasis.android.playbasissdk.model.Rule;
import com.playbasis.android.playbasissdk.model.RuleAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 3/4/15.
 * For PlayBasisSdk project.
 */
public class QuestAdapter  extends BaseAdapter {
    public static final String TAG = "QuestAdapter";


    private LayoutInflater mInflater;
    private List<Mission> missions;
    private Context mContext;
    private FragmentActivity mFragmentActivity;

    //Constructor
    public QuestAdapter(FragmentActivity fragmentActivity) {
        mContext = fragmentActivity;
        mFragmentActivity = fragmentActivity;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        missions = new ArrayList<>();
    }

    //Get the missions
    public void setMissions(List<Mission> missions){
        this.missions.clear(); // Clear previous data
        this.missions.addAll(missions); // Add new missions
        notifyDataSetChanged(); // Notify the adapter we have new datas

    }

    @Override
    public int getCount() {
        return missions.size();
    }

    @Override
    public Object getItem(int i) {
        return missions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;

        //Set item recycler
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.adapter_mission, viewGroup, false);
            holder.view = view;
            holder.vName = (TextView)view.findViewById(R.id.textView_mission_name);
            holder.vDescription = (TextView)view.findViewById(R.id.textView_mission_description);
            holder.vClick = (Button) view.findViewById(R.id.button_click);
            holder.imageView = (NetworkImageView)view.findViewById(R.id.imageView_mission);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.vName.setText(missions.get(position).getMissionName());
        holder.vDescription.setText(Html.fromHtml(missions.get(position).getDescription()));
        holder.imageView.setImageUrl(missions.get(position).getImage(), SampleApplication.playbasis.getHttpManager().getImageLoader());
        holder.vClick.setText(missions.get(position).getHint());
        
        
        // Button click event
        holder.vClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.vClick.setEnabled(false);
                
                //If the button if "click" event
                if(missions.get(position).getHint().equals("click")){
                    //Send a sync "click" event to the playbasis dashBoard
                    SampleApplication.playbasis.Do("exampleplayer", RuleAction.CLICK, new OnResult<Rule>() {
                        @Override
                        public void onSuccess(Rule result) {
                            // set the view to green
                            holder.view.setBackgroundColor(mContext.getResources().getColor(
                                    android.R.color.holo_green_light));
                            displayResult(result); // Display reward dialogFragment
                        }

                        @Override
                        public void onError(HttpError error) {
                            holder.vClick.setEnabled(true);
                            //show error toast
                            Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                //If the button if "like" event
                }else{
                    //Send a sync "like" event to the playbasis dashBoard
                    SampleApplication.playbasis.Do("exampleplayer", RuleAction.LIKE, new OnResult<Rule>() {
                        @Override
                        public void onSuccess(Rule result) {
                            // set the view to green
                            holder.view.setBackgroundColor(mContext.getResources().getColor(
                                    android.R.color.holo_green_light));
                            displayResult(result);// Display reward dialogFragment
                        }

                        @Override
                        public void onError(HttpError error) {
                            holder.vClick.setEnabled(true);
                            //show error toast
                            Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                
            }
        });
        return view;
    }
    
    // Show Reward dialog fragment
    private void displayResult(Rule result){

        // Verify if this mission have rewards
        if(result.getMissions().size()> 0){
            RewardWidget rewardWidget = new RewardWidget(); //Create Reward dialog fragment
            
            Mission mission = result.getMissions().get(0); // Get first mission
            for (Event event : mission.getEvents()) {
                // set point on the rewardFragment if the reward have points
                if (event.getRewardType().equals("point")) {
                    rewardWidget.setPoints(event.getValue());
                }
                // set badge on the rewardFragment if the reward have badge
                else if (event.getRewardType().equals("badge")) {
                    rewardWidget.setBadge(event.getBadgeData());
                }
            }
            rewardWidget.show(mFragmentActivity.getSupportFragmentManager(), "fragment_reward_widget");
        }else {
            // Display error message, this mission doesn't have rewards
            Toast.makeText(mContext, "No rewards available", Toast.LENGTH_SHORT).show();
        }

        
    }

    //View holder recycler
    public static class ViewHolder {
        public View view;
        public NetworkImageView imageView;
        public TextView vName;
        public TextView vDescription;
        public Button  vClick;
    }

}
