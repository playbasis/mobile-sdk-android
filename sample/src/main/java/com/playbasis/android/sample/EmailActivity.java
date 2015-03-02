package com.playbasis.android.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.CommunicationApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.PlayerApi;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Player;

import java.util.List;


public class EmailActivity extends FragmentActivity {
    
    EditText vPlayerid;
    Button vSend;
    Button vClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        
        SampleApplication.playbasis.setActivity(this);
        
        vPlayerid = (EditText) findViewById(R.id.editText_player_id);
        
        vSend = (Button) findViewById(R.id.button_send);
        vSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail(vPlayerid.getText().toString());
            }
        });
        
        vClear = (Button) findViewById(R.id.button_clear);
        vClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEmail(vPlayerid.getText().toString());
            }
        });
        
        
    }
    
    private void sendEmail(String playerId){
        CommunicationApi.sendEmail(SampleApplication.playbasis, playerId, "test", "test message", null , new OnResult<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
                String successMessage = "";
                if(result!=null){
                    for (String s : result) {
                        successMessage += s;
                        successMessage += ",";
                    }
                }
                Toast.makeText(EmailActivity.this, successMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(HttpError error) {
                if(error.requestError!=null){
                    Toast.makeText(EmailActivity.this, error.requestError.message, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EmailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }
    
    private void clearEmail(String playerId){
        PlayerApi.getPlayerInfo(SampleApplication.playbasis, playerId, new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                result.setEmail("pbapp_auto_user@playbasis.com");
                PlayerApi.update(SampleApplication.playbasis,result, new OnResult<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        Toast.makeText(EmailActivity.this, "Email clear", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(HttpError error) {
                        Toast.makeText(EmailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(HttpError error) {
                Toast.makeText(EmailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SampleApplication.playbasis.removeActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_email, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
