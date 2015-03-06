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
public class PlayerEmailView extends AbstractPlayerView {
    public static final String TAG = "PlayerEmailView";


    private EditText mEmailView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*        View view = inflater.inflate(R.layout.player_email_view, container);
        mEmailView    = (EditText) view.findViewById(R.id.editText_player_email);
        Button mSubmitButton = (Button) view.findViewById(R.id.button_submit);*/

        LinearLayout rlmain = new LinearLayout(getActivity());
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        rlmain.setLayoutParams(llp);
        rlmain.setOrientation(LinearLayout.VERTICAL);
        rlmain.setPadding(16,16,16,16);

        mEmailView = new EditText(getActivity());
        Button mSubmitButton = new Button(getActivity());
        LinearLayout .LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        mEmailView.setLayoutParams(lp);
        mSubmitButton.setLayoutParams(lp);

        mEmailView.setHint("Email");
        mSubmitButton.setText("OK");

        rlmain.addView(mEmailView);
        rlmain.addView(mSubmitButton);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
        getDialog().setTitle("PLAYER EMAIL");
        if(player!=null){
            mEmailView.setText(player.getEmail());
        }

        return rlmain;
    }

    @Override
    public void setPlayer(Player player){
        super.setPlayer(player);
        if(this.player!=null){
            if(mEmailView!=null)mEmailView.setText(this.player.getEmail());
        }

    }

    private void validate(){

        mEmailView.setError(null);

        String email = mEmailView.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        if(!Validator.isValid(email)){
            mEmailView.setError("Can\'t be empty");
            focusView = mEmailView;
            cancel = true;
        }else if(!Validator.isValidEmail(email)){
            mEmailView.setError("Not a valid email");
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            if(player==null)player= new Player();
            player.setEmail(email);

            if (getPlayerListener()!=null) getPlayerListener().onPlayer(player);
            dismiss();
        }



    }
    
}
