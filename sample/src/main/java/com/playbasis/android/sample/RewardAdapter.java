package com.playbasis.android.sample;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.playbasis.android.playbasissdk.http.toolbox.NetworkImageView;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.Reward;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 3/4/15.
 * For PlayBasisSdk project.
 */
public class RewardAdapter extends BaseAdapter {
    public static final String TAG = "RewardAdapter";


    private LayoutInflater mInflater;
    private List<Goods> goods;
    private OnRewardClick onRewardClick;

    public RewardAdapter(Context context) {
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        goods = new ArrayList<>();
    }

    public void setrewards(List<Goods> goods){
        this.goods.clear();
        this.goods.addAll(goods);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return goods.size();
    }

    @Override
    public Object getItem(int i) {
        return goods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.adapter_reward, viewGroup, false);
            holder.vName = (TextView)view.findViewById(R.id.textView_reward_name);
            holder.vDescription = (TextView)view.findViewById(R.id.textView_reward_description);
            holder.vReward = (Button) view.findViewById(R.id.button_reward);
            holder.imageView = (NetworkImageView)view.findViewById(R.id.imageView_reward);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.vName.setText(goods.get(position).getName());
        holder.vDescription.setText(Html.fromHtml(goods.get(position).getDescription()));
        holder.imageView.setImageUrl(goods.get(position).getImage(), SampleApplication.playbasis.getHttpManager().getImageLoader());
        holder.vReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onRewardClick!= null) onRewardClick.onRewardClick(goods.get(position).getGoodsId());
            }
        });
        return view;
    }

    public static class ViewHolder {
        public NetworkImageView imageView;
        public TextView vName;
        public TextView vDescription;
        public Button  vReward;
    }
    
    public void setOnRewardClickListener(OnRewardClick onRewardClick){
        this.onRewardClick = onRewardClick;
    }
    
    public interface OnRewardClick {
        public void onRewardClick(String goodId);
    }
}
