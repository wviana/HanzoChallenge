package com.hanzo.wviana.hanzotestapp;


import com.hanzo.wviana.hanzotestapp.model.HanzoUserFields;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by wviana on 9/21/15.
 */
public interface HanzoApi {
    String BASE_URL = "https://enterprise.hanzo.com.br/contents/";

    @GET("/8/fields.json")
    void getUserFields(Callback<HanzoUserFields> response);
}
