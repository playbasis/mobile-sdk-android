package com.playbasis.android.playbasissdk.parser;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.playbasis.android.playbasissdk.api.ApiConst;
import com.playbasis.android.playbasissdk.model.BadgeData;
import com.playbasis.android.playbasissdk.model.Reward;

import java.io.IOException;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class RewardTypeArrayAdapter extends TypeAdapter<Reward> {
    public static final String TAG = "RewardTypeArrayAdapter";

    @Override
    public void write(JsonWriter out, Reward value) throws IOException {

    }

    @Override
    public Reward read(JsonReader in) throws IOException {
        final Reward rewardInstance = new Reward();

        in.beginObject();
        while (in.hasNext()) {
            String jsonTag = in.nextName();
            switch (jsonTag){
                case "value":
                case "reward_value":
                    rewardInstance.setRewardValue(in.nextString());
                    break;
                case "event_type":
                case "reward_type":
                    rewardInstance.setRewardType(in.nextString());
                    break;
                case "reward_id":
                    rewardInstance.setRewardId(in.nextString());
                    break;
                case "reward_data":
                    in.beginObject();
                    BadgeData rewardData = new BadgeData();
                    while (in.hasNext()){
                        switch (in.nextName()){
                            case "badge_id":
                                rewardData.setBadgeId(in.nextString());
                                break;
                            case "badgeId":
                                rewardData.setBadgeId(in.nextString());
                                break;
                            case "image":
                                rewardData.setImage(in.nextString());
                                break;
                            case ApiConst.NAME:
                                rewardData.setName(in.nextString());
                                break;
                            case "description":
                                rewardData.setDescription(in.nextString());
                                break;
                            case "hint":
                                rewardData.setHint(in.nextString());
                                break;
                            case "sponsor":
                                rewardData.setSponsor(in.nextBoolean());
                                break;
                            case ApiConst.CLAIM:
                                rewardData.setClaim(in.nextBoolean());
                                break;
                            case ApiConst.REDEEM:
                                rewardData.setRedeem(in.nextBoolean());
                                break;
                            
                        }
                    }
                    rewardInstance.setRewardData(rewardData);
                    in.endObject();
                    break;
            }
        }
        in.endObject();

        return rewardInstance;
    }
}
