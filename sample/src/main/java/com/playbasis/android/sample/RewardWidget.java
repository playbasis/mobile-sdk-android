package com.playbasis.android.sample;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.http.toolbox.NetworkImageView;
import com.playbasis.android.playbasissdk.model.BadgeData;

/**
 * Created by gregoire barret on 3/4/15.
 * For PlayBasisSdk project.
 */
public class RewardWidget extends DialogFragment {
    public static final String TAG = "RewardWidget";

    private NetworkImageView imageView;
    private TextView vName;
    private TextView vHint;
    private TextView vDescription;
    private TextView vPoints;
    private TextView vExp;
    private TextView vCoupon;
    private LinearLayout lBadge;
    private LinearLayout lPoints;
    private LinearLayout lExp;
    private LinearLayout lCoupon;
    
    private BadgeData badge;
    private String point;
    private String exp;
    private String coupon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Create the widget view
        View view = inflater.inflate(R.layout.widget_reward, container);
        vName    = (TextView) view.findViewById(R.id.textView_rewardwidget_badge_name);
        vHint    = (TextView) view.findViewById(R.id.textView_rewardwidget_badge_hint);
        vDescription    = (TextView) view.findViewById(R.id.textView_rewardwidget_badge_description);
        vPoints    = (TextView) view.findViewById(R.id.textView_rewardwidget_points);
        vExp    = (TextView) view.findViewById(R.id.textView_rewardwidget_exp);
        vCoupon    = (TextView) view.findViewById(R.id.textView_rewardwidget_coupon);
        imageView    = (NetworkImageView) view.findViewById(R.id.imageView_rewardwidget_badge);
        lBadge = (LinearLayout) view.findViewById(R.id.linearLayout_rewardwidget_badge);
        lPoints = (LinearLayout) view.findViewById(R.id.linearLayout_rewardwidget_points);
        lExp = (LinearLayout) view.findViewById(R.id.linearLayout_rewardwidget_exp);
        lCoupon = (LinearLayout) view.findViewById(R.id.linearLayout_rewardwidget_coupon);

        Button mOkButton = (Button) view.findViewById(R.id.button_widget_ok);

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        getDialog().setTitle("REWARD");
        // If have badge, display the badge layout
        if(badge!=null){
            lBadge.setVisibility(View.VISIBLE);
            vName.setText(badge.getName());
            vHint.setText(badge.getHint());
            vDescription.setText(Html.fromHtml(badge.getDescription()));
            imageView.setImageUrl(badge.getImage(), SampleApplication.playbasis.getHttpManager().getImageLoader());
        }
        // If have point, display the point layout
        if (Validator.isValid( point)){
            lPoints.setVisibility(View.VISIBLE);
            vPoints.setText(point);
        }
        // If have exp, display the exp layout
        if (Validator.isValid( exp)){
            lExp.setVisibility(View.VISIBLE);
            vExp.setText(exp);
        }
        // If have coupon, display the coupon layout
        if (Validator.isValid( coupon)){
            lCoupon.setVisibility(View.VISIBLE);
            vCoupon.setText(coupon);
        }

        return view;
    }

    public void setBadge(BadgeData badge){
        this.badge = badge;

    }
    
    public void setPoints(String points){
        this.point = points;
        
    }
    
    public void setExp(String exp){
        this.exp = exp;
        
    }

    public void setCoupon(String coupon){
        this.coupon = coupon;

    }
}
