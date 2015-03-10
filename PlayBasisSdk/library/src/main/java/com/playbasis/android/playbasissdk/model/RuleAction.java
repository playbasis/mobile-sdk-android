package com.playbasis.android.playbasissdk.model;

/**
 * Created by gregoire barret on 2/24/15.
 * For PlayBasisSdk project.
 */
public enum RuleAction {
    /**
     * Buy action.
     */
    BUY("buy"),
    /**
     * Checkin action.
     */
    CHECKIN("checkin"),
    /**
     * Click action.
     */
    CLICK("click"),
    /**
     * Comment action.
     */
    COMMENT("comment"),
    /**
     * Compare action.
     */
    COMPARE("compare"),
    /**
     * Facebook comment action.
     */
    FBCOMMENT("fbcomment"),
    /**
     * Facebook like action.
     */
    FBLIKE("fblike"),
    /**
     * Facebook post action.
     */
    FBPOST("fbpost"),
    /**
     * Facebook status action.
     */
    FBSTATUS("fbstatus"),
    /**
     * Like action.
     */
    LIKE("like"),
    /**
     * Login action.
     */
    LOGIN("login"),
    /**
     * Logout action.
     */
    LOGOUT("logout"),
    /**
     * Love action.
     */
    LOVE("love"),
    /**
     * Order action.
     */
    ORDER("order"),
    /**
     * Payment action.
     */
    PAYMENT("payment"),
    /**
     * Read action.
     */
    READ("read"),
    /**
     * Redeem action.
     */
    REDEEM("redeem"),
    /**
     * Register action.
     */
    REGISTER("register"),
    /**
     * Review action.
     */
    REVIEW("review"),
    /**
     * Share action.
     */
    SHARE("share"),
    /**
     * Tweet action.
     */
    TWEET("tweet"),
    /**
     * Visit action.
     */
    VISIT("visit"),
    /**
     * Want action.
     */
    WANT("want"),
    /**
     * Watch action.
     */
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
