package com.playbasis.android.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.PlayerApi;
import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.http.toolbox.NetworkImageView;
import com.playbasis.android.playbasissdk.model.Player;


public class PlayerActivity extends FragmentActivity {

    EditText vPlayerId;
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

        SampleApplication.playbasis.setActivity(this);

        vPlayerId = (EditText) findViewById(R.id.editText_player_id);
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

        Button submitButton  = (Button) findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPlayerInfo(vPlayerId.getText().toString());
            }
        });
        
        
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SampleApplication.playbasis.removeActivity();
    }

    private void getPlayerInfo(String playerId){
        PlayerApi.getPlayerInfo(SampleApplication.playbasis, playerId, new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                String playerId = result.getClPlayerId()!=null ? result.getClPlayerId() : "";
                String playerName = result.getUsername()!=null ? result.getUsername() : "";
                String playerExp = result.getExp()!=null ? String.valueOf(result.getExp()) : "";
                String playerLevel = result.getLevel()!=null ? String.valueOf(result.getLevel()) : "";
                String playerFirstName = result.getFirstName()!=null ? result.getFirstName() : "";
                String playerLstName = result.getLastName()!=null ? result.getLastName() : "";
                String playerGender = result.getGender()!=null ? result.getGender().toString() : "";
                String playerBirthDate = result.getBirthDate()!=null ? result.getBirthDate() : "";
                String playerRegistered = result.getRegistered()!=null ? result.getRegistered() : "";
                String playerLogin = result.getLastLogin()!=null ? result.getLastLogin() : "";
                String playerLogout = result.getLastLogout()!=null ? result.getLastLogout() : "";
                String imageUrl = result.getImage()!=null ? result.getImage() : "";

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
                if(error.requestError!=null){
                    Toast.makeText(PlayerActivity.this, error.requestError.message, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PlayerActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
