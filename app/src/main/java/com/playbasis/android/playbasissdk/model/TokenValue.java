package com.playbasis.android.playbasissdk.model;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class TokenValue {
    public static final String TAG = "tokenValue";
    
    private String token;
    private Integer value;

    /**
     * Token getter.
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * Token setter.
     * @param token token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *  Value getter.
     * @return value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Value setter
     * @param value value
     */
    public void setValue(Integer value) {
        this.value = value;
    }
}
