package com.playbasis.android.playbasissdk.widget;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.playbasis.android.playbasissdk.R;
import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.model.Player;

/**
 * Created by gregoire barret on 2/27/15.
 * For PlayBasisSdk project.
 */
public class PlayerSmsView extends AbstractPlayerView implements View.OnClickListener {
    public static final String TAG = "PlayerSmsView";


    private EditText mPhoneView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_sms_view, container);
        mPhoneView    = (EditText) view.findViewById(R.id.editText_player_phone);
        Button mSubmitButton = (Button) view.findViewById(R.id.button_submit);

        mSubmitButton.setOnClickListener(this);
        getDialog().setTitle(getString(R.string.player_info));
        if(player!=null){
            mPhoneView.setText(player.getEmail());
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view==null)return;
        if (view.getId() == R.id.button_submit){
            validate();
        }
    }

    @Override
    public void setPlayer(Player player){
        super.setPlayer(player);
        if(this.player!=null){
            if(mPhoneView!=null)mPhoneView.setText(this.player.getPhoneNumber());
        }

    }

    private void validate(){

        mPhoneView.setError(null);

        String phone = mPhoneView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(!Validator.isValid(phone)){
            mPhoneView.setError(getString(R.string.error_empty));
            focusView = mPhoneView;
            cancel = true;
        }else if(!Validator.isValidPhone(phone)){
            mPhoneView.setError(getString(R.string.error_phone_no_valid));
            focusView = mPhoneView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            if(player==null)player= new Player();
            player.setPhoneNumber(phone);

            if (getPlayerListener()!=null) getPlayerListener().onPlayer(player);
            dismiss();
        }



    }

}
