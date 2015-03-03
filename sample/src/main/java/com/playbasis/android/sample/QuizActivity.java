package com.playbasis.android.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.QuizApi;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Quiz;

import java.util.List;


public class QuizActivity extends FragmentActivity {
    
    EditText vPlayerId;
    LinearLayout vLayout;

    @Override
    protected void onStart() {
        super.onStart();
        SampleApplication.playbasis.setActivity(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        SampleApplication.playbasis.removeActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        vPlayerId = (EditText) findViewById(R.id.editText_player_id);
        vLayout = (LinearLayout) findViewById(R.id.layout_quiz);
        
        Button submitButton  = (Button) findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getQuiz(vPlayerId.getText().toString());
            }
        });
    }
    
    
    private void getQuiz(String playerId){
        QuizApi.activeList(SampleApplication.playbasis, playerId, new OnResult<List<Quiz>>() {
            @Override
            public void onSuccess(List<Quiz> result) {
            }

            @Override
            public void onError(HttpError error) {
                Toast.makeText(QuizActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
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
