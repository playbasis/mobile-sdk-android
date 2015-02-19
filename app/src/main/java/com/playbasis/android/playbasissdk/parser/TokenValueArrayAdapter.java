package com.playbasis.android.playbasissdk.parser;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.playbasis.android.playbasissdk.model.Reward;
import com.playbasis.android.playbasissdk.model.TokenValue;

import java.io.IOException;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class TokenValueArrayAdapter extends TypeAdapter<TokenValue> {
    public static final String TAG = "TokenValueArrayAdapter";

    @Override
    public void write(JsonWriter out, TokenValue value) throws IOException {
        
    }

    @Override
    public TokenValue read(JsonReader in) throws IOException {
        final TokenValue tokenValueInstance = new TokenValue();
        in.beginObject();
        while (in.hasNext()) {
            String jsonTag = in.nextName();
            tokenValueInstance.setToken(jsonTag);
            tokenValueInstance.setValue(in.nextInt());
        }
        in.endObject();
        
        return tokenValueInstance;
    }
}
