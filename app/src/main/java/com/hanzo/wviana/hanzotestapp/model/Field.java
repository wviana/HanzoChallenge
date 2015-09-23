
package com.hanzo.wviana.hanzotestapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hanzo.wviana.hanzotestapp.R;

public class Field {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("jsonName")
    @Expose
    private String jsonName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("validation")
    @Expose
    private String validation;
    @SerializedName("mandatory")
    @Expose
    private boolean mandatory;
    @SerializedName("isJsonData")
    @Expose
    private boolean isJsonData;
    @SerializedName("maxSize")
    @Expose
    private int maxSize;
    @SerializedName("minSize")
    @Expose
    private int minSize;
    @SerializedName("editProfile")
    @Expose
    private boolean editProfile;
    @SerializedName("mask")
    @Expose
    private String mask;
    @SerializedName("combo")
    @Expose
    private List<String> combo = new ArrayList<String>();
//    private View mView;

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The jsonName
     */
    public String getJsonName() {
        return jsonName;
    }

    /**
     * 
     * @param jsonName
     *     The jsonName
     */
    public void setJsonName(String jsonName) {
        this.jsonName = jsonName;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The validation
     */
    public String getValidation() {
        return validation;
    }

    /**
     * 
     * @param validation
     *     The validation
     */
    public void setValidation(String validation) {
        this.validation = validation;
    }

    /**
     * 
     * @return
     *     The mandatory
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * 
     * @param mandatory
     *     The mandatory
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * 
     * @return
     *     The isJsonData
     */
    public boolean isIsJsonData() {
        return isJsonData;
    }

    /**
     * 
     * @param isJsonData
     *     The isJsonData
     */
    public void setIsJsonData(boolean isJsonData) {
        this.isJsonData = isJsonData;
    }

    /**
     * 
     * @return
     *     The maxSize
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * 
     * @param maxSize
     *     The maxSize
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 
     * @return
     *     The minSize
     */
    public int getMinSize() {
        return minSize;
    }

    /**
     * 
     * @param minSize
     *     The minSize
     */
    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    /**
     * 
     * @return
     *     The editProfile
     */
    public boolean isEditProfile() {
        return editProfile;
    }

    /**
     * 
     * @param editProfile
     *     The editProfile
     */
    public void setEditProfile(boolean editProfile) {
        this.editProfile = editProfile;
    }

    /**
     * 
     * @return
     *     The mask
     */
    public String getMask() {
        return mask;
    }

    /**
     * 
     * @param mask
     *     The mask
     */
    public void setMask(String mask) {
        this.mask = mask;
    }

    /**
     * 
     * @return
     *     The combo
     */
    public List<String> getCombo() {
        return combo;
    }

    /**
     * 
     * @param combo
     *     The combo
     */
    public void setCombo(List<String> combo) {
        this.combo = combo;
    }

    public View getView(LayoutInflater inflater, ViewGroup viewGroup, Context context) {
        View mView = null;
        switch (getType()) {
            case "string":
                EditText textFildView = (EditText) inflater.inflate(R.layout.text_field, viewGroup, false);
                textFildView.setHint(getName());
                mView = textFildView;
                break;
            case "gender":
                RadioGroup radioFieldView = (RadioGroup) inflater.inflate(R.layout.gender_select, viewGroup, false);
                mView = radioFieldView;
                break;
            case "combo":
                Spinner comboFieldView = (Spinner) inflater.inflate(R.layout.combo_field, viewGroup, false);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, getCombo());
                comboFieldView.setAdapter(adapter);
                mView = comboFieldView;
                break;
            default:
                TextView unknowFieldView = (TextView) inflater.inflate(R.layout.unknow_field, viewGroup, false);
                mView = unknowFieldView;
                break;
        }

        mView.setTag(this);
        return mView;
    }

}
