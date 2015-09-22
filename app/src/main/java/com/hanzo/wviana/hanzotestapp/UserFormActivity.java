package com.hanzo.wviana.hanzotestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hanzo.wviana.hanzotestapp.model.Field;
import com.hanzo.wviana.hanzotestapp.model.HanzoUserFields;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by wviana on 9/21/15.
 */
public class UserFormActivity extends AppCompatActivity{

    @Bind(R.id.user_form_filds_list)
    LinearLayout formFildList;

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
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }

    private void inflateFields(List<Field> fields) {
        for(Field f : fields){
            View fildView = genField(f);
            formFildList.addView(fildView);
        }
    }

    private View genField(Field f){
        LayoutInflater inflater = getLayoutInflater();

        if(f.getType().equals("string")){
            return genTextField(inflater ,f);
        } else {
            return genTextField(inflater, f);
        }
    }

    private View genTextField(LayoutInflater inflater, Field f) {
        EditText fildView = (EditText) inflater.inflate(R.layout.text_field, formFildList, false);
        fildView.setHint(f.getName());
        fildView.setTag(f);
        return fildView;
    }

    private boolean validate(){
        int formSize = formFildList.getChildCount();

        for(int i = 0; i < formSize; i++){
            View formFild = formFildList.getChildAt(i);
            if(formFild instanceof EditText){
                if(!validateEditTextFild((EditText) formFild)) {
                    return false;
                }
            }
        }

        return true;

    }

    private boolean validateEditTextFild(EditText formFild) {
        Field f = (Field) formFild.getTag();
        String formText = formFild.getText().toString();
        int formTextSize = formText.length();

        if((!f.isMandatory() && formTextSize == 0)){
            return true;
        } else if(formTextSize <= f.getMaxSize()){
            formFild.setError(String.format("Campo deve ter menos que %d caracteres.", f.getMaxSize()));
            return false;
        } else if(formTextSize >= f.getMinSize()){
            formFild.setError(String.format("Campo deve ter mais que %d caracteres.", f.getMinSize()));
            return false;
        }

        return true;
    }
}
