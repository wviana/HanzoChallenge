package com.hanzo.wviana.hanzotestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wviana on 9/21/15.
 */
public class UserFormActivity extends AppCompatActivity{

    @Bind(R.id.user_form_filds_list)
    ListView formFildList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        ButterKnife.bind(this);
    }
}
