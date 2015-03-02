package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.playbasissdk.widget.AbstractPlayerView;
import com.playbasis.android.playbasissdk.widget.PlayerEmailView;
import com.playbasis.android.playbasissdk.widget.PlayerSmsView;
import com.playbasis.android.playbasissdk.widget.PlayerView;

/**
 * Created by gregoire barret on 2/27/15.
 * For PlayBasisSdk project.
 */
public class PlayerValidatorApi extends PlayerApi {
    public static final String TAG = "PlayerValidatorApi";


    public static void emailValidation(@NonNull final Playbasis playbasis,
                                       @NonNull final String playerId, final OnResult<Boolean> listener) {

        PlayerApi.getPlayerPrivateInfo(playbasis, playerId, new OnResult<Player>() {

            @Override
            public void onSuccess(Player player) {
                if (Validator.isValidPlaybasisEmail(player.getEmail())) {
                    if (listener != null) listener.onSuccess(true);
                } else {

                    FragmentManager fm;
                    if (playbasis.getActivity() != null) {
                        fm = playbasis.getActivity().getSupportFragmentManager();
                    } else {
                        try {
                            fm = ((FragmentActivity) playbasis.getContext()).getSupportFragmentManager();
                        } catch (ClassCastException e) {
                            if (listener != null) listener.onSuccess(false);
                            return;
                        }
                    }

                    AbstractPlayerView playerView = playbasis.getPlayerEmailView() == null ?
                            new PlayerEmailView() : playbasis.getPlayerEmailView();
                    playerView.setPlayer(player);
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
                if (listener != null) listener.onError(error);
            }
        });
    }


    public static void smsValidation(@NonNull final Playbasis playbasis,
                                     @NonNull final String playerId, final OnResult<Boolean> listener) {

        PlayerApi.getPlayerPrivateInfo(playbasis, playerId, new OnResult<Player>() {

            @Override
            public void onSuccess(Player player) {
                if (Validator.isValidPhone(player.getPhoneNumber())) {
                    if (listener != null) listener.onSuccess(true);
                } else {
                    FragmentManager fm;
                    if (playbasis.getActivity() != null) {
                        fm = playbasis.getActivity().getSupportFragmentManager();
                    } else {
                        try {
                            fm = ((FragmentActivity) playbasis.getContext()).getSupportFragmentManager();
                        } catch (ClassCastException e) {
                            if (listener != null) listener.onSuccess(false);
                            return;
                        }
                    }
                    AbstractPlayerView playerView = playbasis.getPlayerSmsView() == null ?
                            new PlayerSmsView() : playbasis.getPlayerSmsView();
                    playerView.setPlayer(player);
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
                if (listener != null) listener.onError(error);
            }
        });


    }

}
