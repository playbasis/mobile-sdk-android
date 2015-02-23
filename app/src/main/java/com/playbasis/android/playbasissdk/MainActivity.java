package com.playbasis.android.playbasissdk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.playbasis.android.playbasissdk.api.Api;
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
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.ActionConfig;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.playbasissdk.model.Point;
import com.playbasis.android.playbasissdk.model.Quest;
import com.playbasis.android.playbasissdk.model.QuizDetail;
import com.playbasis.android.playbasissdk.model.Rank;
import com.playbasis.android.playbasissdk.model.Ranks;
import com.playbasis.android.playbasissdk.model.RedeemEvent;
import com.playbasis.android.playbasissdk.model.StoredRequest;
import com.playbasis.android.playbasissdk.model.Rule;
import com.playbasis.android.playbasissdk.secure.RequestStorage;

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

        playbasis.getAuthenticator().requestAuthToken(playbasis, new OnResult<AuthToken>() {
            @Override
            public void onSuccess(AuthToken result) {
                Log.d("requestAuthToken", result.getToken());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("requestAuthToken", error.toString());
            }
        });

        PlayerApi.getPlayerInfo(playbasis, "jontestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("getPlayerInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("getPlayerInfo", error.toString());
            }
        } );

        PlayerApi.getPlayerPrivateInfo(playbasis, "jontestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("getPlayerPrivateInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("getPlayerPrivateInfo", error.toString());
            }
        });

        PlayerApi.getListPlayerInfo(playbasis, Arrays.asList("jontestuser"), new OnResult<List<Player>>() {
            @Override
            public void onSuccess(List<Player> result) {
                Log.d("getListPlayerInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("getListPlayerInfo", error.toString());
            }
        });

        PlayerApi.getDetailedPlayerListInfo(playbasis, "jontestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("getDetailedListInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("getDetailedListInfo", error.toString());
            }
        });

        PlayerApi.getDetailedPlayerPrivateInfo(playbasis, "jontestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("etDetailedPlayerPrivate", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("etDetailedPlayerPrivate", error.toString());
            }
        });


        PlayerApi.badges(playbasis, "jontestuser", new OnResult<List<Badge>>() {
            @Override
            public void onSuccess(List<Badge> result) {
                Log.d("badges", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("badges", error.toString());
            }
        });
        
        PlayerApi.ranks(playbasis, null, new OnResult<Ranks>() {
            @Override
            public void onSuccess(Ranks result) {
                Log.d("ranks", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("ranks", error.toString());
            }
        });
        
        PlayerApi.rank(playbasis, "exp", null ,new OnResult<List<Rank>>() {
            @Override
            public void onSuccess(List<Rank> result) {
                Log.d("rank", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("rank", error.toString());
            }
        });

        PlayerApi.quest(playbasis, "jontestuser", "54c0ad73be120b42388b47f7", new OnResult<Quest>() {
            @Override
            public void onSuccess(Quest result) {
                Log.d("quest", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("quest", error.toString());
            }
        } );

        PlayerApi.quests(playbasis, "jontestuser", new OnResult<List<Quest>>() {
            @Override
            public void onSuccess(List<Quest> result) {
                Log.d("quests", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("quests", error.toString());
            }
        } );
        
        

        BadgeApi.badges(playbasis, new OnResult<List<Badge>>() {
            @Override
            public void onSuccess(List<Badge> result) {
                Log.d("badges", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("badges", error.toString());
            }
        });
        
        BadgeApi.badge(playbasis, "54c0adc9be120b42388b482d", new OnResult<Badge>() {
            @Override
            public void onSuccess(Badge result) {
                Log.d("badge", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("badge", error.toString());
            }
        } );

        GoodsApi.listInfo(playbasis,  new OnResult<List<Goods>>() {
            @Override
            public void onSuccess(List<Goods> result) {
                Log.d("listInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("listInfo", error.toString());
            }
        });
        
        GoodsApi.info(playbasis,  "54c0b24cbe120b9b388b45c5", new OnResult<Goods>() {
            @Override
            public void onSuccess(Goods result) {
                Log.d("info", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("info", error.toString());
            }
        });
        
        GoodsApi.groupAvailable(playbasis, "jontestuser", "54c0b24cbe120b9b388b45c5", null,
                new OnResult<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                Log.d("groupAvailable", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("groupAvailable", error.toString());
            }
        });
        
        QuestApi.questsAvailable(playbasis,"jontestuser", new OnResult<List<Quest>>() {
            @Override
            public void onSuccess(List<Quest> result) {
                Log.d("questsAvailable",result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("questsAvailable", error.toString());
            }
        } );

        QuestApi.cancel(playbasis, false, "54c0ad73be120b42388b47f7", "jontestuser", new OnResult<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("cancel","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("cancel","");
            }
        } );

        QuestApi.join(playbasis, false, "54c0ad73be120b42388b47f7", "jontestuser", new OnResult<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("join","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("join","");
            }
        } );

        QuestApi.joinAll(playbasis, true,  "jontestuser", new OnResult<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("joinAll","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("joinAll","");
            }
        });
        
        QuizApi.detail(playbasis, "54c09131be120bee348b52c9", "jontestuser", new OnResult<QuizDetail>() {
            @Override
            public void onSuccess(QuizDetail result) {
                Log.d("detail","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("detail","");
            }
        });

        RedeemApi.goods(playbasis, false, "54c0b24cbe120b9b388b45c5", "jontestuser", 1, new OnResult<List<RedeemEvent>>() {
            @Override
            public void onSuccess(List<RedeemEvent> result) {
                Log.d("goods","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("goods","");
            }
        } );

        CommunicationApi.sendEmail(playbasis, false, "jontestuser", "test", "test", null, new OnResult<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
                Log.d("sendEmail","");
                Log.d("sendEmail","");
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
                Log.d("actionConfig","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("actionConfig","");
            }
        });
        
        EngineApi.rule(playbasis, false, "like", "jontestuser", null,null,null, new OnResult<Rule>() {
            @Override
            public void onSuccess(Rule result) {
                Log.d("rule","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("rule","");
            }
        });
        EngineApi.rule(playbasis, true, "like", "jontestuser", null,null,null, new OnResult<Rule>() {
            @Override
            public void onSuccess(Rule result) {
                Log.d("rule","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("rule","");
            }
        });

        ServiceApi.recentPoint(playbasis,null,null,null, new OnResult<List<Point>>() {
            @Override
            public void onSuccess(List<Point> result) {
                Log.d("recentPoint","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("recentPoint","");
            }
        });


        Api.resendRequests(playbasis);

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
