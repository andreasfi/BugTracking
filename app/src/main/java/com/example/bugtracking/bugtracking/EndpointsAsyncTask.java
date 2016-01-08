package com.example.bugtracking.bugtracking;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.andreas.myapplication.backend.developerApi.DeveloperApi;
import com.example.andreas.myapplication.backend.developerApi.model.Developer;
import com.example.bugtracking.bugtracking.adapter.DeveloperDataSource;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

/**
 * Created by Andreas on 07.01.2016.
 */
public class EndpointsAsyncTask extends AsyncTask {
    private static DeveloperApi developerApi = null;
    private static final String TAG = EndpointsAsyncTask.class.getName();
    private List<Developer> developer;

    EndpointsAsyncTask(){}

    EndpointsAsyncTask(List<Developer>developer){
        this.developer = developer;
    }

    @Override
    protected List<Developer> doInBackground(Object[] params) {
        if(developerApi == null){
            // Only do this once
            DeveloperApi.Builder builder = new DeveloperApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    // if you deploy on the cloud backend, use your app name
                    // such as https://<your-app-id>.appspot.com
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            developerApi = builder.build();
        }
        try{
            for(Developer developer2 : developer){
                developerApi.insert((Developer)developer2);
            }

            return null;

        } catch (IOException e){
            Log.e(TAG, e.toString());
        }

        return null;
    }
}
