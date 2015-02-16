package com.playbasis.android.playbasissdk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.playbasis.android.playbasissdk.api.AuthApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.Token;
import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.http.HttpError;


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

        AuthApi.auth(playbasis, new OnResult<Token>() {
            @Override
            public void onSuccess(Token result) {
                Log.d("Main", result.getToken() );
                Log.d("Main", result.getToken() );
            }

            @Override
            public void onError(HttpError error) {
                Log.d("Main", error.toString() );
                Log.d("Main", error.toString() );
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
