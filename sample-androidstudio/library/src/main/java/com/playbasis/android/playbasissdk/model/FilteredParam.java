
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilteredParam {

    @Expose
    private Url url;

    /**
     * 
     * @return
     *     The Url
     */
    public Url getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The Url
     */
    public void setUrl(Url url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FilteredParam{" +
                "url='" + url + '\'' +
                '}';
    }
}
