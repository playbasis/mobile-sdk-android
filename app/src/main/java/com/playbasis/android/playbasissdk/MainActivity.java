package com.playbasis.android.playbasissdk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.playbasis.android.playbasissdk.api.AuthToken;
import com.playbasis.android.playbasissdk.api.BadgeApi;
import com.playbasis.android.playbasissdk.api.CommunicationApi;
import com.playbasis.android.playbasissdk.api.EngineApi;
import com.playbasis.android.playbasissdk.api.GoodsApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.PlayerApi;
import com.playbasis.android.playbasissdk.api.QuestApi;
import com.playbasis.android.playbasissdk.api.QuizApi;
import com.playbasis.android.playbasissdk.api.RedeemApi;
import com.playbasis.android.playbasissdk.api.ServiceApi;
import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.ActionConfig;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.Gender;
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.playbasissdk.model.Point;
import com.playbasis.android.playbasissdk.model.Quest;
import com.playbasis.android.playbasissdk.model.Quiz;
import com.playbasis.android.playbasissdk.model.QuizDetail;
import com.playbasis.android.playbasissdk.model.Rank;
import com.playbasis.android.playbasissdk.model.Ranks;
import com.playbasis.android.playbasissdk.model.RedeemEvent;
import com.playbasis.android.playbasissdk.model.Rule;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Playbasis playbasis = new Playbasis.Builder(this)
                .setApiKey("3026965093")
                .setApiSecret("ce9c9335d542674a2a3e286307dba8c0")
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


        PlayerApi.badges(playbasis, "jontestuser", new OnResult<List<Badge>>() {
            @Override
            public void onSuccess(List<Badge> result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });
        
        PlayerApi.ranks(playbasis, null, new OnResult<Ranks>() {
            @Override
            public void onSuccess(Ranks result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });
        
        PlayerApi.rank(playbasis, "exp", null ,new OnResult<List<Rank>>() {
            @Override
            public void onSuccess(List<Rank> result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });

        PlayerApi.quest(playbasis, "jontestuser", "54c0ad73be120b42388b47f7", new OnResult<Quest>() {
            @Override
            public void onSuccess(Quest result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        } );

        PlayerApi.quests(playbasis, "jontestuser", new OnResult<List<Quest>>() {
            @Override
            public void onSuccess(List<Quest> result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        } );
        
        

        BadgeApi.badges(playbasis, new OnResult<List<Badge>>() {
            @Override
            public void onSuccess(List<Badge> result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });
        
        BadgeApi.badge(playbasis, "54c0adc9be120b42388b482d", new OnResult<Badge>() {
            @Override
            public void onSuccess(Badge result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        } );

        GoodsApi.listInfo(playbasis,  new OnResult<List<Goods>>() {
            @Override
            public void onSuccess(List<Goods> result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });
        
        GoodsApi.info(playbasis,  "54c0b24cbe120b9b388b45c5", new OnResult<Goods>() {
            @Override
            public void onSuccess(Goods result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });
        
        GoodsApi.groupAvailable(playbasis, "jontestuser", "54c0b24cbe120b9b388b45c5", null,
                new OnResult<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                Log.d("main", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("main", error.toString());
            }
        });
        
        QuestApi.questsAvailable(playbasis,"jontestuser", new OnResult<List<Quest>>() {
            @Override
            public void onSuccess(List<Quest> result) {
                Log.d("","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        } );

        QuestApi.cancel(playbasis, false, "54c0ad73be120b42388b47f7", "jontestuser", new OnResult<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        } );

        QuestApi.join(playbasis, false, "54c0ad73be120b42388b47f7", "jontestuser", new OnResult<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        } );

        QuestApi.joinAll(playbasis, true,  "jontestuser", new OnResult<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        });
        
        QuizApi.detail(playbasis, "54c09131be120bee348b52c9", "jontestuser", new OnResult<QuizDetail>() {
            @Override
            public void onSuccess(QuizDetail result) {
                Log.d("","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        });

        RedeemApi.goods(playbasis, false, "54c0b24cbe120b9b388b45c5", "jontestuser", 1, new OnResult<List<RedeemEvent>>() {
            @Override
            public void onSuccess(List<RedeemEvent> result) {
                Log.d("","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        } );

        CommunicationApi.sendEmail(playbasis, false, "jontestuser", "test", "test", null, new OnResult<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
                Log.d("","");
                Log.d("","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
                Log.d("","");
            }
        } );

        EngineApi.actionConfig(playbasis, new OnResult<ActionConfig>() {
            @Override
            public void onSuccess(ActionConfig result) {
                Log.d("","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        });
        
        EngineApi.rule(playbasis, false, "like", "jontestuser", null,null,null, new OnResult<Rule>() {
            @Override
            public void onSuccess(Rule result) {
                Log.d("","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        });
        EngineApi.rule(playbasis, true, "like", "jontestuser", null,null,null, new OnResult<Rule>() {
            @Override
            public void onSuccess(Rule result) {
                Log.d("","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        });

        ServiceApi.recentPoint(playbasis,null,null,null, new OnResult<List<Point>>() {
            @Override
            public void onSuccess(List<Point> result) {
                Log.d("","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
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
