package com.playbasis.android.sample.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.PlayerApi;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.toolbox.NetworkImageView;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.sample.R;
import com.playbasis.android.sample.SampleApplication;


public class PlayerActivity extends FragmentActivity {

    TextView vID;
    TextView vUsername;
    TextView vExp;
    TextView vLevel;
    TextView vFirstName;
    TextView vLastNAme;
    TextView vGender;
    TextView vBirthDate;
    TextView vRegestered;
    TextView vLastLogin;
    TextView vLastLogout;
    NetworkImageView vImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //Create the view
        vID = (TextView) findViewById(R.id.textView_player_id);
        vUsername = (TextView) findViewById(R.id.textView_player_username);
        vExp = (TextView) findViewById(R.id.textView_player_exp);
        vLevel = (TextView) findViewById(R.id.textView_player_level);
        vFirstName = (TextView) findViewById(R.id.textView_player_firstname);
        vLastNAme = (TextView) findViewById(R.id.textView_player_lastname);
        vGender = (TextView) findViewById(R.id.textView_player_gender);
        vBirthDate = (TextView) findViewById(R.id.textView_player_birthdate);
        vRegestered = (TextView) findViewById(R.id.textView_player_registered);
        vLastLogin = (TextView) findViewById(R.id.textView_player_last_login);
        vLastLogout = (TextView) findViewById(R.id.textView_player_last_logout);
        vImage = (NetworkImageView) findViewById(R.id.image_player);


        getPlayerInfo("TestPlayer001");
        
        
    }

    @Override
    protected void onResume() {
        super.onResume();
        SampleApplication.playbasis.setActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SampleApplication.playbasis.removeActivity();
    }

    private void getPlayerInfo(String playerId){
        PlayerApi.playerInfo(SampleApplication.playbasis, playerId, new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                // Get player information into string
                String playerId = result.getClPlayerId() != null ? result.getClPlayerId() : "";
                String playerName = result.getUsername() != null ? result.getUsername() : "";
                String playerExp = result.getExp() != null ? String.valueOf(result.getExp()) : "";
                String playerLevel = result.getLevel() != null ? String.valueOf(result.getLevel()) : "";
                String playerFirstName = result.getFirstName() != null ? result.getFirstName() : "";
                String playerLstName = result.getLastName() != null ? result.getLastName() : "";
                String playerGender = result.getGender() != null ? result.getGender().toString() : "";
                String playerBirthDate = result.getBirthDate() != null ? result.getBirthDate() : "";
                String playerRegistered = result.getRegistered() != null ? result.getRegistered() : "";
                String playerLogin = result.getLastLogin() != null ? result.getLastLogin() : "";
                String playerLogout = result.getLastLogout() != null ? result.getLastLogout() : "";
                String imageUrl = result.getImage() != null ? result.getImage() : "";

                // Set player information into view
                vID.setText(playerId);
                vUsername.setText(playerName);
                vExp.setText(playerExp);
                vLevel.setText(playerLevel);
                vFirstName.setText(playerFirstName);
                vLastNAme.setText(playerLstName);
                vGender.setText(playerGender);
                vBirthDate.setText(playerBirthDate);
                vRegestered.setText(playerRegistered);
                vLastLogin.setText(playerLogin);
                vLastLogout.setText(playerLogout);
                vImage.setImageUrl(imageUrl, SampleApplication.playbasis.getHttpManager().getImageLoader());
            }

            @Override
            public void onError(HttpError error) {
                // Show error toast
                if (error.requestError != null) {
                    Toast.makeText(PlayerActivity.this, error.requestError.message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PlayerActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }
}
