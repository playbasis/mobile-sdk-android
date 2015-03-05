package com.playbasis.android.playbasissdk.parser;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.playbasis.android.playbasissdk.model.PbPlayerId;
import com.playbasis.android.playbasissdk.model.Point;
import com.playbasis.android.playbasissdk.model.Rank;

import java.io.IOException;

/**
 * Created by gregoire barret on 3/5/15.
 * For PlayBasisSdk project.
 */
public class PointTypeArrayAdapter extends TypeAdapter<Point> {
    public static final String TAG = "PointTypeArrayAdapter";

    @Override
    public void write(JsonWriter out, Point value) throws IOException {

    }

    @Override
    public Point read(JsonReader in) throws IOException {
        final Point pointInstance = new Point();
        in.beginObject();
        while (in.hasNext()) {
            String jsonTag = in.nextName();
            switch (jsonTag){
                case "reward_id":
                    pointInstance.setRewardId(in.nextString());
                    break;
                case "reward_name":
                    pointInstance.setRewardName(in.nextString());
                    break;
                case "point_value":
                case "value":
                    pointInstance.setValue(in.nextInt());
                    break;
            }
        }
        in.endObject();

        return pointInstance;
    }
}
