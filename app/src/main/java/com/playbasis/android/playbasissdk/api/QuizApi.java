package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Quiz;
import com.playbasis.android.playbasissdk.model.QuizDetail;
import com.playbasis.android.playbasissdk.model.QuizQuestion;
import com.playbasis.android.playbasissdk.model.QuizRank;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class QuizApi extends Api  {
    public static final String TAG = "QuizApi";

    private static void quizzes(@NonNull Playbasis playbasis, @NonNull String uri, List<NameValuePair> params,
                                  final OnResult<List<Quiz>>listener ){

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Quiz> quizzes = JsonHelper.FromJsonArray(result.getJSONArray("result"), Quiz.class);
                    if (listener != null) listener.onSuccess(quizzes);
                } catch (JSONException e) {
                    if (listener != null) listener.onError(new HttpError(e));
                }
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void quiz(@NonNull Playbasis playbasis, @NonNull String uri, List<NameValuePair> params,
                              final OnResult<Quiz>listener ){

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Quiz quiz = JsonHelper.FromJsonObject(result.getJSONObject("result"), Quiz.class);
                    if (listener != null) listener.onSuccess(quiz);
                } catch (JSONException e) {
                    if (listener != null) listener.onError(new HttpError(e));
                }

            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }



    public static void activeList(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<List<Quiz>>listener ){

        String uri = SDKUtil.getServerUrl(false) + SDKUtil._QUIZ_URL  + "list";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));

        quizzes(playbasis,uri,params,listener);
    }

    public static void detail(@NonNull Playbasis playbasis, @NonNull String quizId, @NonNull String playerId,
                              final OnResult<QuizDetail>listener ){

        String uri = SDKUtil.getServerUrl(false) + SDKUtil._QUIZ_URL + quizId + "/detail";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    QuizDetail quiz = JsonHelper.FromJsonObject(result.getJSONObject("result"), QuizDetail.class);
                    if (listener != null) listener.onSuccess(quiz);
                } catch (JSONException e) {
                    if (listener != null) listener.onError(new HttpError(e));
                }
                
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void random(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<Quiz>listener ){

        String uri = SDKUtil.getServerUrl(false) + SDKUtil._QUIZ_URL  + "random";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));

       quiz(playbasis,uri,params,listener);
    }

    public static void recentDone(@NonNull Playbasis playbasis, @NonNull String playerId, Integer limit,
                                  final OnResult<List<Quiz>>listener ){

        String uri = SDKUtil.getServerUrl(false) + SDKUtil._QUIZ_URL  + "players/" + playerId + "/" + String.valueOf
                (limit==null ? 5 : limit);


        quizzes(playbasis,uri,null,listener);
    }

    public static void recentPending(@NonNull Playbasis playbasis, @NonNull String playerId, Integer limit,
                                  final OnResult<List<Quiz>>listener ){

        String uri = SDKUtil.getServerUrl(false) + SDKUtil._QUIZ_URL  + "players/" + playerId + "/pending/" + String
                .valueOf
                        (limit == null ? 5 : limit);

        quizzes(playbasis,uri,null,listener);
    }

    public static void rank(@NonNull Playbasis playbasis, @NonNull String quizId, Integer limit,
                              final OnResult<QuizRank>listener ){

        String uri = SDKUtil.getServerUrl(false) + SDKUtil._QUIZ_URL + quizId + "/rank/" + String.valueOf(limit==null
                ? 5 : limit);

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    QuizRank quizRank = JsonHelper.FromJsonObject(result.getJSONObject("result"), QuizRank.class);
                    if (listener != null) listener.onSuccess(quizRank);
                } catch (JSONException e) {
                    if (listener != null) listener.onError(new HttpError(e));
                }

            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }

    public static void qestions(@NonNull Playbasis playbasis, @NonNull String quizId, @NonNull String playerId,
                            final OnResult<List<QuizQuestion>>listener ){

        String uri = SDKUtil.getServerUrl(false) + SDKUtil._QUIZ_URL + quizId + "/question";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        
        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<QuizQuestion> quizQuestions = JsonHelper.FromJsonArray(result.getJSONArray("result"),
                            QuizQuestion.class);
                    if (listener != null) listener.onSuccess(quizQuestions);
                } catch (JSONException e) {
                    if (listener != null) listener.onError(new HttpError(e));
                }

            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }


    private static void answerQuestion(@NonNull Playbasis playbasis, Boolean isAsync,
                                  @NonNull String quizId, @NonNull String playerId, @NonNull String questionId,
                                  @NonNull String optionId, final OnResult<Map<String, Object>>listener ){
        String uri = SDKUtil.getServerUrl(isAsync) + SDKUtil._QUIZ_URL + quizId + "/answer";
        
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));
        params.add(new BasicNameValuePair("question_id", questionId));
        params.add(new BasicNameValuePair("option_id", optionId));

        JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            @SuppressWarnings("unchecked")
            public void onSuccess(JSONObject result) {
                try {
                    HashMap<String,Object> events = JsonHelper.FromJsonObject(result.getJSONObject("result"), HashMap.class);
                    if (listener != null) listener.onSuccess(events);
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (listener != null) listener.onError(new HttpError(e));
                }
            }

            @Override
            public void onError(HttpError error) {
                if (listener != null) listener.onError(error);
            }
        });
    }
}
