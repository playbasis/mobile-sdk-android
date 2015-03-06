package com.playbasis.android.playbasissdk.widget;

import android.app.Activity;
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
 * Created by gregoire barret on 2/26/15.
 * For PlayBasisSdk project.
 */
public class PlayerView extends AbstractPlayerView{
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

//        View view = inflater.inflate(R.layout.player_view, container);
//        mIdView       = (EditText) view.findViewById(R.id.editText_player_id);
//        mEmailView    = (EditText) view.findViewById(R.id.editText_player_email);
//        mNameView     = (EditText) view.findViewById(R.id.editText_player_name);
//        Button mSubmitButton = (Button) view.findViewById(R.id.button_submit);




        LinearLayout rlmain = new LinearLayout(getActivity());
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        rlmain.setLayoutParams(llp);
        rlmain.setOrientation(LinearLayout.VERTICAL);
        rlmain.setPadding(16,16,16,16);
        
        mIdView = new EditText(getActivity());
        mEmailView = new EditText(getActivity());
        mNameView = new EditText(getActivity());
        Button mSubmitButton = new Button(getActivity());
        LinearLayout .LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        mIdView.setLayoutParams(lp);
        mEmailView.setLayoutParams(lp);
        mNameView.setLayoutParams(lp);
        mSubmitButton.setLayoutParams(lp);
        
        mIdView.setHint("Player Id");
        mEmailView.setHint("Email");
        mNameView.setHint("Name");
        mSubmitButton.setText("OK");

        rlmain.addView(mIdView);
        rlmain.addView(mEmailView);
        rlmain.addView(mNameView);
        rlmain.addView(mSubmitButton);
        


        

       mSubmitButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               validate();
           }
       });
        getDialog().setTitle("PLAYER INFO");

        if(player!=null){
            mIdView.setText(player.getClPlayerId());
            mEmailView.setText(player.getEmail());
            mNameView.setText(player.getUsername());
        }

        return rlmain;
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
            mIdView.setError("Can\'t be empty");
            focusView = mIdView;
            cancel = true;
        }

        if(!Validator.isValid(email)){
            mEmailView.setError("Can\'t be empty");
            focusView = mEmailView;
            cancel = true;
        }else if(!Validator.isValidEmail(email)){
            mEmailView.setError("Not a valid email");
            focusView = mEmailView;
            cancel = true;
        }

        if(!Validator.isValid(name)){
            mNameView.setError("Can\'t be empty");
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
