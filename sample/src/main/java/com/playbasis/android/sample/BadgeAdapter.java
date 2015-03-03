package com.playbasis.android.sample;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.playbasis.android.playbasissdk.http.toolbox.NetworkImageView;
import com.playbasis.android.playbasissdk.model.Badge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 3/3/15.
 * For PlayBasisSdk project.
 */
public class BadgeAdapter extends BaseAdapter {
    public static final String TAG = "BadgeAdapter";

    private LayoutInflater mInflater;
    private List<Badge> badges;

    public BadgeAdapter(Context context) {
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        badges = new ArrayList<>();
    }
    
    public void setBadges(List<Badge> badges){
        this.badges.clear();
        this.badges.addAll(badges);
        notifyDataSetChanged();
        
    }

    @Override
    public int getCount() {
        return badges.size();
    }

    @Override
    public Object getItem(int i) {
        return badges.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.adapter_badge, viewGroup, false);
            holder.vName = (TextView)view.findViewById(R.id.textView_badge_name);
            holder.vHint = (TextView)view.findViewById(R.id.textView_badge_hint);
            holder.vDescription = (TextView)view.findViewById(R.id.textView_badge_description);
            holder.imageView = (NetworkImageView)view.findViewById(R.id.imageView_badge);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.vName.setText(badges.get(i).getName());
        holder.vHint.setText(badges.get(i).getHint());
        holder.vDescription.setText(Html.fromHtml(badges.get(i).getDescription()));
        holder.imageView.setImageUrl(badges.get(i).getImage(), SampleApplication.playbasis.getHttpManager().getImageLoader());
        return view;
    }

    public static class ViewHolder {
        public NetworkImageView imageView;
        public TextView vName;
        public TextView vHint;
        public TextView vDescription;
    }
}
