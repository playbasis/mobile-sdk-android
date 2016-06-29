
package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url {

    @Expose
    private String operation;
    @SerializedName("completion_string")
    @Expose
    private String completionString;

    /**
     * 
     * @return
     *     The operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * 
     * @param operation
     *     The operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * [getCompletionString description]
     * @return completion string
     */
    public String getCompletionString() {
        return this.completionString;
    }

    /**
     * [setCompletionString description]
     * @param completionString completion string
     */
    public void setCompletionString(String completionString) {
        this.completionString = completionString;
    }

    @Override
    public String toString() {
        return "Url{" +
                "operation='" + operation + '\'' +
                ", completionString='" + completionString + '\'' +
                '}';
    }
}
