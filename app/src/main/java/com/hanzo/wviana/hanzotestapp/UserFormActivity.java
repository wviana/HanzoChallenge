package com.hanzo.wviana.hanzotestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.hanzo.wviana.hanzotestapp.model.Field;
import com.hanzo.wviana.hanzotestapp.model.HanzoUserFields;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by wviana on 9/21/15.
 */
public class UserFormActivity extends AppCompatActivity{

    public static String USER_DATA = "user_data";
    public static int NEW_USER = 100;
    public static int EDIT_USER = 200;
    @Bind(R.id.user_form_filds_list)
    LinearLayout formFildList;
    private HashMap<String, View> jsonNameView = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        ButterKnife.bind(this);

        HanzoApi api = new RestAdapter.Builder()
                .setEndpoint(HanzoApi.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(HanzoApi.class);

        api.getUserFields(new Callback<HanzoUserFields>() {
            @Override
            public void success(HanzoUserFields hanzoUserFields, Response response) {
                inflateFields(hanzoUserFields.getFields());
                loadFieldsDataIfExists();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void loadFieldsDataIfExists() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.get(MainActivity.EDIT_DATA) != null){
            HashMap<String, String> editData = (HashMap<String, String>) bundle.get(MainActivity.EDIT_DATA);
            for(String key : editData.keySet()){
                if(jsonNameView.containsKey(key)){
                    setFieldValue(jsonNameView.get(key), editData.get(key));
                }
            }
        }
    }

    @OnClick(R.id.user_form_save)
    public void saveUser(){
        if (validate()) {
            HashMap<String, String> userFieldsValues = new HashMap<>();

            int formSize = formFildList.getChildCount();
            for(int i = 0; i < formSize; i++) {
                View viewFild = formFildList.getChildAt(i);
                Field f = (Field) viewFild.getTag();

                userFieldsValues.put(f.getJsonName(), getFieldValue(viewFild));

            }
            Intent intent = new Intent();
            intent.putExtra(USER_DATA, userFieldsValues);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    private String getFieldValue(View viewField){
        String value = "";
        if (viewField instanceof EditText) {
            value = ((EditText) viewField).getText().toString();
        } else if (viewField instanceof Spinner) {
            value = ((Spinner) viewField).getSelectedItem().toString();
        } else if (viewField instanceof RadioGroup) {
            int selectedId = ((RadioGroup) viewField).getCheckedRadioButtonId();
            if (selectedId == R.id.radio_gender_male) {
                value = "male";
            } else {
                value = "female";
            }
        }
        return value;
    }

    private void setFieldValue(View viewField, String value){
        if (viewField instanceof EditText) {
            ((EditText) viewField).setText(value);
        } else if (viewField instanceof Spinner) {
            Spinner comboField = ((Spinner) viewField);
            int itemsCount = comboField.getCount();
            for(int i = 0; i < itemsCount; i++){
                if(comboField.getItemAtPosition(i).toString().equals(value)){
                    comboField.setSelection(i);
                }
            }
        } else if (viewField instanceof RadioGroup) {
            RadioGroup genderField = ((RadioGroup) viewField);
            if(value.equals("male")){
                ((RadioButton) genderField.findViewById(R.id.radio_gender_male)).toggle();
            } else {
                ((RadioButton) genderField.findViewById(R.id.radio_gender_female)).toggle();
            }
        }
    }

    private void inflateFields(List<Field> fields) {
        LayoutInflater inflater = getLayoutInflater();
        for(Field f : fields){
            View view = f.getView(inflater, formFildList, this);
            formFildList.addView(view);
            jsonNameView.put(f.getJsonName(), view);
        }
    }

    private boolean validateField(View viewField){
        Field field = (Field) viewField.getTag();
        if (viewField instanceof EditText) {
            EditText textField = ((EditText) viewField);
            if(field.isMandatory()){
                if(textField.getText().length() < field.getMinSize()){
                    textField.setError(String.format(getString(R.string.invalid_field_min_size), field.getMinSize()));
                    return false;
                }
                if(textField.getText().length() > field.getMaxSize()){
                    textField.setError(String.format(getString(R.string.invalid_field_max_size), field.getMaxSize()));
                    return false;
                }
            }
        } else if (viewField instanceof Spinner) {
            Spinner comboField = ((Spinner) viewField);
            if(field.isMandatory() && comboField.getSelectedItem().toString().equals("")){
                return false;
            }
        } else if (viewField instanceof RadioGroup) {
            RadioGroup genderField = ((RadioGroup) viewField);
            if(field.isMandatory() && !genderField.isSelected()){
                return false;
            }
        }

        return true;
    }

    private boolean validate(){
        int formSize = formFildList.getChildCount();

        for(int i = 0; i < formSize; i++){
            View formFild = formFildList.getChildAt(i);
            if(!validateField(formFild)){
                return false;
            }
        }

        return true;

    }

    @Override
    public void onBackPressed() {
        loadFieldsDataIfExists();
        saveUser();
    }
}
