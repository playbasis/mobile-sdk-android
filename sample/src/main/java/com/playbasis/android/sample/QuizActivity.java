package com.playbasis.android.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.QuizApi;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Quiz;

import java.util.List;


public class QuizActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getQuiz();
    }
    
    
    private void getQuiz(){
        QuizApi.activeList(SampleApplication.playbasis, "gregusertest", new OnResult<List<Quiz>>() {
            @Override
            public void onSuccess(List<Quiz> result) {
                Log.d("", "");
            }

            @Override
            public void onError(HttpError error) {
                Log.d("", "");
            }
        });
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
