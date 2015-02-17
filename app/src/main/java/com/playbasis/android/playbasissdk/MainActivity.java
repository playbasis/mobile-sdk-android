package com.playbasis.android.playbasissdk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.playbasis.android.playbasissdk.api.AuthToken;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.PlayerApi;
import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Player;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Playbasis playbasis = new Playbasis.Builder(this)
                .setApiKey("3026965093")
                .setApiSecret("ce9c9335d542674a2a3e286307dba8c0")
                .setServerUrl("https://api.pbapp.net")
                .build();

        playbasis.getAuthenticator().getAuthToken(new OnResult<AuthToken>() {
            @Override
            public void onSuccess(AuthToken result) {
                Log.d("main", result.getToken());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });

        PlayerApi.getPlayerInfo(playbasis, "jontestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        } );

        PlayerApi.getPlayerPrivateInfo(playbasis, "jontestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });

        PlayerApi.getListPlayerInfo(playbasis, Arrays.asList("jontestuser"), new OnResult<List<Player>>() {
            @Override
            public void onSuccess(List<Player> result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });

        PlayerApi.getDetailedPlayerListInfo(playbasis, "jontestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });

        PlayerApi.getDetailedPlayerPrivateInfo(playbasis, "jontestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
