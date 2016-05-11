package com.playbasis.android.sample.model;

/**
 * Created by TorIsHere on 1/8/2016 AD.
 */
public class User {

    private String userId;
    private String email;
    private String nodeId;
    private String fullName;
    private String userImage;
    private String nodeName;
    private String nodeDesc;
    private String parentNodeId;;
    private String sessionId;
    private String roleName;
    private int golds;
    private int points;

    public String getNodeDesc() {
        return nodeDesc;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }

    public User() {
        this.userId = "";
        this.email = "";
        this.nodeId = "";
        this.roleName = "";
        this.nodeName = "";
        this.nodeDesc = "";
        golds = 0;
        points = 0;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getGolds() {
        return golds;
    }

    public void setGolds(int golds) {
        this.golds = golds;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
