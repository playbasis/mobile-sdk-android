package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.playbasis.android.playbasissdk.api.ApiConst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class ActionConfig {
    public static final String TAG = "ActionConfig";

    @Expose
    private String name;
    @Expose
    private String key;
    @Expose
    private List<Config> config = new ArrayList<Config>();

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The config
     */
    public List<Config> getConfig() {
        return config;
    }

    /**
     *
     * @param config
     * The config
     */
    public void setConfig(List<Config> config) {
        this.config = config;
    }

    /**
     *  Get config Id.
     * @return config Id
     */
    public String getKey() {
        return key;
    }

    /**
     *  Config Id.
     * @param key Config Id
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    
    public static List<ActionConfig> parseEngineRules(JSONObject json) throws JSONException {
        List<ActionConfig> actionConfigs = new ArrayList<>();
        
        Iterator<String> iterator = json.keys();
        while (iterator.hasNext()){
            String jsonKey = iterator.next();
            ActionConfig actionConfig = new ActionConfig();
            
            JSONObject jsonActionConfig = json.getJSONObject(jsonKey);
            JSONArray jsonConfig = jsonActionConfig.getJSONArray("config");
            actionConfig.setKey(jsonKey);
            actionConfig.setName(jsonActionConfig.getString(ApiConst.NAME));
            
            List<Config> configs = new ArrayList<>();
            for (int i = 0; i < jsonConfig.length(); i++) {
                Config config = new Config();
                config.setUrl(jsonConfig.getJSONObject(i).getString(ApiConst.URL));
                configs.add(config);
            }
            actionConfig.setConfig(configs);
            actionConfigs.add(actionConfig);
        }
        return actionConfigs;
    }
}
