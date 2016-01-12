package com.example.bugtracking.bugtracking;

import android.content.Context;
import android.os.AsyncTask;

import com.example.andreas.myapplication.backend.developerApi.DeveloperApi;
import com.example.andreas.myapplication.backend.developerApi.model.Developer;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.appengine.repackaged.com.google.common.base.Pair;

import java.io.IOException;
import java.util.List;


/**
 * Created by andreas on 1/11/16.
 */
public class EndpointsAsyncTask extends AsyncTask {
    private static DeveloperApi developerApi = null;
    private static final String TAG = EndpointsAsyncTask.class.getName();
    private List<Developer> developer;

    EndpointsAsyncTask(List<Developer>developer){
        this.developer = developer;
    }

    @Override
    protected String doInBackground(Object[] params) {
        if(developerApi != null){
            DeveloperApi.Builder builder = new DeveloperApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),null)
                    .setRootUrl("http:/1.bugtrack-1187.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
            developerApi = builder.build();
        }


        try {
            for(Developer developerins : developer){
                developerApi.insert(developerins);
            }
            return null;
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
