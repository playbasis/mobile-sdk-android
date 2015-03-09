package com.playbasis.android.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.playbasis.android.playbasissdk.api.OnResult;
import com.playbasis.android.playbasissdk.api.QuizApi;
import com.playbasis.android.playbasissdk.http.HttpError;
import com.playbasis.android.playbasissdk.model.Quiz;
import com.playbasis.android.playbasissdk.model.QuizQuestion;
import com.playbasis.android.playbasissdk.model.QuizQuestionAnswer;
import com.playbasis.android.playbasissdk.model.QuizQuestionOption;
import com.playbasis.android.playbasissdk.model.Reward;

import java.util.ArrayList;
import java.util.List;


public class QuizActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
    
    TextView vTitle;
    ListView vListView;
    ProgressBar progressBar;

    ArrayAdapter<NameID> adapter;
    List<NameID> adapterValues;
    List<Reward> rewards;
    
    // use for know if we have to request a quiz or a question 
    int quizState = 0;
    
    String playerId = "exampleplayer"; //The player id
    String quizId; // The quiz id
    String questionId; // The question id
    int questionCount = 0;
    String[] questionIds = {"2006735390fb144a5cc9f544", "2588556f1dfe6ca5bfd49497"};

    @Override
    protected void onResume() {
        super.onResume();
        SampleApplication.playbasis.setActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SampleApplication.playbasis.removeActivity();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Set views
        vTitle = (TextView) findViewById(R.id.textView_title);
        vListView = (ListView) findViewById(R.id.listView_quiz);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Create the adapter
        adapterValues = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, adapterValues);
        // Assign adapter to ListView
        vListView.setAdapter(adapter);
        vListView.setOnItemClickListener(this);

        getQuiz();
    }

    private void showProgress(Boolean show){
        if(show){
            progressBar.setVisibility(View.VISIBLE);
            vListView.setVisibility(View.INVISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
            vListView.setVisibility(View.VISIBLE);
        }
    }
    
    
    private void getQuiz(){
        quizState = 1; //set quiz state to quiz
        showProgress(true);
        QuizApi.activeList(SampleApplication.playbasis, playerId, new OnResult<List<Quiz>>() {
            @Override
            public void onSuccess(List<Quiz> result) {
                vTitle.setText("Select a quiz:"); //Display a title
                adapterValues.clear(); // Clean the list view
                if(result!=null){
                    // populate the list view with quiz available
                    for (Quiz quiz : result) {
                        adapterValues.add(new NameID(quiz.getQuizId() ,quiz.getName()));
                    }
                }
                adapter.notifyDataSetChanged();
                showProgress(false);
            }

            @Override
            public void onError(HttpError error) {
                Toast.makeText(QuizActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                showProgress(false);
            }
        });
        
    }

    private void getQuestions(String questionId){
        quizState = 2; // set quizSate on question state
        
        
        QuizApi.questions(SampleApplication.playbasis,quizId,playerId, questionId, new OnResult<QuizQuestion>() {
            @Override
            public void onSuccess(final QuizQuestion result) {
                
                adapterValues.clear();// clear listView
                if(result!=null && result.getQuestionId()!=null){ //test if question id is null
                    vTitle.setText(result.getQuestion());
                    QuizActivity.this.questionId = result.getQuestionId(); // save the question id
                    // populate the list view with options
                    if(result.getOptions()!=null){
                        for (QuizQuestionOption option : result.getOptions()) {
                            adapterValues.add(new NameID(option.getOptionId() ,option.getOption()));
                        }
                    }
                }else{
                    //if questionId null, the quiz doesn't have other questions
                    vTitle.setText("Quiz finish");

                    // Get the previous question rewards
                    if(rewards!=null){
                        RewardWidget rewardWidget = new RewardWidget(); // Create reward dialogFragment
                        for (Reward reward : rewards) {
                            if(reward.getRewardType().equals("exp")){ //Set Exp to reward dialogFragment
                                rewardWidget.setExp(reward.getRewardValue());
                            }else if(reward.getRewardType().equals("point")){//Set point to reward dialogFragment
                                rewardWidget.setPoints(reward.getRewardValue());
                            }else if(reward.getRewardType().equals("badge")){//Set badge to reward dialogFragment
                                rewardWidget.setBadge(reward.getRewardData());
                            }
                        }
                        rewardWidget.show(getSupportFragmentManager(), "fragment_reward_widget"); // Show dialog
                    }

                }
                adapter.notifyDataSetChanged(); // update listView
                
            }

            @Override
            public void onError(HttpError error) {
                Toast.makeText(QuizActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        } );
        
    }
    
    //Send the question answer
    private void sendAnswer(String optionId){
        QuizApi.answerQuestion(SampleApplication.playbasis, quizId, playerId, questionId, optionId, new OnResult<QuizQuestionAnswer>() {
            @Override
            public void onSuccess(QuizQuestionAnswer result) {
                rewards = result.getRewards();// save rewards
                getQuestions(questionIds[questionCount]);//Get next question
                questionCount++;
            }
            @Override
            public void onError(HttpError error) {
                //Show error
                Toast.makeText(QuizActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if(quizState ==1){ // if quiz sate
            quizId = adapterValues.get(position).id; // save selected quiz id
            getQuestions(null); //get questions
        }else if (quizState == 2){ // if question state
            sendAnswer(adapterValues.get(position).id); // send answer
        }

    }

    //Class display on the list view
    private class NameID{
        public String id;
        public String name;

        private NameID(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
