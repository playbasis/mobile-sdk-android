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


    @Expose
    private List<Point> point = new ArrayList<Point>();
    @Expose
    private List<Exp> exp = new ArrayList<Exp>();

    /**
     *
     * @return
     * The point
     */
    public List<Point> getPoint() {
        return point;
    }

    /**
     *
     * @param point
     * The point
     */
    public void setPoint(List<Point> point) {
        this.point = point;
    }

    public Rank withPoint(List<Point> point) {
        this.point = point;
        return this;
    }

    /**
     *
     * @return
     * The exp
     */
    public List<Exp> getExp() {
        return exp;
    }

    /**
     *
     * @param exp
     * The exp
     */
    public void setExp(List<Exp> exp) {
        this.exp = exp;
    }

    public Rank withExp(List<Exp> exp) {
        this.exp = exp;
        return this;
    }


    @Override
    public String toString() {
        return "Rank{" +
                "point=" + point +
                ", exp=" + exp +
                '}';
    }

    private  class Exp {
        public static final String TAG = "Exp";

        @SerializedName("pb_player_id")
        @Expose
        private PbPlayerId pbPlayerId;
        @SerializedName("player_id")
        @Expose
        private String playerId;
        @Expose
        private Integer exp;

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
         * @return The exp
         */
        public Integer getExp() {
            return exp;
        }

        /**
         * @param exp The exp
         */
        public void setExp(Integer exp) {
            this.exp = exp;
        }

        @Override
        public String toString() {
            return "Exp{" +
                    "pbPlayerId=" + pbPlayerId +
                    ", playerId='" + playerId + '\'' +
                    ", exp=" + exp +
                    '}';
        }
    }
    
    private class Point {
        public static final String TAG = "Point";


        @SerializedName("pb_player_id")
        @Expose
        private PbPlayerId pbPlayerId;
        @SerializedName("player_id")
        @Expose
        private String playerId;
        @Expose
        private Integer point;

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
         * @return The point
         */
        public Integer getPoint() {
            return point;
        }

        /**
         * @param point The point
         */
        public void setPoint(Integer point) {
            this.point = point;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "pbPlayerId=" + pbPlayerId +
                    ", playerId='" + playerId + '\'' +
                    ", point=" + point +
                    '}';
        }
    }
    
    private class PbPlayerId {
        public static final String TAG = "PbPlayerId";

        @Expose
        private String $id;

        /**
         *
         * @return
         * The $id
         */
        public String get$id() {
            return $id;
        }

        /**
         *
         * @param $id
         * The $id
         */
        public void set$id(String $id) {
            this.$id = $id;
        }

        @Override
        public String toString() {
            return "PbPlayerId{" +
                    "$id='" + $id + '\'' +
                    '}';
        }
    }

    
    
}
