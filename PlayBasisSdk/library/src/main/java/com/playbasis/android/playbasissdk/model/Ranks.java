package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gregoire barret on 2/19/15.
 * For PlayBasisSdk project.
 */
public class Ranks {
    public static final String TAG = "Ranks";



    @Expose
    private List<Rank> point = new ArrayList<Rank>();
    @Expose
    private List<Rank> exp = new ArrayList<Rank>();

    /**
     *
     * @return
     * The point
     */
    public List<Rank> getPoint() {
        return point;
    }

    /**
     *
     * @param point
     * The point
     */
    public void setPoint(List<Rank> point) {
        this.point = point;
    }

    public Ranks withPoint(List<Rank> point) {
        this.point = point;
        return this;
    }

    /**
     *
     * @return
     * The exp
     */
    public List<Rank> getExp() {
        return exp;
    }

    /**
     *
     * @param exp
     * The exp
     */
    public void setExp(List<Rank> exp) {
        this.exp = exp;
    }

    public Ranks withExp(List<Rank> exp) {
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
}
