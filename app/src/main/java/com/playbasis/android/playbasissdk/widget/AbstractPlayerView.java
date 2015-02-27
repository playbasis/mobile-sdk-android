package com.playbasis.android.playbasissdk.widget;

import android.support.v4.app.DialogFragment;

import com.playbasis.android.playbasissdk.model.Player;

/**
 * Created by gregoire barret on 2/27/15.
 * For PlayBasisSdk project.
 */
public abstract class AbstractPlayerView extends DialogFragment {
    public static final String TAG = "AbstractPlayerView";

    public interface OnPlayer{
        public void onPlayer(Player player);

    }

    private OnPlayer mListener;
    
    protected Player player;
    
    public AbstractPlayerView(){
        
        
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setPlayerListener(OnPlayer listener){
        this.mListener = listener;
    }
    
    public OnPlayer getPlayerListener(){
        return mListener;
    }
    
}
