package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;

/**
 * Created by TorIsHere on 2/19/2016 AD.
 */
public class NumMissions {
    @Expose
    int total;

    @Expose
    int join;

    @Expose
    int unjoin;

    @Expose
    int finish;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getJoin() {
        return join;
    }

    public void setJoin(int join) {
        this.join = join;
    }

    public int getUnjoin() {
        return unjoin;
    }

    public void setUnjoin(int unjoin) {
        this.unjoin = unjoin;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }
}
