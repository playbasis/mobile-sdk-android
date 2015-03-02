package com.playbasis.android.playbasissdk.parser;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.playbasis.android.playbasissdk.model.PbPlayerId;
import com.playbasis.android.playbasissdk.model.Rank;
import com.playbasis.android.playbasissdk.model.Reward;
import com.playbasis.android.playbasissdk.model.RewardData;

import java.io.IOException;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class RankTypeArrayAdapter extends TypeAdapter<Rank> {
    public static final String TAG = "RankTypeArrayAdapter";

    @Override
    public void write(JsonWriter out, Rank value) throws IOException {
        
    }

    @Override
    public Rank read(JsonReader in) throws IOException {
        final Rank rankInstance = new Rank();
        in.beginObject();
        while (in.hasNext()) {
            String jsonTag = in.nextName();
            switch (jsonTag){
                case "player_id":
                    rankInstance.setPlayerId(in.nextString());
                    break;
                case "exp":
                case "point":
                    rankInstance.setValue(in.nextInt());
                    break;
                case "pb_player_id":
                    in.beginObject();
                    PbPlayerId pbPlayerId = new PbPlayerId();
                    while (in.hasNext()){
                        switch (in.nextName()){
                            case "$id":
                                pbPlayerId.set$id(in.nextString());
                                break;
                        }
                    }
                    rankInstance.setPbPlayerId(pbPlayerId);
                    in.endObject();
                    break;
            }
        }
        in.endObject();

        return rankInstance;
    }
}
