package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.api.ApiConst;

import java.util.ArrayList;

/**
 * Created by Nick-Playbasis on 1/11/2016.
 */
public class RankPeer {

    ArrayList<Leaderboard> leaderboards;

    @SerializedName(ApiConst.PLAYER_ID)
    @Expose
    String myRankPlayerId;

    int rank;

    @SerializedName("ranked_by")
    @Expose
    String rankBy;

    int rankedValue;
    public RankPeer(){
        leaderboards = new ArrayList<>();
        myRankPlayerId = "";
        rank = 1;
        rankBy = "";
        rankedValue = 0;
    }
    public ArrayList<Leaderboard> getLeaderboards() {
        return leaderboards;
    }

    public void setLeaderboards(ArrayList<Leaderboard> leaderboard) {
        this.leaderboards = leaderboard;
    }
    public String getMyRankPlayerId() {
        return myRankPlayerId;
    }

    public void setMyRankPlayerId(String playerID) {
        this.myRankPlayerId = playerID;
    }
    public int getRank() {
        return rank;
    }

    public void setRank(int rankNo) {
        this.rank = rankNo;
    }
    public String getRankBy() {
        return rankBy;
    }

    public void setRankBy(String rankBy) {
        this.rankBy = rankBy;
    }
    public int getRankedValue() {
        return rankedValue;
    }

    public void setRankedValue(int value) {
        this.rankedValue = value;
    }
}
