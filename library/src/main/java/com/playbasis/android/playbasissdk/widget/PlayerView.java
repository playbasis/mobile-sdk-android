package com.playbasis.android.playbasissdk.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.playbasis.android.playbasissdk.R;
import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.model.Player;

/**
 * Created by gregoire barret on 2/26/15.
 * For PlayBasisSdk project.
 */
public class PlayerView extends AbstractPlayerView implements View.OnClickListener {
    public static final String TAG = "PlayerView";

    private EditText mIdView;
    private EditText mEmailView;
    private EditText mNameView;


    public PlayerView() {
        // Empty constructor required for DialogFragment
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.player_view, container);
        mIdView       = (EditText) view.findViewById(R.id.editText_player_id);
        mEmailView    = (EditText) view.findViewById(R.id.editText_player_email);
        mNameView     = (EditText) view.findViewById(R.id.editText_player_name);
        Button mSubmitButton = (Button) view.findViewById(R.id.button_submit);



/*
        LinearLayout rlmain = new LinearLayout(getActivity());
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);

        mIdView = new EditText(getActivity());
        mEmailView = new EditText(getActivity());
        mNameView = new EditText(getActivity());
        LinearLayout .LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        mIdView.setLayoutParams(lp);
        mEmailView.setLayoutParams(lp);
        mNameView.setLayoutParams(lp);

        rlmain.addView(mIdView);
        rlmain.addView(mEmailView);
        rlmain.addView(mNameView);
*/

        

        mSubmitButton.setOnClickListener(this);
        getDialog().setTitle(getString(R.string.player_info));

        if(player!=null){
            mIdView.setText(player.getClPlayerId());
            mEmailView.setText(player.getEmail());
            mNameView.setText(player.getUsername());
        }

        return view;
    }


    @Override
    public void onClick(View view) {
        if (view==null)return;
/*        if (view.getId() == R.id.button_submit){
            validate();
        }*/
        
    }
    
    @Override
    public void setPlayer(Player player){
        super.setPlayer(player);
        if(this.player!=null){
            if(mIdView!=null)mIdView.setText(this.player.getClPlayerId());
            if(mEmailView!=null)mEmailView.setText(this.player.getEmail());
            if(mNameView!=null)mNameView.setText(this.player.getUsername());
        }
        
    }
    
    private void validate(){
        
        mIdView.setError(null);
        mEmailView.setError(null);
        mNameView.setError(null);

        String id = mIdView.getText().toString().trim();
        String email = mEmailView.getText().toString().trim();
        String name = mNameView.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;
        
        
        if(!Validator.isValid(id)){
            mIdView.setError(getString(R.string.error_empty));
            focusView = mIdView;
            cancel = true;
        }

        if(!Validator.isValid(email)){
            mEmailView.setError(getString(R.string.error_empty));
            focusView = mEmailView;
            cancel = true;
        }else if(!Validator.isValidEmail(email)){
            mEmailView.setError(getString(R.string.error_email_no_valid));
            focusView = mEmailView;
            cancel = true;
        }

        if(!Validator.isValid(name)){
            mNameView.setError(getString(R.string.error_empty));
            focusView = mNameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            if(player==null)player= new Player();
            player.withClPlayerId(id)
            .withUsername(name)
            .withEmail(email);
           
            if (getPlayerListener()!=null) getPlayerListener().onPlayer(player);
            dismiss();
        }



    }
    


}
