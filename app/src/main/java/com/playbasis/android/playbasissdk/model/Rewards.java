package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class Rewards {
    public static final String TAG = "Rewards";
    
    private Exp exp;
    
    private Point point;


    /**
     *  
     * @return Exp rewards
     */
    public Exp getExp() {
        return exp;
    }

    /**
     *  
     * @param exp Exp rewards
     */
    public void setExp(Exp exp) {
        this.exp = exp;
    }

    /**
     *  
     * @return Point rewards
     */
    public Point getPoint() {
        return point;
    }

    /**
     *
     * @param point Point rewards
     */
    public void setPoint(Point point) {
        this.point = point;
    }

    private class Exp{
        @SerializedName("exp_value")
        @Expose
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private class Point{
        @SerializedName("point_value")
        @Expose
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    @Override
    public String toString() {
        return "Rewards{" +
                "exp=" + exp +
                ", point=" + point +
                '}';
    }
}
