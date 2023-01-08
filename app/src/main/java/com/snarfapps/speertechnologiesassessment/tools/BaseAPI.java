package com.snarfapps.speertechnologiesassessment.tools;

import android.util.Log;

import com.google.gson.JsonObject;
import com.loopj.android.http.RequestParams;

public abstract class BaseAPI {

    /**
     *
     * Create interface callback methods
     *
     */
    public interface BaseAPICallbacks {
        // method explanations on the defaultCallbacks variable
        void onError(String errMsg, BaseAPI caller);
        void onSuccessFinished(JsonObject response, BaseAPI caller);
        void onErrorCodeReceived(String errorCode, JsonObject responseBodyJson, BaseAPI caller);
    }


    /**
     * Base variables
     */
    //class tag
    private final String TAG = getClass().getSimpleName();
    //Server URL
    private final String API_URL = "";

    //api url endpoint, this will be changed by class extensions
    public String REQUEST_URL= "";

    //Create 2 types of params, JSON and Multipart
    public JsonObject params = new JsonObject();
    public RequestParams requestParams = new RequestParams();

    /**
     * Create a default callback listener
     */

    private BaseAPICallbacks defaultCallbacks = new BaseAPICallbacks() {
        /*
            errMsg - the api error message received,
            caller - BaseAPI extension that triggered the error
         */
        @Override
        public void onError(String errMsg, BaseAPI caller) {
            Log.e(TAG,"API ERROR "+ errMsg + " from "+ caller.getClass().getSimpleName());
        }

        /*
            response is the Json response from the API endpoint when it returns 200
         */
        @Override
        public void onSuccessFinished(JsonObject response, BaseAPI caller) {
            Log.e(TAG, "SUCCESS API: "+ response.toString() +" == from "+ caller.getClass().getSimpleName());
        }

        @Override
        public void onErrorCodeReceived(String errorCode, JsonObject responseBodyJson, BaseAPI caller) {
            Log.e(TAG,"API_ERROR_CODE: "+errorCode+ " from "+caller.getClass().getSimpleName());
        }
    };

    /**
     * Abstract methods for subclasses
     *
     */

    abstract String getUrlTail();
    abstract API_METHOD getMethod();
    abstract REQUEST_TYPE getRequestType();

    /**
     * Enums for subclasses' abstract methods return values
     */
    /*
        sets the BaseAPI subclass' request type
     */
    public enum REQUEST_TYPE{
        JSON,FORM_DATA
    }
    /*
        sets the BaseAPI subclass' endpoint method
     */
    public enum API_METHOD{
        GET,POST
    }
}
