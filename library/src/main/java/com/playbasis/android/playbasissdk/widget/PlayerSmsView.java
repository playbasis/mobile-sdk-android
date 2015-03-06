package com.playbasis.android.playbasissdk.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.model.Player;

/**
 * Created by gregoire barret on 2/27/15.
 * For PlayBasisSdk project.
 */
public class PlayerSmsView extends AbstractPlayerView {
    public static final String TAG = "PlayerSmsView";


    private EditText mPhoneView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*        View view = inflater.inflate(R.layout.player_sms_view, container);
        mPhoneView    = (EditText) view.findViewById(R.id.editText_player_phone);
        Button mSubmitButton = (Button) view.findViewById(R.id.button_submit);*/


        LinearLayout rlmain = new LinearLayout(getActivity());
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        rlmain.setLayoutParams(llp);
        rlmain.setOrientation(LinearLayout.VERTICAL);
        rlmain.setPadding(16,16,16,16);

        mPhoneView = new EditText(getActivity());
        Button mSubmitButton = new Button(getActivity());
        LinearLayout .LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        mPhoneView.setLayoutParams(lp);
        mSubmitButton.setLayoutParams(lp);

        mPhoneView.setHint("Phone number");
        mSubmitButton.setText("OK");

        rlmain.addView(mPhoneView);
        rlmain.addView(mSubmitButton);


        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
        getDialog().setTitle("PLAYER SMS");
        if(player!=null){
            mPhoneView.setText(player.getEmail());
        }

        return rlmain;
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

        String phone = mPhoneView.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        if(!Validator.isValid(phone)){
            mPhoneView.setError("Can\'t be empty");
            focusView = mPhoneView;
            cancel = true;
        }else if(!Validator.isValidPhone(phone)){
            mPhoneView.setError("phone number format should be +[countrycode][number]");
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
