package com.playbasis.android.sample;

import android.content.Context;
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
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.Mission;
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
    private OnMissionClick onMissionClick;
    private Context mContext;

    public QuestAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        missions = new ArrayList<>();
    }

    public void setMissions(List<Mission> missions){
        this.missions.clear();
        this.missions.addAll(missions);
        notifyDataSetChanged();

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

        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.adapter_mission, viewGroup, false);
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
        holder.vClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.vClick.setEnabled(false);
                if(missions.get(position).getHint().equals("click")){
                    SampleApplication.playbasis.Do("gregtestuser", RuleAction.CLICK, new OnResult<Rule>() {
                        @Override
                        public void onSuccess(Rule result) {
                            holder.vClick.setEnabled(true);
                            Toast.makeText(mContext, result.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(HttpError error) {
                            holder.vClick.setEnabled(true);
                            Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    SampleApplication.playbasis.Do("gregtestuser", RuleAction.LIKE, new OnResult<Rule>() {
                        @Override
                        public void onSuccess(Rule result) {
                            holder.vClick.setEnabled(true);
                            Toast.makeText(mContext, result.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(HttpError error) {
                            holder.vClick.setEnabled(true);
                            Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                
                if(onMissionClick!= null) onMissionClick.onMissionClick(missions.get(position).getMissionId());
            }
        });
        return view;
    }

    public static class ViewHolder {
        public NetworkImageView imageView;
        public TextView vName;
        public TextView vDescription;
        public Button  vClick;
    }

    public void setOnMissionClickListener(OnMissionClick onMissionClick){
        this.onMissionClick = onMissionClick;
    }

    public interface OnMissionClick {
        public void onMissionClick(String missionId);
    }
}
