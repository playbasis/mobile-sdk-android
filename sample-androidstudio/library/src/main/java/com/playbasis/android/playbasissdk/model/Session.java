
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Session {


    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("date_expire")
    @Expose
    private String dateExpire;

    /**
     *
     * @return
     * The sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     *
     * @param sessionId
     * The reward_id
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     *
     * @return
     * The dateExpire
     */
    public String getDateExpire() {
        return dateExpire;
    }

    /**
     *
     * @param dateExpire
     * The reward_name
     */
    public void setDateExpire(String dateExpire) {
        this.dateExpire = dateExpire;
    }


    @Override
    public String toString() {
        return "Session{" +
                "session_id='" + sessionId + '\'' +
                ", date_expire='" + dateExpire +
                '}';
    }
}
