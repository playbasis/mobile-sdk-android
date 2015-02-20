package com.playbasis.android.playbasissdk.parser;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.playbasis.android.playbasissdk.model.ActionConfig;
import com.playbasis.android.playbasissdk.model.Config;
import com.playbasis.android.playbasissdk.model.PbPlayerId;
import com.playbasis.android.playbasissdk.model.Rank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/20/15.
 * For PlayBasisSdk project.
 */
public class ActionConfigArrayAdapter extends TypeAdapter<ActionConfig> {
    public static final String TAG = "ActionConfigArrayAdapter";

    @Override
    public void write(JsonWriter out, ActionConfig value) throws IOException {

    }

    @Override
    public ActionConfig read(JsonReader in) throws IOException {
        final ActionConfig actionConfigInstance = new ActionConfig();
        in.beginObject();
        while (in.hasNext()) {
            String jsonTag = in.nextName();
            actionConfigInstance.setKey(jsonTag);
            in.beginObject();
            while (in.hasNext()) {
                switch (in.nextName()) {
                    case "name":
                        actionConfigInstance.setName(in.nextString());
                        break;
                    case "config":
                        in.beginArray();
                        List<Config> configArray = new ArrayList<>();
                        while (in.hasNext()) {
                            in.beginObject();
                            Config config = new Config();
                         //   while (in.hasNext()) {

                                if (in.nextName().equals("url")) {
                                    config.setUrl(in.nextString());
                                }
                       //     }
                            configArray.add(config);
                            in.endObject();
                        }
                        actionConfigInstance.setConfig(configArray);
                        in.endArray();
                        break;
                }
            }
            in.endObject();
        }
        in.endObject();


        return actionConfigInstance;
    }
}
