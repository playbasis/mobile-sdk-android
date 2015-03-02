package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/18/15.
 * For PlayBasisSdk project.
 */
public class Rank {
    public static final String TAG = "Rank";


        @SerializedName("pb_player_id")
        @Expose
        private PbPlayerId pbPlayerId;
        @SerializedName("player_id")
        @Expose
        private String playerId;
        @Expose
        private Integer value;

        /**
         * @return The pbPlayerId
         */
        public PbPlayerId getPbPlayerId() {
            return pbPlayerId;
        }

        /**
         * @param pbPlayerId The pb_player_id
         */
        public void setPbPlayerId(PbPlayerId pbPlayerId) {
            this.pbPlayerId = pbPlayerId;
        }

        /**
         * @return The playerId
         */
        public String getPlayerId() {
            return playerId;
        }

        /**
         * @param playerId The player_id
         */
        public void setPlayerId(String playerId) {
            this.playerId = playerId;
        }


        /**
         * @return The value
         */
        public Integer getValue() {
            return value;
        }

        /**
         * @param value The value
         */
        public void setValue(Integer value) {
            this.value = value;
        }

    @Override
    public String toString() {
        return "Rank{" +
                "pbPlayerId=" + pbPlayerId +
                ", playerId='" + playerId + '\'' +
                ", value=" + value +
                '}';
    }
}
