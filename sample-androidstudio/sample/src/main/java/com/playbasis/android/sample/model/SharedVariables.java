package com.playbasis.android.sample.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by TorIsHere on 12/15/2015 AD.
 */
public class SharedVariables {
    private static SharedVariables ourInstance = new SharedVariables();

    final private String sharedPreference = "MyPreferences";
    private String userType;
    private User user;

    public static SharedVariables getInstance() {
        return ourInstance;
    }

    private SharedVariables() {
        userType = "";
        user = new User();
    }

    public String getSharedPreference() {
        return this.sharedPreference;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return this.userType;
    }

    private void saveUserToPreference(Context context, User user) {
        SharedPreferences preferences = context.getSharedPreferences(SharedVariables.getInstance().getSharedPreference(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("user", json);

        editor.commit();

    }

    private User fetchUserFromPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SharedVariables.getInstance().getSharedPreference(), Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = preferences.getString("user", null);
        if (json == null) {
            return null;
        }
        User savedUser = gson.fromJson(json, User.class);
        user = savedUser;
        return user;
    }
    public User getUser(Context context) {
        if (user == null ||  user.getNodeId() == null || user.getNodeId().length() <= 0) {
            return fetchUserFromPreference(context);
        }

        return user;
    }

    public void setUser(Context context, User user) {
        this.user = user;
        saveUserToPreference(context, user);
    }
}
