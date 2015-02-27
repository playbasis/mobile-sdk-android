package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.RequestError;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.playbasissdk.widget.PlayerEmailView;
import com.playbasis.android.playbasissdk.widget.PlayerSmsView;
import com.playbasis.android.playbasissdk.widget.PlayerView;

import java.util.List;

/**
 * Created by gregoire barret on 2/27/15.
 * For PlayBasisSdk project.
 */
public class PlayerValidatorApi extends PlayerApi {
    public static final String TAG = "PlayerValidatorApi";
    
    
    
    public static void emailValidation(@NonNull final Playbasis playbasis,
                                       @NonNull final String playerId, final OnResult<Boolean>listener){
        
        PlayerApi.getPlayerPrivateInfo(playbasis,playerId, new OnResult<Player>() {

            @Override
            public void onSuccess(Player result) {
                if(Validator.isValidPlaybasisEmail(result.getEmail())){
                    if(listener!=null) listener.onSuccess(true);
                }else{
                        FragmentManager fm;
                        try {
                            fm = ((FragmentActivity) playbasis.getContext()).getSupportFragmentManager();
                        } catch (ClassCastException e) {
                       //     if (listener != null) listener.onError(new HttpError(
                      //              new RequestError("player not valid", RequestError.ERROR_CODE.DEFAULT)));
                            if(listener!=null) listener.onSuccess(false);
                            return;
                        }
                        PlayerEmailView playerView = new PlayerEmailView();
                        playerView.setPlayer(result);
                        playerView.show(fm, "fragment_player_info");
                        playerView.setPlayerListener(new PlayerView.OnPlayer() {
                            @Override
                            public void onPlayer(Player player) {
                                updatePlayer(playbasis, false, player, new OnResult<Boolean>() {
                                    @Override
                                    public void onSuccess(Boolean result) {
                                        if (result) {
                                            if (listener != null) listener.onSuccess(true);
                                        } else {
                                            if (listener != null) listener.onError(new HttpError());
                                        }
                                    }

                                    @Override
                                    public void onError(HttpError error) {
                                        if (listener != null) listener.onError(error);
                                    }
                                });
                            }
                        });
                }
            }
            @Override
            public void onError(HttpError error) {
                if(listener!=null) listener.onError(error);
            }
        });
    }


    public static void smsValidation(@NonNull final Playbasis playbasis,
                                       @NonNull final String playerId, final OnResult<Boolean>listener){

        PlayerApi.getPlayerPrivateInfo(playbasis,playerId, new OnResult<Player>() {

            @Override
            public void onSuccess(Player result) {
                if(Validator.isValidPhone(result.getPhoneNumber())){
                    if(listener!=null) listener.onSuccess(true);
                }else{
                    FragmentManager fm;
                    try {
                        fm = ((FragmentActivity) playbasis.getContext()).getSupportFragmentManager();
                    } catch (ClassCastException e) {
                        //     if (listener != null) listener.onError(new HttpError(
                        //              new RequestError("player not valid", RequestError.ERROR_CODE.DEFAULT)));
                        if(listener!=null) listener.onSuccess(false);
                        return;
                    }
                    PlayerSmsView playerView = new PlayerSmsView();
                    playerView.setPlayer(result);
                    playerView.show(fm, "fragment_player_info");
                    playerView.setPlayerListener(new PlayerView.OnPlayer() {
                        @Override
                        public void onPlayer(Player player) {
                            updatePlayer(playbasis, false, player,new OnResult<Boolean>() {
                                @Override
                                public void onSuccess(Boolean result) {
                                    if(result){
                                        if(listener!=null) listener.onSuccess(true);
                                    }else{
                                        if(listener!=null) listener.onError(new HttpError());
                                    }
                                }

                                @Override
                                public void onError(HttpError error) {
                                    if(listener!=null) listener.onError(error);
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onError(HttpError error) {
                if(listener!=null) listener.onError(error);
            }
        });



    }
    
}
