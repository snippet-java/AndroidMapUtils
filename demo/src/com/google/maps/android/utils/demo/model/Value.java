
package com.google.maps.android.utils.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("rev")
    @Expose
    private String rev;

    /**
     * 
     * @return
     *     The rev
     */
    public String getRev() {
        return rev;
    }

    /**
     * 
     * @param rev
     *     The rev
     */
    public void setRev(String rev) {
        this.rev = rev;
    }

}
