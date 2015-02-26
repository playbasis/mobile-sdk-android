package com.playbasis.android.playbasissdk.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.playbasis.android.playbasissdk.R;
import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.playbasissdk.secure.PrivatePreferences;

/**
 * Created by gregoire barret on 2/26/15.
 * For PlayBasisSdk project.
 */
public class PlayerView extends DialogFragment implements View.OnClickListener {
    public static final String TAG = "PlayerView";



    public interface OnPlayer{
        public void onPlayer(Player player);
        
    }
    
    
    private OnPlayer mListener;
    
    private EditText mIdView;
    private EditText mEmailView;
    private EditText mNameView;

    private Player mPlayer;
    
    public PlayerView() {
        // Empty constructor required for DialogFragment
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
/*        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_view, container);
        mIdView       = (EditText) view.findViewById(R.id.editText_player_id);
        mEmailView    = (EditText) view.findViewById(R.id.editText_player_email);
        mNameView     = (EditText) view.findViewById(R.id.editText_player_name);
        Button mSubmitButton = (Button) view.findViewById(R.id.button_submit);
    
        mSubmitButton.setOnClickListener(this);
        getDialog().setTitle(getString(R.string.player_info));

        if(mPlayer!=null){
            mIdView.setText(mPlayer.getClPlayerId());
            mEmailView.setText(mPlayer.getEmail());
            mNameView.setText(mPlayer.getUsername());
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
    
    public void setPlayer(Player player){
        this.mPlayer = player;
        if(mPlayer!=null){
            if(mIdView!=null)mIdView.setText(mPlayer.getClPlayerId());
            if(mEmailView!=null)mEmailView.setText(mPlayer.getEmail());
            if(mNameView!=null)mNameView.setText(mPlayer.getUsername());
        }
        
    }
    
    private void validate(){
        
        mIdView.setError(null);
        mEmailView.setError(null);
        mNameView.setError(null);

        String id = mIdView.getText().toString();
        String email = mEmailView.getText().toString();
        String name = mNameView.getText().toString();

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
            if(mPlayer==null)mPlayer= new Player();
            mPlayer.withClPlayerId(id)
            .withUsername(name)
            .withEmail(email);
           
            if (mListener!=null) mListener.onPlayer(mPlayer);
            dismiss();
        }



    }
    
    public void setPlayerListener(OnPlayer listener){
        this.mListener = listener;
        
    }

}
