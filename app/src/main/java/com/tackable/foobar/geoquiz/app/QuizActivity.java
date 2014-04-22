package com.tackable.foobar.geoquiz.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//android:orientation="vertical" in layout permanently sets orientation
public class QuizActivity extends ActionBarActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "Index";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
      new TrueFalse(R.string.question_oceans, true),
      new TrueFalse(R.string.question_mideast, false),
      new TrueFalse(R.string.question_africa, false),
      new TrueFalse(R.string.question_americas, true),
      new TrueFalse(R.string.question_asia, true),
    };
    private int mCurrentIndex = 0;

    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart()");
    }
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause()");
    }
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume()");
    }
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop()");
    }
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    private void updateQuestion(){
//        Log.d(TAG, "Logging updateQuestion() for question #" + mCurrentIndex, new Exception());
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;
        if( userPressedTrue == answerIsTrue ){
            messageResId = R.string.correct_toast;
        }
        else{
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "test onCreate() log");

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mTrueButton = (Button)findViewById(R.id.true_button);
       // mTrueButton = (Button)findViewById(R.id.question_text_view);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("true click", "onclick");
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //something else here
                Log.v("false click", "onclick");
                checkAnswer(false);
            }
        });
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v("next click", "in on click");
                mCurrentIndex = (mCurrentIndex + 1)% mQuestionBank.length;
                updateQuestion();
            }
        });

        mPreviousButton = (Button)findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v("previous click", "in on click");
                mCurrentIndex -= 1;
                if(mCurrentIndex < 0){
                    mCurrentIndex = mQuestionBank.length - 1;
                }
                updateQuestion();
            }
        });


        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v("text view click", "in on click");
                mCurrentIndex = (mCurrentIndex + 1)% mQuestionBank.length;
                updateQuestion();

            }
        });

        if( savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        updateQuestion();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState()");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
