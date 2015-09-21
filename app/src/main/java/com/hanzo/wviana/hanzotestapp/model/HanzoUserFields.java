
package com.hanzo.wviana.hanzotestapp.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HanzoUserFields {

    @SerializedName("version")
    @Expose
    private int version;
    @SerializedName("showProfileImage")
    @Expose
    private boolean showProfileImage;
    @SerializedName("facebook")
    @Expose
    private boolean facebook;
    @SerializedName("twitter")
    @Expose
    private boolean twitter;
    @SerializedName("fields")
    @Expose
    private List<Field> fields = new ArrayList<Field>();

    /**
     * 
     * @return
     *     The version
     */
    public int getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The showProfileImage
     */
    public boolean isShowProfileImage() {
        return showProfileImage;
    }

    /**
     * 
     * @param showProfileImage
     *     The showProfileImage
     */
    public void setShowProfileImage(boolean showProfileImage) {
        this.showProfileImage = showProfileImage;
    }

    /**
     * 
     * @return
     *     The facebook
     */
    public boolean isFacebook() {
        return facebook;
    }

    /**
     * 
     * @param facebook
     *     The facebook
     */
    public void setFacebook(boolean facebook) {
        this.facebook = facebook;
    }

    /**
     * 
     * @return
     *     The twitter
     */
    public boolean isTwitter() {
        return twitter;
    }

    /**
     * 
     * @param twitter
     *     The twitter
     */
    public void setTwitter(boolean twitter) {
        this.twitter = twitter;
    }

    /**
     * 
     * @return
     *     The fields
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * 
     * @param fields
     *     The fields
     */
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

}
