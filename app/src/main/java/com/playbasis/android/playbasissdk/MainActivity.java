package com.playbasis.android.playbasissdk;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.Api;
import com.playbasis.android.playbasissdk.api.BadgeApi;
import com.playbasis.android.playbasissdk.api.CommunicationApi;
import com.playbasis.android.playbasissdk.api.EngineApi;
import com.playbasis.android.playbasissdk.api.GoodsApi;
import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.PlayerApi;
import com.playbasis.android.playbasissdk.api.QuestApi;
import com.playbasis.android.playbasissdk.api.QuizApi;
import com.playbasis.android.playbasissdk.api.ServiceApi;
import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Action;
import com.playbasis.android.playbasissdk.model.ActionConfig;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.Event;
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.Level;
import com.playbasis.android.playbasissdk.model.Mission;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.playbasissdk.model.Point;
import com.playbasis.android.playbasissdk.model.PointDetail;
import com.playbasis.android.playbasissdk.model.Quest;
import com.playbasis.android.playbasissdk.model.Quiz;
import com.playbasis.android.playbasissdk.model.QuizDetail;
import com.playbasis.android.playbasissdk.model.QuizPending;
import com.playbasis.android.playbasissdk.model.QuizQuestion;
import com.playbasis.android.playbasissdk.model.QuizRank;
import com.playbasis.android.playbasissdk.model.Rank;
import com.playbasis.android.playbasissdk.model.Ranks;
import com.playbasis.android.playbasissdk.model.Reward;
import com.playbasis.android.playbasissdk.model.RuleAction;
import com.playbasis.android.playbasissdk.model.Rule;
import com.playbasis.android.playbasissdk.model.StoredRequest;
import com.playbasis.android.playbasissdk.model.UIEvent;
import com.playbasis.android.playbasissdk.secure.RequestStorage;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class MainActivity extends FragmentActivity {


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button send = (Button) findViewById(R.id.button_send);
        Button resend = (Button) findViewById(R.id.button_resend);

        final Playbasis playbasis = new Playbasis.Builder(this)
                .setApiKey("3416989394")
                .setApiSecret("b1fa1529410702557a6fe2f3913768a0")
                .build();
        
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playbasis.Do("gregusertest", RuleAction.CLICK, new OnResult<Rule>() {
                    @Override
                    public void onSuccess(Rule result) {
                        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(HttpError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestStorage storage = new RequestStorage(playbasis.getContext());
                List<StoredRequest> requests = storage.loadAll();
                if (requests != null && requests.size() > 0) {
                    for (StoredRequest request : requests) {
/*                        if (request != null) asyncPost(playbasis, request.getUrl(), request.getTimestamp(),
                                request.paramsToJson(),
                                null);*/
                        if (request !=null) Api.JsonObjectPOST(playbasis, SDKUtil.SERVER_URL + request.getUrl(),
                                request.getTimestamp(),
                                request.paramsToValuePair(),
                                new OnResult<JSONObject>() {
                                    @Override
                                    public void onSuccess(JSONObject result) {
                                        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onError(HttpError error) {
                                        Toast.makeText(getApplicationContext(), error.toString(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                }
            }
        });



      PlayerApi.register(playbasis, false, new Player(null, "greg", "greg"), new OnResult<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                Log.d("quiz", "detail: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("COMPARE", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        
        


/*        CommunicationApi.sendSms(playbasis, "gregusertest", "salut", null, new OnResult<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
                Log.d("quiz", "random: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "detail: " + (error.requestError != null ? error.requestError.toString() : error.toString()));
            }
        });*/


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

    public void highLevelTest(Playbasis playbasis){
        
        playbasis.Track("gregusertest", RuleAction.BUY);
        playbasis.Track("gregusertest", RuleAction.CHECKIN);
        playbasis.Track("gregusertest", RuleAction.CLICK);
        playbasis.Track("gregusertest", RuleAction.COMMENT);
        playbasis.Track("gregusertest", RuleAction.COMPARE);
        playbasis.Track("gregusertest", RuleAction.FBCOMMENT);
        playbasis.Track("gregusertest", RuleAction.FBLIKE);
        playbasis.Track("gregusertest", RuleAction.FBPOST);
        playbasis.Track("gregusertest", RuleAction.FBSTATUS);
        playbasis.Track("gregusertest", RuleAction.LIKE);
        playbasis.Track("gregusertest", RuleAction.LOGIN);
        playbasis.Track("gregusertest", RuleAction.LOGOUT);
        playbasis.Track("gregusertest", RuleAction.LOVE);
        playbasis.Track("gregusertest", RuleAction.ORDER);
        playbasis.Track("gregusertest", RuleAction.PAYMENT);
        playbasis.Track("gregusertest", RuleAction.READ);
        playbasis.Track("gregusertest", RuleAction.REDEEM);
        playbasis.Track("gregusertest", RuleAction.REGISTER);
        playbasis.Track("gregusertest", RuleAction.REVIEW);
        playbasis.Track("gregusertest", RuleAction.SHARE);
        playbasis.Track("gregusertest", RuleAction.TWEET);
        playbasis.Track("gregusertest", RuleAction.VISIT);
        playbasis.Track("gregusertest", RuleAction.WANT);
        playbasis.Track("gregusertest", RuleAction.WATCH);
       
        playbasis.Do("gregusertest", RuleAction.COMPARE, new OnResult<Rule>() {
            @Override
            public void onSuccess(Rule result) {
                Log.d("COMPARE", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("COMPARE", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

    }

    public void lowLevelTest(Playbasis playbasis){


/*        playbasis.getAuthenticator().requestAuthToken(new OnResult<AuthToken>() {
            @Override
            public void onSuccess(AuthToken result) {
                Log.d("requestAuthToken", result.getToken());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("requestAuthToken", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });*/



        //Test Quiz

        QuizApi.activeList(playbasis, null, new OnResult<List<Quiz>>() {
            @Override
            public void onSuccess(List<Quiz> result) {
                Log.d("quiz", "ActiveList: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "ActiveList: " + (error.requestError!=null? error.requestError.toString() : error.toString()) );
            }
        });


        QuizApi.detail(playbasis, "54ed874bbe120bc32a8b45b8", null, new OnResult<QuizDetail>() {
            @Override
            public void onSuccess(QuizDetail result) {
                Log.d("quiz", "detail: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "detail: " + (error.requestError!=null? error.requestError.toString() : error.toString()) );
            }
        });

        QuizApi.random(playbasis, "gregtestuser", new OnResult<Quiz>() {
            @Override
            public void onSuccess(Quiz result) {
                Log.d("quiz", "random: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "random: " + (error.requestError!=null? error.requestError.toString() : error.toString()) );
            }
        });

        QuizApi.questions(playbasis, "54eaee12be120b92398b45db", "gregtestuser", new OnResult<QuizQuestion>() {
            @Override
            public void onSuccess(QuizQuestion result) {
                Log.d("quiz", "qestions: " + result.toString());

            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "qestions: " + (error.requestError!=null? error.requestError.toString() : error.toString()));

            }
        });

        QuizApi.recentPending(playbasis, "gregtestuser" , null, new OnResult<List<QuizPending>>() {
            @Override
            public void onSuccess(List<QuizPending> result) {
                Log.d("quiz", "recentPending: " + result.toString());

            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "recentPending: " + (error.requestError!=null? error.requestError.toString() : error.toString()) );

            }
        } );
        
        
/*        QuizApi.answerQuestion(playbasis, false, "54eaee12be120b92398b45db" , "gregtestuser",
                "3157806f3f77a416a27eaac1" , "33095474a08f442a1854ede6", new OnResult<QuizQuestionAnswer>() {
                    @Override
                    public void onSuccess(QuizQuestionAnswer result) {
                        Log.d("quiz", "answerQuestion: " + result.toString());
                    }

                    @Override
                    public void onError(HttpError error) {
                        Log.d("quiz", "answerQuestion: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
                    }
                });*/

        QuizApi.recentDone(playbasis, "gregtestuser", null , new OnResult<List<Quiz>>() {
            @Override
            public void onSuccess(List<Quiz> result) {
                Log.d("quiz", "recentDone: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "recentDone: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        QuizApi.rank(playbasis, "54eaee12be120b92398b45db" , null , new OnResult<List<QuizRank>>() {
            @Override
            public void onSuccess(List<QuizRank> result) {
                Log.d("quiz", "rank: " + result.toString());

            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "rank: " + (error.requestError!=null? error.requestError.toString() : error.toString()));

            }
        });



        //Test Quest

        QuestApi.listInfo(playbasis,new OnResult<List<Quest>>() {
            @Override
            public void onSuccess(List<Quest> result) {
                Log.d("Quest", "listInfo: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("Quest", "listInfo: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        QuestApi.info(playbasis, "54eb1128be120bd03a8b4578", new OnResult<Quest>() {
            @Override
            public void onSuccess(Quest result) {
                Log.d("Quest", "info: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("Quest", "info: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        QuestApi.missionInfo(playbasis, "54eb1128be120bd03a8b4578", "54eb1128be120bd03a8b4577", new OnResult<Mission>() {
            @Override
            public void onSuccess(Mission result) {
                Log.d("Quest", "missionInfo: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("Quest", "missionInfo: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        QuestApi.questsAvailable(playbasis, "gregtestuser", new OnResult<List<Quest>>() {
            @Override
            public void onSuccess(List<Quest> result) {
                Log.d("Quest", "questsAvailable: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("Quest", "questsAvailable: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        QuestApi.questAvailable(playbasis, "gregtestuser", "54eb1128be120bd03a8b4578", new OnResult<Event>() {
            @Override
            public void onSuccess(Event result) {
                Log.d("Quest", "questsAvailable: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("Quest", "questsAvailable: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        final  Playbasis pb = playbasis;
        QuestApi.join(playbasis, false,  "54eb1128be120bd03a8b4578", "gregtestuser", new OnResult<Event>() {
            @Override
            public void onSuccess(Event result) {
                QuestApi.cancel(pb, false,  "54eb1128be120bd03a8b4578", "gregtestuser", new OnResult<Event>() {
                    @Override
                    public void onSuccess(Event result) {
                        Log.d("Quest", "questsAvailable: " + result.toString());
                    }

                    @Override
                    public void onError(HttpError error) {
                        Log.e("Quest", "cancel: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
                    }
                });
            }
            @Override
            public void onError(HttpError error) {
                Log.e("Quest", "join: " + (error.requestError!=null? error.requestError.toString() : (error.requestError!=null? error.requestError.toString() : error.toString())));
            }
        } );

        QuestApi.joinAll(playbasis, false, "gregtestuser", new OnResult<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("Quest", "questsAvailable: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("Quest", "questsAvailable: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        } );

        // Goods
        GoodsApi.listInfo(playbasis, new OnResult<List<Goods>>() {
            @Override
            public void onSuccess(List<Goods> result) {
                Log.d("GoodsApi", "listInfo: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("GoodsApi", "listInfo: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        GoodsApi.info(playbasis, "54ebe635be120b81148b499a",  new OnResult<Goods>() {
            @Override
            public void onSuccess(Goods result) {
                Log.d("GoodsApi", "info: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("GoodsApi", "info: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        GoodsApi.groupAvailable(playbasis, "gregtestuser" , "mygrpup", null, new OnResult<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                Log.d("GoodsApi", "groupAvailable: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("GoodsApi", "groupAvailable: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });
        
/*        RedeemApi.goods(playbasis, false, "54ebe635be120b81148b499a", "gregtestuser", 1, new OnResult<List<RedeemEvent>>() {
            @Override
            public void onSuccess(List<RedeemEvent> result) {
                Log.d("RedeemApi", "goods: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("RedeemApi", "goods: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        } );
        
        RedeemApi.goodsGroup(playbasis, false, "mygroup", "gregtestuser" , 1 , new OnResult<List<RedeemEvent>>() {
            @Override
            public void onSuccess(List<RedeemEvent> result) {
                Log.d("RedeemApi", "goodsGroup: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("RedeemApi", "goodsGroup: " + (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });*/


        //badges

        BadgeApi.badges(playbasis, new OnResult<List<Badge>>() {
            @Override
            public void onSuccess(List<Badge> result) {
                Log.d("badges", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("badges", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

/*        BadgeApi.badge(playbasis, "54eb1187be120bd13a8b458d", new OnResult<Badge>() {
            @Override
            public void onSuccess(Badge result) {
                Log.d("badge", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("badge", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        } );
        
        PlayerApi.claimBadge(playbasis, false, "gregtestuser", "54eb1187be120bd13a8b458d",
                new OnResult<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        Log.d("badge", result.toString());
                    }

                    @Override
                    public void onError(HttpError error) {
                        Log.d("badge", (error.requestError!=null? error.requestError.toString() : error.toString()));
                    }
                });*/

        PlayerApi.rank(playbasis, "point", null, new OnResult<List<Rank>>() {
            @Override
            public void onSuccess(List<Rank> result) {
                Log.d("rank", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("rank", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        } );

        PlayerApi.ranks(playbasis, null, new OnResult<Ranks>() {
            @Override
            public void onSuccess(Ranks result) {
                Log.d("ranks", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("ranks", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        PlayerApi.levels(playbasis, new OnResult<List<Level>>() {
            @Override
            public void onSuccess(List<Level> result) {
                Log.d("levels", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("levels", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        PlayerApi.level(playbasis, "2", new OnResult<Level>() {
            @Override
            public void onSuccess(Level result) {
                Log.d("level", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("level", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        //Communication
/*
        CommunicationApi.sendEmail(playbasis, false, "gregtestuser", "test", "test", null, 
                new OnResult<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
                Log.d("sendEmail","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        } );

        CommunicationApi.sendSms(playbasis, false, "gregtestuser", "test", null, new OnResult<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
                Log.d("sendEmail","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        } );

        CommunicationApi.sendPush(playbasis, false, "gregtestuser", "test", null, new OnResult<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
                Log.d("sendEmail","");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("","");
            }
        } );*/

        //Player

        PlayerApi.getPlayerInfo(playbasis, "gregtestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("getPlayerInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("getPlayerInfo", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        } );

        PlayerApi.getPlayerPrivateInfo(playbasis, "gregtestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("getPlayerPrivateInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("getPlayerPrivateInfo", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        PlayerApi.getListPlayerInfo(playbasis, Arrays.asList("gregtestuser"), new OnResult<List<Player>>() {
            @Override
            public void onSuccess(List<Player> result) {
                Log.d("getListPlayerInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("getListPlayerInfo", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        PlayerApi.getDetailedPlayerListInfo(playbasis, "gregtestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("getDetailedListInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("getDetailedListInfo", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        PlayerApi.getDetailedPlayerPrivateInfo(playbasis, "gregtestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("etDetailedPlayerPrivate", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("etDetailedPlayerPrivate", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });


        PlayerApi.badges(playbasis, "gregtestuser", new OnResult<List<Badge>>() {
            @Override
            public void onSuccess(List<Badge> result) {
                Log.d("badges", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("badges", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });


        PlayerApi.quest(playbasis, "gregtestuser", "54eb1128be120bd03a8b4578", new OnResult<Quest>() {
            @Override
            public void onSuccess(Quest result) {
                Log.d("quest", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("quest", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        } );

        PlayerApi.quests(playbasis, "gregtestuser", new OnResult<List<Quest>>() {
            @Override
            public void onSuccess(List<Quest> result) {
                Log.d("quests", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("quests", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        } );

        PlayerApi.questReward(playbasis, "gregtestuser", null, null , new OnResult<List<Reward>>() {
            @Override
            public void onSuccess(List<Reward> result) {
                Log.d("questReward", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("questReward", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        PlayerApi.points(playbasis, "gregtestuser", new OnResult<List<Point>>() {
            @Override
            public void onSuccess(List<Point> result) {
                Log.d("points", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("points", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        PlayerApi.point(playbasis , "gregtestuser", "point", new OnResult<List<Point>>() {
            @Override
            public void onSuccess(List<Point> result) {
                Log.d("point", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("point", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        PlayerApi.pointHistory(playbasis, "gregtestuser", null, null , null , new OnResult<List<PointDetail>>() {
            @Override
            public void onSuccess(List<PointDetail> result) {
                Log.d("pointHistory", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("pointHistory", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        } );


        PlayerApi.actionTime(playbasis, "gregtestuser", "like", new OnResult<Action>() {
            @Override
            public void onSuccess(Action result) {
                Log.d("actionTime", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("actionTime", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });


        PlayerApi.actionCount(playbasis, "gregtestuser", "like", new OnResult<Action>() {
            @Override
            public void onSuccess(Action result) {
                Log.d("actionCount", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("actionCount", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });


        PlayerApi.actionLast(playbasis, "gregtestuser", new OnResult<Action>() {
            @Override
            public void onSuccess(Action result) {
                Log.d("actionLast", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("actionLast", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });
        
/*        RedeemApi.goods(playbasis, false, "54ec1198be120b29168b4aaa", "gregtestuser", 1 , new OnResult<List<RedeemGood>>() {
            @Override
            public void onSuccess(List<RedeemGood> result) {

            }

            @Override
            public void onError(HttpError error) {
                Log.d("goods", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        } );*/

        PlayerApi.goods(pb, "gregtestuser", new OnResult<List<Goods>>() {
            @Override
            public void onSuccess(List<Goods> result) {
                Log.d("goods", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("goods", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        EngineApi.actionConfig(playbasis, new OnResult<List<ActionConfig>>() {
            @Override
            public void onSuccess(List<ActionConfig> result) {
                Log.d("actionConfig", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("actionConfig", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        });

        ServiceApi.recentPoint(playbasis, null,null,null,new OnResult<List<PointDetail>>() {
            @Override
            public void onSuccess(List<PointDetail> result) {
                Log.d("recentPoint", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("recentPoint", (error.requestError!=null? error.requestError.toString() : error.toString()));
            }
        }  );
        
    }

}
