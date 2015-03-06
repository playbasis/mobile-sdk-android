package com.playbasis.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.PlayerApi;
import com.playbasis.android.playbasissdk.api.QuestApi;
import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Rule;
import com.playbasis.android.playbasissdk.model.RuleAction;
import com.playbasis.android.playbasissdk.model.UIEvent;


public class MainActivity extends FragmentActivity {

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
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
/*        SampleApplication.playbasis.Do("gfdgsdfgsdfg", RuleAction.CLICK, new OnResult<Rule>() {
            @Override
            public void onSuccess(Rule result) {
                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(HttpError error) {
                if(error.requestError!=null && error.requestError.message != null)
                    Toast.makeText(MainActivity.this, error.requestError.message, Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });*/



        Button playerButton = (Button) findViewById(R.id.button_player);
        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                startActivity(intent);

            }
        });
        
        Button quizButton = (Button) findViewById(R.id.button_quiz);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        Button badgeButton = (Button) findViewById(R.id.button_badge);
        badgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BadgeActivity.class);
                startActivity(intent);
            }
        });

        Button rewardButton = (Button) findViewById(R.id.button_reward);
        rewardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RewardActivity.class);
                startActivity(intent);
            }
        });

        Button questButton = (Button) findViewById(R.id.button_quest);
        questButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuestActivity.class);
                startActivity(intent);
            }
        });

        Button testButton = (Button) findViewById(R.id.button_test);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SampleApplication.playbasis.Do("exampleplayer", UIEvent.TOUCH, new OnResult<Rule>() {
                    @Override
                    public void onSuccess(final Rule result) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onError(final HttpError error) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        
                    }
                });
            }
        });

     //   SampleApplication.playbasis.Track("testFakeUser", "click");

/*        final Playbasis playbasis = new Playbasis.Builder(this)
                .setApiKey("3416989394")
                .setApiSecret("b1fa1529410702557a6fe2f3913768a0")
                .build();
        playbasis.setActivity(this);

        PlayerApi.register(playbasis, false, null, new OnResult<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                Log.d("quiz", "detail: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("COMPARE", (error.requestError != null ? error.requestError.toString() : error.toString()));
            }
        });*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void uieventTest(Playbasis playbasis){
        playbasis.Track("gregusertest", "click");
        playbasis.Track("gregusertest", UIEvent.LONG_CLICK);
        playbasis.Track("gregusertest", UIEvent.FOCUS_CHANGE);
        playbasis.Track("gregusertest", UIEvent.MENU_ITEM);
        playbasis.Track("gregusertest", UIEvent.TOUCH);
        playbasis.Track("gregusertest", UIEvent.KEY);
        playbasis.Track("gregusertest", UIEvent.VIEW_CREATE);
        playbasis.Track("gregusertest", UIEvent.VIEW_DISPLAY);
        playbasis.Track("gregusertest", UIEvent.VIEW_REMOVE);
        playbasis.Track("gregusertest", UIEvent.VIEW_DESTROY);

    }
}
