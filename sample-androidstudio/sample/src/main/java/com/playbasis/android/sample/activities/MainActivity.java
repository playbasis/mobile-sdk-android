package com.playbasis.android.sample.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.playbasis.android.playbasissdk.api.AuthApi;
import com.playbasis.android.playbasissdk.api.AuthToken;
import com.playbasis.android.playbasissdk.api.CommunicationApi;
import com.playbasis.android.playbasissdk.api.EngineApi;
import com.playbasis.android.playbasissdk.api.FileApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.PlayerApi;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Image;
import com.playbasis.android.playbasissdk.model.Rule;
import com.playbasis.android.playbasissdk.model.Event;
import com.playbasis.android.sample.R;
import com.playbasis.android.sample.SampleApplication;
import com.playbasis.android.sample.model.SharedVariables;
import com.playbasis.android.sample.model.User;

import org.json.JSONObject;


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

        //PlayerApi.setupPhone(SampleApplication.playbasis,"1",SampleApplication.);

                AuthApi.auth(SampleApplication.playbasis, new OnResult<AuthToken>() {
            @Override
            public void onSuccess(AuthToken result) {
                Log.d("MainActivity", "authen success");
                SampleApplication.playbasis.getAuthenticator().setAuthToken(result);
            }

            @Override
            public void onError(HttpError error) {

            }
        });
PlayerApi.verifyPlayerEmail(SampleApplication.playbasis, "1", new OnResult<JSONObject>() {
    @Override
    public void onSuccess(JSONObject result) {
        Log.d("MainActivity", result.toString());
    }

    @Override
    public void onError(HttpError error) {
       Log.d("MainActivity", error.getMessage());

    }
});
     /*   try {
            setupPhone();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
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

        Button engineButton = (Button) findViewById(R.id.button_engine);
        engineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EngineApi.rule(SampleApplication.playbasis, false, "click", "TestPlayer001", null, null, null, null, new OnResult<Rule>() {
                    @Override
                    public void onSuccess(Rule result) {
                        List<Event> events = result.getEvents();
                        for (Event event : events) {
                            System.out.println(event.getRewardType());
                            System.out.println(event.getValue());
                            System.out.println(event.getIndex());
                            System.out.println(event.getGood());
                            System.out.println(event.getBadgeData());
                        }
                    }

                    @Override
                    public void onError(HttpError error) {
                        Log.d("Engine","failed");
                        if (error.requestError != null) {
                            Log.d("Engine", error.requestError.message);
                        } else {
                            Log.d("Engine", error.toString());
                        }
                    }
                });
/*
                EngineApi.ruleDetail(SampleApplication.playbasis, "56167df4be120b4e353701fa", new OnResult<RuleDetail>() {
                    @Override
                    public void onSuccess(RuleDetail result) {
                        List<RuleReward> rewards = result.getRewards();
                        for(int i = 0; i < rewards.size(); i++) {
                            System.out.println(rewards.get(i).getGood());
                        }
                    }

                    @Override
                    public void onError(HttpError error) {
                        if (error.requestError != null) {
                            Log.d("Engine", error.requestError.message);
                        } else {
                            Log.d("Engine", error.toString());
                        }
                    }
                });*/
            }
        });

        Button testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                FileApi.uploadImage(SampleApplication.playbasis, "images", bm, new OnResult<Image>() {
                    @Override
                    public void onSuccess(Image result) {
                        System.out.println("--------------------------------");
                        System.out.println("--------------------------------");
                        System.out.println(result.getThumbUrl());
                        System.out.println(result.getUrl());
                        System.out.println("--------------------------------");
                        System.out.println("--------------------------------");
                    }

                    @Override
                    public void onError(HttpError error) {

                    }
                });
/*
                EngineApi.ruleDetail(SampleApplication.playbasis, "5698af19472af270068b5caa", "1D", new OnResult<RuleDetail>() {
                    @Override
                    public void onSuccess(RuleDetail result) {
                        System.out.println("Success");
                        System.out.println(result.getRuleGroup());
                        RuleGroup ruleGroup = result.getRuleGroup();

                        System.out.println(ruleGroup.getCategory());
                        System.out.println(ruleGroup.getName());
                        RuleState ruleState = ruleGroup.getRuleState();
                        if(ruleState != null) {
                            System.out.println(ruleState.getInput());
                            Input input = ruleState.getInput();
                            System.out.println(input.getIndex());
                        }
                    }

                    @Override
                    public void onError(HttpError error) {

                    }

                });
                /*PlayerApi.resetPasswordByEmail(SampleApplication.playbasis, "kittikorn.a@playbasis.com", new OnResult<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(result);
                    }

                    @Override
                    public void onError(HttpError error) {
                        System.out.println("Error");
                        System.out.println(error.requestError.message);
                    }
                });*/


                /*
                PlayerApi.requestOtp(SampleApplication.playbasis, "sm1", new OnResult<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(result);
                    }

                    @Override
                    public void onError(HttpError error) {

                    }
                });

                PlayerApi.auth(SampleApplication.playbasis, null, "sm1", "12345678", null, new OnResult<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        System.out.println("Success!!!");
                    }

                    @Override
                    public void onError(HttpError error) {
                        error.printStackTrace();
                        System.out.println("Error!!!");
                    }
                });

                /*
                PlayerApi.quests(SampleApplication.playbasis, "sm1", new OnResult<List<Quest>>() {
                    @Override
                    public void onSuccess(List<Quest> result) {
                        for (Quest quest : result) {
                            System.out.println(quest);
                        }
                    }

                    @Override
                    public void onError(HttpError error) {

                    }
                });

                PlayerApi.questsAll(SampleApplication.playbasis, "sm1", new OnResult<List<Quest>>() {
                    @Override
                    public void onSuccess(List<Quest> result) {
                        for (Quest quest : result) {
                            System.out.println(quest);
                        }
                    }

                    @Override
                    public void onError(HttpError error) {

                    }
                });
*/
                //PlayerApi
            }
        });
    }

    private void setupPhone() throws IOException {
        Log.d("MainActivity", " setupPhone");
        InstanceID instanceID = InstanceID.getInstance(this);

        //String deviceToken = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
        String deviceToken="123456789";
        // [END get_token]
        Log.i("setupPhone", "GCM Registration Token: " + deviceToken);

        // TODO: register device deviceToken
        User user = SharedVariables.getInstance().getUser(this);
        PlayerApi.setupPhone(SampleApplication.playbasis, "1", deviceToken, Build.MANUFACTURER, Build.MODEL, "android","+66861234567", new OnResult<String>() {
            @Override
            public void onSuccess(String s) {
                Log.d("MainActivity", "success setupPhone");
            }

            @Override
            public void onError(HttpError httpError) {
                Log.e("MainActivity", httpError.getMessage());

            }
        });
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

}
