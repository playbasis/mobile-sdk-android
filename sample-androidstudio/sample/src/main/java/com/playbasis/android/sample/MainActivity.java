package com.playbasis.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.playbasis.android.playbasissdk.api.EngineApi;
import com.playbasis.android.playbasissdk.api.AuthApi;
import com.playbasis.android.playbasissdk.api.AuthToken;
import com.playbasis.android.playbasissdk.api.EngineApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.OrganizationApi;
import com.playbasis.android.playbasissdk.api.PlayerApi;
import com.playbasis.android.playbasissdk.api.QuestApi;
import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.MonthlySaleReport;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.playbasissdk.model.Quest;
import com.playbasis.android.playbasissdk.model.Role;
import com.playbasis.android.playbasissdk.model.Rule;
import com.playbasis.android.playbasissdk.model.RuleAction;
import com.playbasis.android.playbasissdk.model.RuleDetail;
import com.playbasis.android.playbasissdk.model.RuleReward;
import com.playbasis.android.playbasissdk.model.SaleReport;
import com.playbasis.android.playbasissdk.model.UIEvent;
import com.playbasis.android.playbasissdk.model.Event;

import org.json.JSONObject;

import java.util.Map;


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
                PlayerApi.requestOtp(SampleApplication.playbasis, "sm1", new OnResult<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(result);
                    }

                    @Override
                    public void onError(HttpError error) {

                    }
                });

                PlayerApi.saleReport(SampleApplication.playbasis, "sm1", null, null, null, null, new OnResult<ArrayList<SaleReport>>() {

                    @Override
                    public void onSuccess(ArrayList<SaleReport> result) {
                        System.out.println(result);
                    }

                    @Override
                    public void onError(HttpError error) {

                    }
                });
                PlayerApi.getRole(SampleApplication.playbasis, "sm1", "567c069c5e232a194f8b57fe", new OnResult<ArrayList<Role>>() {

                    @Override
                    public void onSuccess(ArrayList<Role> result) {
                        System.out.println("Role");
                        System.out.println(result.size());
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

                OrganizationApi.getSalesHistory(SampleApplication.playbasis, "567c069c5e232a194f8b57fe", 6, null, null, null, null, new OnResult<ArrayList<MonthlySaleReport>>() {
                    @Override
                    public void onSuccess(ArrayList<MonthlySaleReport> result) {
                        System.out.println("Success getSalesHistory");
                        for (MonthlySaleReport monthlySaleReport : result) {
                            System.out.println(monthlySaleReport.getMonth() + ":" + monthlySaleReport.getYear() + "   " + monthlySaleReport.getAmount() + "   " + monthlySaleReport.getPreviousAmount() + "   " + monthlySaleReport.getPercentChanged());
                        }
                    }

                    @Override
                    public void onError(HttpError error) {
                        System.out.println("Error getSalesHistory");
                    }
                });

                PlayerApi.getRole(SampleApplication.playbasis, "sm1", "567c069c5e232a194f8b57fe", new OnResult<ArrayList<Role>>() {
                    @Override
                    public void onSuccess(ArrayList<Role> roles) {
                        Role currentRole = roles.get(0);
                        final String roleName = currentRole.getRoleName();

                        System.out.println("roleName" + roleName);

                    }

                    @Override
                    public void onError(HttpError httpError) {

                    }
                });
                // OrganizationApi.getSalesHistory(​SampleApplication.playbasis, "567c069c5e232a194f8b57fe", 6, null, null, null, null, new OnResult<>())
                //PlayerApi
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
