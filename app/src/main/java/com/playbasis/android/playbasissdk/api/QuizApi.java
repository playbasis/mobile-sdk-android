package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Quiz;
import com.playbasis.android.playbasissdk.model.QuizDetail;
import com.playbasis.android.playbasissdk.model.QuizPending;
import com.playbasis.android.playbasissdk.model.QuizQuestion;
import com.playbasis.android.playbasissdk.model.QuizQuestionAnswer;
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
public class QuizApi extends Api {
    public static final String TAG = "QuizApi";

    private static void quizzes(@NonNull Playbasis playbasis, @NonNull String uri, List<NameValuePair> params,
                                final OnResult<List<Quiz>> listener) {

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
                            final OnResult<Quiz> listener) {

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Quiz quiz = new Quiz();
                    if (!result.isNull("result")) {
                        quiz = JsonHelper.FromJsonObject(result.getJSONObject("result"), Quiz.class);
                    }
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


    /**
     * Returns a list of active quizzes.
     *
     * @param playbasis Playbasis object.
     * @param playerId  Player id as used in client's website.
     * @param listener  Callback interface.
     */
    public static void activeList(@NonNull Playbasis playbasis, String playerId,
                                  final OnResult<List<Quiz>> listener) {

        String uri = SDKUtil.SERVER_URL + SDKUtil._QUIZ_URL + "list";

        List<NameValuePair> params = new ArrayList<>();
        if (playerId != null) params.add(new BasicNameValuePair("player_id", playerId));

        quizzes(playbasis, uri, params, listener);
    }

    /**
     * Get detail of a quiz.
     *
     * @param playbasis Playbasis object.
     * @param quizId    Quiz id.
     * @param playerId  Player id as used in client's website.
     * @param listener  Callback interface.
     */
    public static void detail(@NonNull Playbasis playbasis, @NonNull String quizId, String playerId,
                              final OnResult<QuizDetail> listener) {

        String uri = SDKUtil.SERVER_URL + SDKUtil._QUIZ_URL + quizId + "/detail";

        List<NameValuePair> params = new ArrayList<>();
        if (playerId != null) params.add(new BasicNameValuePair("player_id", playerId));


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

    /**
     * Randomly get one quiz of a list of active quizzes for a given player.
     *
     * @param playbasis Playbasis object.
     * @param playerId  Player id as used in client's website.
     * @param listener  Callback interface.
     */
    public static void random(@NonNull Playbasis playbasis, @NonNull String playerId,
                              final OnResult<Quiz> listener) {

        String uri = SDKUtil.SERVER_URL + SDKUtil._QUIZ_URL + "random";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));

        quiz(playbasis, uri, params, listener);
    }

    /**
     * List recent quizzes done by the player.
     *
     * @param playbasis Playbasis object.
     * @param playerId  Player id as used in client's website.
     * @param limit     Limit number of quizzes.
     * @param listener  Callback interface.
     */
    public static void recentDone(@NonNull Playbasis playbasis, @NonNull String playerId, Integer limit,
                                  final OnResult<List<Quiz>> listener) {

        String uri = SDKUtil.SERVER_URL + SDKUtil._QUIZ_URL + "player/" + playerId + "/" + String.valueOf
                (limit == null ? 5 : limit);


        quizzes(playbasis, uri, null, listener);
    }

    /**
     * List pending quizzes by the player.
     *
     * @param playbasis Playbasis object.
     * @param playerId  Player id as used in client's website.
     * @param limit     Limit number of quizzes.
     * @param listener  Callback interface.
     */
    public static void recentPending(@NonNull Playbasis playbasis, @NonNull String playerId, Integer limit,
                                     final OnResult<List<QuizPending>> listener) {

        String uri = SDKUtil.SERVER_URL + SDKUtil._QUIZ_URL + "player/" + playerId + "/pending/" + String
                .valueOf(limit == null ? 5 : limit);

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<QuizPending> quizzes = JsonHelper.FromJsonArray(result.getJSONArray("result"),
                            QuizPending.class);
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

    /**
     * Rank players by their scores for a give quiz.
     *
     * @param playbasis Playbasis object.
     * @param quizId    Quiz id.
     * @param limit     Limit number of quizzes.
     * @param listener  Callback interface.
     */
    public static void rank(@NonNull Playbasis playbasis, @NonNull String quizId, Integer limit,
                            final OnResult<List<QuizRank>> listener) {

        String uri = SDKUtil.SERVER_URL + SDKUtil._QUIZ_URL + quizId + "/rank/" + String.valueOf(limit == null
                ? 5 : limit);

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<QuizRank> quizRanks = JsonHelper.FromJsonArray(result.getJSONArray("result"), QuizRank.class);
                    if (listener != null) listener.onSuccess(quizRanks);
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

    /**
     * Get a question with a list of options for a given quiz.
     *
     * @param playbasis Playbasis object.
     * @param quizId    Quiz id.
     * @param playerId  Player id as used in client's website.
     * @param listener  Callback interface.
     */
    public static void questions(@NonNull Playbasis playbasis, @NonNull String quizId, @NonNull String playerId,
                                 final OnResult<QuizQuestion> listener) {

        String uri = SDKUtil.SERVER_URL + SDKUtil._QUIZ_URL + quizId + "/question";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("player_id", playerId));

        JsonObjectGET(playbasis, uri, params, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    QuizQuestion quizQuestion = new QuizQuestion();
                    if (!result.isNull("result")) {
                        quizQuestion = JsonHelper.FromJsonObject(result.getJSONObject("result"),
                                QuizQuestion.class);
                    }
                    if (listener != null) listener.onSuccess(quizQuestion);
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


    /**
     * Submit a player's answer for a question for a given quiz.
     *
     * @param playbasis  Playbasis object.
     * @param isAsync    make the request async
     * @param quizId     quiz id
     * @param playerId   player id as used in client's website
     * @param questionId question id
     * @param optionId   option id
     * @param listener   Callback interface.
     */
    public static void answerQuestion(@NonNull Playbasis playbasis, boolean isAsync,
                                      @NonNull String quizId, @NonNull String playerId, @NonNull String questionId,
                                      @NonNull String optionId, final OnResult<QuizQuestionAnswer> listener) {

        String endpoint = SDKUtil._QUIZ_URL + quizId + "/answer";
        if (isAsync) {

            JSONObject jsonObject = null;
            try {
                jsonObject = JsonHelper.newJsonWithToken(playbasis.getAuthenticator());
                jsonObject.put("player_id", playerId);
                jsonObject.put("question_id", questionId);
                jsonObject.put("option_id", optionId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            asyncPost(playbasis, endpoint, jsonObject, new OnResult<String>() {
                @Override
                public void onSuccess(String result) {
                    if (listener != null) listener.onSuccess(null);
                }

                @Override
                public void onError(HttpError error) {
                    if (listener != null) listener.onError(error);
                }
            });


        } else {

            String uri = SDKUtil.SERVER_URL + endpoint;

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("player_id", playerId));
            params.add(new BasicNameValuePair("question_id", questionId));
            params.add(new BasicNameValuePair("option_id", optionId));

            JsonObjectPOST(playbasis, uri, params, new OnResult<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        QuizQuestionAnswer quizQuestionAnswer = JsonHelper.FromJsonObject(result.getJSONObject("result"), QuizQuestionAnswer.class);
                        if (listener != null) listener.onSuccess(quizQuestionAnswer);
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


    }
}
