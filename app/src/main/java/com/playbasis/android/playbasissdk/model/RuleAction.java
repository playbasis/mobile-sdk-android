package com.playbasis.android.playbasissdk.model;

/**
 * Created by gregoire barret on 2/24/15.
 * For PlayBasisSdk project.
 */
public enum RuleAction {
    BUY("buy"),
    CHECKIN("checkin"),
    CLICK("click"),
    COMMENT("comment"),
    COMPARE("compare"),
    FBCOMMENT("fbcomment"),
    FBLIKE("fblike"),
    FBPOST("fbpost"),
    FBSTATUS("fbstatus"),
    LIKE("like"),
    LOGIN("login"),
    LOGOUT("logout"),
    LOVE("love"),
    ORDER("order"),
    PAYMENT("payment"),
    READ("read"),
    REDEEM("redeem"),
    REGISTER("register"),
    REVIEW("review"),
    SHARE("share"),
    TWEET("tweet"),
    VISIT("visit"),
    WANT("want"),
    WATCH("watch")
    ;

    private final String action;

    /**
     * @param action set action
     */
    private RuleAction(final String action) {
        this.action = action;
    }


    @Override
    public String toString() {
        return action;
    }
}
