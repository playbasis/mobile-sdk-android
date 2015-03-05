package com.playbasis.android.sample;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.playbasis.android.playbasissdk.http.toolbox.NetworkImageView;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.BadgeData;

/**
 * Created by gregoire barret on 3/4/15.
 * For PlayBasisSdk project.
 */
public class BadgeWidget extends DialogFragment {
    public static final String TAG = "BadgeWidget";

    public NetworkImageView imageView;
    public TextView vName;
    public TextView vHint;
    public TextView vDescription;
    private BadgeData badge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_badge, container);
        vName    = (TextView) view.findViewById(R.id.textView_widget_badge_name);
        vHint    = (TextView) view.findViewById(R.id.textView_widget_badge_hint);
        vDescription    = (TextView) view.findViewById(R.id.textView_widget_badge_description);
        imageView    = (NetworkImageView) view.findViewById(R.id.imageView_widget_badge);
        
        Button mOkButton = (Button) view.findViewById(R.id.button_widget_ok);

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        getDialog().setTitle("BADGE");
        if(badge!=null){
            vName.setText(badge.getName());
            vHint.setText(badge.getHint());
            vDescription.setText(badge.getDescription());
            imageView.setImageUrl(badge.getImage(), SampleApplication.playbasis.getHttpManager().getImageLoader());
        }

        return view;
    }
    
    public void setBadge(BadgeData badge){
        this.badge = badge;
        
    }
}
