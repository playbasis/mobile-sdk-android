package com.playbasis.android.playbasissdk.parser;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.playbasis.android.playbasissdk.api.ApiConst;
import com.playbasis.android.playbasissdk.model.RedeemEvent;
import com.playbasis.android.playbasissdk.model.TokenValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/24/15.
 * For PlayBasisSdk project.
 */
public class RendeemEventArrayAdapter extends TypeAdapter<RedeemEvent> {
    public static final String TAG = "RendeemEventArrayAdapter";

    @Override
    public void write(JsonWriter out, RedeemEvent value) throws IOException {
        
    }

    @Override
    public RedeemEvent read(JsonReader in) throws IOException {
        final RedeemEvent eventInstance = new RedeemEvent();
        in.beginObject();
        while (in.hasNext()) {
            String jsonTag = in.nextName();
            switch (jsonTag){
                case ApiConst.MESSAGE:
                    eventInstance.setMessage(in.nextString());
                    break;
                case "event_type":
                    eventInstance.setEventType(in.nextString());
                    break;
                case "incomplete":
                    try {
                        in.beginArray();
                        List<TokenValue> tokenValues = new ArrayList<>();
                        while (in.hasNext()){
                            in.beginObject();
                            TokenValue tokenValue = new TokenValue();
                            if (in.nextName().equals("token")) {
                                tokenValue.setToken(in.nextString());
                            }else if(in.nextName().equals("value")){
                                tokenValue.setValue(in.nextInt());
                            }
                            tokenValues.add(tokenValue);
                            in.endObject();
                        }
                        eventInstance.setIncomplete(tokenValues);
                        in.endArray();
                    }catch (Exception e){
                        try {
                            eventInstance.setIncompleteValue(in.nextInt());
                        }catch (Exception e1){
                            
                        }
                    }
                    break;
            }
        }
        in.endObject();

        return eventInstance;
    }
}
