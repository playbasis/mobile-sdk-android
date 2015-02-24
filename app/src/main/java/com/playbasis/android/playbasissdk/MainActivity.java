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
import com.playbasis.android.playbasissdk.model.Action;
import com.playbasis.android.playbasissdk.model.ActionConfig;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.Event;
import com.playbasis.android.playbasissdk.model.Goods;
import com.playbasis.android.playbasissdk.model.Level;
import com.playbasis.android.playbasissdk.model.Mission;
import com.playbasis.android.playbasissdk.model.Player;
import com.playbasis.android.playbasissdk.model.Point;
import com.playbasis.android.playbasissdk.model.PointHistory;
import com.playbasis.android.playbasissdk.model.Quest;
import com.playbasis.android.playbasissdk.model.Quiz;
import com.playbasis.android.playbasissdk.model.QuizDetail;
import com.playbasis.android.playbasissdk.model.QuizPending;
import com.playbasis.android.playbasissdk.model.QuizQuestion;
import com.playbasis.android.playbasissdk.model.QuizQuestionAnswer;
import com.playbasis.android.playbasissdk.model.QuizRank;
import com.playbasis.android.playbasissdk.model.Rank;
import com.playbasis.android.playbasissdk.model.Ranks;
import com.playbasis.android.playbasissdk.model.RedeemEvent;
import com.playbasis.android.playbasissdk.model.RedeemGood;
import com.playbasis.android.playbasissdk.model.Reward;
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
                .setApiKey("3416989394")
                .setApiSecret("b1fa1529410702557a6fe2f3913768a0")
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
        
        
        
        //Test Quiz
        
        QuizApi.activeList(playbasis, null, new OnResult<List<Quiz>>() {
            @Override
            public void onSuccess(List<Quiz> result) {
                Log.d("quiz", "ActiveList: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "ActiveList: " + error.toString() );
            }
        });
        
        
        QuizApi.detail(playbasis, "54c09131be120bee348b52c9", null, new OnResult<QuizDetail>() {
            @Override
            public void onSuccess(QuizDetail result) {
                Log.d("quiz", "detail: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "detail: " + error.toString() );
            }
        });
        
        QuizApi.random(playbasis, "gregtestuser", new OnResult<Quiz>() {
            @Override
            public void onSuccess(Quiz result) {
                Log.d("quiz", "random: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "random: " + error.toString() );
            }
        });
        
        QuizApi.questions(playbasis, "54eaee12be120b92398b45db", "gregtestuser", new OnResult<QuizQuestion>() {
            @Override
            public void onSuccess(QuizQuestion result) {
                Log.d("quiz", "qestions: " + result.toString());

            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "qestions: " + error.toString());

            }
        });
        
        QuizApi.recentPending(playbasis, "gregtestuser" , null, new OnResult<List<QuizPending>>() {
            @Override
            public void onSuccess(List<QuizPending> result) {
                Log.d("quiz", "recentPending: " + result.toString());

            }

            @Override
            public void onError(HttpError error) {
                Log.e("quiz", "recentPending: " + error.toString() );

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
                        Log.d("quiz", "answerQuestion: " + error.toString());
                    }
                });*/
        
        QuizApi.recentDone(playbasis, "gregtestuser", null , new OnResult<List<Quiz>>() {
            @Override
            public void onSuccess(List<Quiz> result) {
                Log.d("quiz", "recentDone: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("quiz", "recentDone: " + error.toString());
            }
        });
        
        QuizApi.rank(playbasis, "54eaee12be120b92398b45db" , null , new OnResult<List<QuizRank>>() {
            @Override
            public void onSuccess(List<QuizRank> result) {
                Log.d("quiz", "rank: " + result.toString());

            }

            @Override
            public void onError(HttpError error) {
                Log.d("quiz", "rank: " + error.toString());

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
                Log.d("Quest", "listInfo: " + error.toString());
            }
        });
        
        QuestApi.info(playbasis, "54eb1128be120bd03a8b4578", new OnResult<Quest>() {
            @Override
            public void onSuccess(Quest result) {
                Log.d("Quest", "info: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("Quest", "info: " + error.toString());
            }
        });
        
        QuestApi.missionInfo(playbasis, "54eb1128be120bd03a8b4578", "54eb1128be120bd03a8b4577", new OnResult<Mission>() {
            @Override
            public void onSuccess(Mission result) {
                Log.d("Quest", "missionInfo: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("Quest", "missionInfo: " + error.toString());
            }
        });
        
        QuestApi.questsAvailable(playbasis, "gregtestuser", new OnResult<List<Quest>>() {
            @Override
            public void onSuccess(List<Quest> result) {
                Log.d("Quest", "questsAvailable: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("Quest", "questsAvailable: " + error.toString());
            }
        });
        
        QuestApi.questAvailable(playbasis, "gregtestuser", "54eb1128be120bd03a8b4578", new OnResult<Event>() {
            @Override
            public void onSuccess(Event result) {
                Log.d("Quest", "questsAvailable: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("Quest", "questsAvailable: " + error.toString());
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

                    }
                });
            }
            @Override
            public void onError(HttpError error) {

            }
        } );
        
        QuestApi.joinAll(playbasis, false, "gregtestuser", new OnResult<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.d("Quest", "questsAvailable: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("Quest", "questsAvailable: " + error.toString());
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
                Log.d("GoodsApi", "listInfo: " + error.toString());
            }
        });
        
        GoodsApi.info(playbasis, "54ebe635be120b81148b499a",  new OnResult<Goods>() {
            @Override
            public void onSuccess(Goods result) {
                Log.d("GoodsApi", "info: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("GoodsApi", "info: " + error.toString());
            }
        });
        
        GoodsApi.groupAvailable(playbasis, "gregtestuser" , "mygrpup", null, new OnResult<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                Log.d("GoodsApi", "groupAvailable: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("GoodsApi", "groupAvailable: " + error.toString());
            }
        });
        
/*        RedeemApi.goods(playbasis, false, "54ebe635be120b81148b499a", "gregtestuser", 1, new OnResult<List<RedeemEvent>>() {
            @Override
            public void onSuccess(List<RedeemEvent> result) {
                Log.d("RedeemApi", "goods: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("RedeemApi", "goods: " + error.toString());
            }
        } );
        
        RedeemApi.goodsGroup(playbasis, false, "mygroup", "gregtestuser" , 1 , new OnResult<List<RedeemEvent>>() {
            @Override
            public void onSuccess(List<RedeemEvent> result) {
                Log.d("RedeemApi", "goodsGroup: " + result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("RedeemApi", "goodsGroup: " + error.toString());
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
                Log.d("badges", error.toString());
            }
        });

/*        BadgeApi.badge(playbasis, "54eb1187be120bd13a8b458d", new OnResult<Badge>() {
            @Override
            public void onSuccess(Badge result) {
                Log.d("badge", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("badge", error.toString());
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
                        Log.d("badge", error.toString());
                    }
                });*/
        
        PlayerApi.rank(playbasis, "point", null, new OnResult<List<Rank>>() {
            @Override
            public void onSuccess(List<Rank> result) {
                Log.d("rank", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("rank", error.toString());
            }
        } );
        
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
        
        PlayerApi.levels(playbasis, new OnResult<List<Level>>() {
            @Override
            public void onSuccess(List<Level> result) {
                Log.d("levels", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("levels", error.toString());
            }
        });
        
        PlayerApi.level(playbasis, "2", new OnResult<Level>() {
            @Override
            public void onSuccess(Level result) {
                Log.d("level", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("level", error.toString());
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
                Log.d("getPlayerInfo", error.toString());
            }
        } );

        PlayerApi.getPlayerPrivateInfo(playbasis, "gregtestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("getPlayerPrivateInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("getPlayerPrivateInfo", error.toString());
            }
        });

        PlayerApi.getListPlayerInfo(playbasis, Arrays.asList("gregtestuser"), new OnResult<List<Player>>() {
            @Override
            public void onSuccess(List<Player> result) {
                Log.d("getListPlayerInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("getListPlayerInfo", error.toString());
            }
        });

        PlayerApi.getDetailedPlayerListInfo(playbasis, "gregtestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("getDetailedListInfo", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("getDetailedListInfo", error.toString());
            }
        });

        PlayerApi.getDetailedPlayerPrivateInfo(playbasis, "gregtestuser", new OnResult<Player>() {
            @Override
            public void onSuccess(Player result) {
                Log.d("etDetailedPlayerPrivate", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("etDetailedPlayerPrivate", error.toString());
            }
        });


        PlayerApi.badges(playbasis, "gregtestuser", new OnResult<List<Badge>>() {
            @Override
            public void onSuccess(List<Badge> result) {
                Log.d("badges", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("badges", error.toString());
            }
        });


        PlayerApi.quest(playbasis, "gregtestuser", "54eb1128be120bd03a8b4578", new OnResult<Quest>() {
            @Override
            public void onSuccess(Quest result) {
                Log.d("quest", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("quest", error.toString());
            }
        } );

        PlayerApi.quests(playbasis, "gregtestuser", new OnResult<List<Quest>>() {
            @Override
            public void onSuccess(List<Quest> result) {
                Log.d("quests", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("quests", error.toString());
            }
        } );
        
        PlayerApi.questReward(playbasis, "gregtestuser", null, null , new OnResult<List<Reward>>() {
            @Override
            public void onSuccess(List<Reward> result) {
                Log.d("questReward", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("questReward", error.toString());
            }
        });
        
        PlayerApi.points(playbasis, "gregtestuser", new OnResult<List<Point>>() {
            @Override
            public void onSuccess(List<Point> result) {
                Log.d("points", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("points", error.toString());
            }
        });
        
        PlayerApi.point(playbasis , "gregtestuser", "point", new OnResult<List<Point>>() {
            @Override
            public void onSuccess(List<Point> result) {
                Log.d("point", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("point", error.toString());
            }
        });
        
        PlayerApi.pointHistory(playbasis, "gregtestuser", null, null , null , new OnResult<List<PointHistory>>() {
            @Override
            public void onSuccess(List<PointHistory> result) {
                Log.d("pointHistory", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("pointHistory", error.toString());
            }
        } );
        
        
        PlayerApi.actionTime(playbasis, "gregtestuser", "like", new OnResult<Action>() {
            @Override
            public void onSuccess(Action result) {
                Log.d("actionTime", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("actionTime", error.toString());
            }
        });
        
        
        PlayerApi.actionCount(playbasis, "gregtestuser", "like", new OnResult<Action>() {
            @Override
            public void onSuccess(Action result) {
                Log.d("actionCount", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("actionCount", error.toString());
            }
        });
        
        
        PlayerApi.actionLast(playbasis, "gregtestuser", new OnResult<Action>() {
            @Override
            public void onSuccess(Action result) {
                Log.d("actionLast", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("actionLast", error.toString());
            }
        });
        
/*        RedeemApi.goods(playbasis, false, "54ec1198be120b29168b4aaa", "gregtestuser", 1 , new OnResult<List<RedeemGood>>() {
            @Override
            public void onSuccess(List<RedeemGood> result) {

            }

            @Override
            public void onError(HttpError error) {
                Log.d("goods", error.toString());
            }
        } );*/

        PlayerApi.goods(pb, "gregtestuser", new OnResult<List<Goods>>() {
            @Override
            public void onSuccess(List<Goods> result) {
                Log.d("goods", result.toString());
            }

            @Override
            public void onError(HttpError error) {
                Log.d("goods", error.toString());
            }
        });
        

        
        
        
        /*

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
*/



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
