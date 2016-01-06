package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TorIsHere on 1/6/2016 AD.
 */
public class Node {

    @SerializedName("node_id")
    @Expose
    private String nodeId;

    @Expose
    private String name;

    public Node() {
        this.nodeId = "";
        this.name = "";
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
