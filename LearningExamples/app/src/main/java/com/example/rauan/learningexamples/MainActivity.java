package com.example.rauan.learningexamples;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton, prevButton;
    private Button mCheatButton;
    private TextView questionTexview;
    private boolean mIsCheater;

    private static final int REQUEST_CODE_CHEAT = 0;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    private int mCurrentIndex = 0;

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        questionTexview.setText(question);
    }
    private void checkAnswer(boolean userPressedTrue) {

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }

        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionTexview = (TextView)findViewById(R.id.question_text_view);

        nextButton = (Button) findViewById(R.id.next_button);
        prevButton = (Button) findViewById(R.id.prev_button);
        trueButton = (Button) findViewById(R.id.true_button);
        falseButton = (Button) findViewById(R.id.false_button);
        questionTexview = (TextView) findViewById(R.id.question_text_view);
        mCheatButton = (Button) findViewById(R.id.cheat_button);

        questionTexview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);

            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentIndex == 0){
                    mCurrentIndex = 5;
                }
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                Log.d("testindex", "curent index - " + mCurrentIndex + "\nlen - " + mQuestionBank.length +
                        "\nindex - 1 - " + (mCurrentIndex - 1));
                updateQuestion();
            }
        });
        mIsCheater = false;
        updateQuestion();

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean answerIsTrue = mQuestionBank[mCurrentIndex].
                        isAnswerTrue();
                Intent i = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
                        startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });
    }

    public void nextQuestion(){
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
//        Log.d("testindex", "curent index - " + mCurrentIndex + "\nlen - " + mQuestionBank.length +
//                "\nindex + 1 - " + (mCurrentIndex + 1));
        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != AppCompatActivity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            if (data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }
}
