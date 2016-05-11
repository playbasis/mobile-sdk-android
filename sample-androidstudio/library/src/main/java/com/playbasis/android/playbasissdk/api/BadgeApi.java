package com.playbasis.android.playbasissdk.api;

import android.support.annotation.NonNull;

import com.playbasis.android.playbasissdk.core.Playbasis;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.JsonHelper;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Badge;
import com.playbasis.android.playbasissdk.model.Level;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by gregoire barret on 2/18/15.
 * For PlayBasisSdk project.
 */
public class BadgeApi extends Api {
    public static final String TAG = "BadgeApi";
    public static final String BADGES = "badges";

    /**
     * Returns information about all available badges for the current site.
     * @param playbasis Playbasis object.
     * @param listener Callback interface.
     */
    public static void badges(@NonNull Playbasis playbasis, final OnResult<List<Badge>> listener){
        String uri = playbasis.getUrl() + SDKUtil.BADGES_URL;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    List<Badge> badges = JsonHelper.FromJsonArray(result.getJSONArray(BADGES), Badge.class);
                    if (listener != null) listener.onSuccess(badges);
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
     * Returns information about the badge with the specified id.
     * @param playbasis Playbasis object.
     * @param badgeId Badge id to query.
     * @param listener Callback interface.
     */
    public static void badge(@NonNull Playbasis playbasis, @NonNull String badgeId,
                             final OnResult<Badge> listener) {
        String uri = playbasis.getUrl() + SDKUtil._BADGE_URL + badgeId;

        JsonObjectGET(playbasis, uri, null, new OnResult<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    Badge badge = JsonHelper.FromJsonObject(result.getJSONObject(ApiConst.BADGE), Badge.class);
                    if (listener != null) listener.onSuccess(badge);
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
