package com.playbasis.android.playbasissdk.model;

/**
 * Created by gregoire barret on 2/26/15.
 * For PlayBasisSdk project.
 */
public enum UIEvent {
    /**
     * This is called when the user either touches the item (when in touch mode),  or focuses upon the item with the 
     *  navigation-keys or trackball and presses the suitable "enter" key or presses down on the trackball.
     */
    CLICK("onclick"),
    /**
     *  This is called when the user either touches and holds the item (when in touch mode),  
     *   or focuses upon the item with the navigation-keys or trackball and presses and holds the suitable "enter" 
     *   key  or presses and holds down on the trackball (for one second).
     */
    LONG_CLICK("onlongclick"),
    /**
     * This is called when the user navigates onto or away from the item, using the navigation-keys or trackball.
     */
    FOCUS_CHANGE("onfocuschange"),
    /**
     *  This is called when the user performs an action qualified as a touch event, including a press, a release,  
     *  or any movement gesture on the screen (within the bounds of the item)
     */
    TOUCH("ontouch"),
    /**
     *  This is called when the user is presses or releases a hardware key on the device.
     */
    KEY("onkey"),
    /**
     * This is called when a Context Menu is being built (as the result of a sustained "long click")
     */
    MENU_ITEM("onmenuitem"),
    /**
     *  This event is called when  a new view is created.
     */
    VIEW_CREATE("onviewcreate"),
    /**
     *  This event is called when a view appear on the screen
     */
    VIEW_DISPLAY("onviewdisplay"),
    /**
     * This event is called when the view disapear form the screen.
     */
    VIEW_REMOVE("onviewremove"),
    /**
     * This event is called when  a view is destroy.
     */
    VIEW_DESTROY("onviewdestroy")
    ;


    private final String action;

    /**
     * @param action set action
     */
    private UIEvent(final String action) {
        this.action = action;
    }


    @Override
    public String toString() {
        return action;
    }
}
