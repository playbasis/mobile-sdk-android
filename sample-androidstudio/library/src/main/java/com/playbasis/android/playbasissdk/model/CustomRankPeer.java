package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nick-Playbasis on 1/8/2016.
 */
public class CustomRankPeer {

    ArrayList<CustomLeaderboard> leaderboards;

    @SerializedName("player_id")
    @Expose
    String myRankPlayerId;

    @SerializedName("node_name")
    @Expose
    String nodeName;

    int rank;

    @SerializedName("ranked_by")
    @Expose
    String rankBy;

    int rankedValue;    

    public CustomRankPeer(){
        leaderboards = new ArrayList<>();
        myRankPlayerId = "";
        rank = 1;
        rankBy = "";
        rankedValue = 0;
    }
    public ArrayList<CustomLeaderboard> getLeaderboards() {
        return leaderboards;
    }

    public void setLeaderboards(ArrayList<CustomLeaderboard> leaderboard) {
        this.leaderboards = leaderboard;
    }
    public String getMyRankPlayerId() {
        return myRankPlayerId;
    }

    public void setMyRankPlayerId(String playerID) {
        this.myRankPlayerId = playerID;
    }
    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
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
