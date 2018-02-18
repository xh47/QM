package compsci290.edu.duke.qm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Ian Hua on 2/18/2018.
 */

public class MainActivity extends AppCompatActivity {

    private Button myLeftButton;
    private Button myRightButton;
    private Button myBottomLeftButton;
    private Button myBottomRightButton;

    private QuizGenerator myQuiz;
    private Quiz4Generator my4Quiz;
    private TextView myQuestionView;
    private TextView mScoreView;
    private TextView finalScoreView;
    private int mQuestionIndex;
    private int mCorrect;
    private int mRandom = (int) Math.random()*2;;
    private String mScoreBase;
    private ArrayList<Integer> shuff = new ArrayList<>();
    private int mQNum = 5;
    private String[] mAnswers = new String[4];


    private static String INDEX = "INDEX";
    private static String SCORE = "SCORE";
    private static String GAME = "GAME";
    private static String ANSWERS = "ANSWERS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i = 0; i < 4; i++){
            shuff.add(i,i);
        }
        setContentView(R.layout.home_screen);




//        if (savedInstanceState != null) {
//            Toast.makeText(MainActivity.this,
//                    "create and saved != null",
//                    Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        mQNum = savedInstanceState.getInt(GAME);
        mQuestionIndex = savedInstanceState.getInt(INDEX);
        myQuiz = new QuizGenerator();
        my4Quiz = new Quiz4Generator();
        mCorrect = savedInstanceState.getInt(SCORE);
        mAnswers = savedInstanceState.getStringArray(ANSWERS);
    if (mQNum == 2) {
        new2Game2(mAnswers);
        myQuiz = new QuizGenerator();
        QuizGenerator.Question q = myQuiz.getQuestion(mQuestionIndex);
        String s = String.format("%d/%d with %d to go",mCorrect,myQuiz.size(),(myQuiz.size()-mQuestionIndex));
        mScoreView.setText(mScoreBase+s);

        myQuestionView.setText(q.getQuery());


    }
        else if (mQNum == 4){
        new4Game2(mAnswers);
        my4Quiz = new Quiz4Generator();
        Quiz4Generator.Question q = my4Quiz.getQuestion(mQuestionIndex);
        String s = String.format("%d/%d with %d to go",mCorrect,my4Quiz.size(),(my4Quiz.size()-mQuestionIndex));
        mScoreView.setText(mScoreBase+s);


        myQuestionView.setText(q.getQuery());

    }

        else if (mQNum == 0){
        String s = String.format("%d/%d with %d to go",mCorrect,myQuiz.size(),(myQuiz.size()-mQuestionIndex));
        String fin = "Score:"+ " " + s;

        setContentView(R.layout.done);
        finalScoreView = (TextView) this.findViewById(R.id.score_view);
        finalScoreView.setText(fin);
    }
    else if (mQNum == 1){
        String s = String.format("%d/%d with %d to go",mCorrect,my4Quiz.size(),(my4Quiz.size()-mQuestionIndex));
        String fin = "Score:"+ " " + s;

        setContentView(R.layout.done4);
        finalScoreView = (TextView) this.findViewById(R.id.score_view);
        finalScoreView.setText(fin);
    }
        else if (mQNum == 5){
        setContentView(R.layout.home_screen);

    }


    }

    @Override
    public void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        state.putInt(INDEX,mQuestionIndex);
        state.putInt(SCORE,mCorrect);
        state.putInt(GAME, mQNum);
        state.putStringArray(ANSWERS, mAnswers);

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    public void takeAnotherQuiz(View button){
        new2Game();
    }

    public void takeAnother4Quiz(View button){
        new4Game();
    }
    public void quit(View button){
        setContentView(R.layout.home_screen);
    }
    public void leftClick(View button){
        if(myLeftButton.getText().toString().equals(myQuiz.getQuestion(mQuestionIndex).getCorrectAnswer())) {
            Toast.makeText(MainActivity.this,
                    "that's correct! :-)",
                    Toast.LENGTH_SHORT).show();
            mCorrect += 1;
            updateScore();
        }
        else{
            Toast.makeText(MainActivity.this,
                    "that's wrong :-(",
                    Toast.LENGTH_SHORT).show();
            updateScore();
        }


    }

    public void rightClick(View button){
        if(myRightButton.getText().toString().equals(myQuiz.getQuestion(mQuestionIndex).getCorrectAnswer())) {
            Toast.makeText(MainActivity.this,
                    "that's correct! :-)",
                    Toast.LENGTH_SHORT).show();
            mCorrect += 1;
            updateScore();
        }
        else {
            Toast.makeText(MainActivity.this,
                    "that's wrong :-(",
                    Toast.LENGTH_SHORT).show();
            updateScore();
        }


    }
    public void topLeftClick(View button){
        if(myLeftButton.getText().toString().equals(my4Quiz.getQuestion(mQuestionIndex).getCorrectAnswer())) {
            Toast.makeText(MainActivity.this,
                    "that's correct! :-)",
                    Toast.LENGTH_SHORT).show();
            mCorrect += 1;
            update4Score();
        }
        else{
            Toast.makeText(MainActivity.this,
                    "that's wrong :-(",
                    Toast.LENGTH_SHORT).show();
            update4Score();
        }


    }

    public void topRightClick(View button) {
        if (myRightButton.getText().toString().equals(my4Quiz.getQuestion(mQuestionIndex).getCorrectAnswer())) {
            Toast.makeText(MainActivity.this,
                    "that's correct! :-)",
                    Toast.LENGTH_SHORT).show();
            mCorrect += 1;
            update4Score();
        } else {
            Toast.makeText(MainActivity.this,
                    "that's wrong :-(",
                    Toast.LENGTH_SHORT).show();
            update4Score();
        }
    }

    public void bottomLeftClick(View button){
        if(myBottomLeftButton.getText().toString().equals(my4Quiz.getQuestion(mQuestionIndex).getCorrectAnswer())) {
            Toast.makeText(MainActivity.this,
                    "that's correct! :-)",
                    Toast.LENGTH_SHORT).show();
            mCorrect += 1;
            update4Score();
        }
        else{
            Toast.makeText(MainActivity.this,
                    "that's wrong :-(",
                    Toast.LENGTH_SHORT).show();
            update4Score();
        }


    }

    public void bottomRightClick(View button){
        if(myBottomRightButton.getText().toString().equals(my4Quiz.getQuestion(mQuestionIndex).getCorrectAnswer())) {
            Toast.makeText(MainActivity.this,
                    "that's correct! :-)",
                    Toast.LENGTH_SHORT).show();
            mCorrect += 1;
            update4Score();
        }
        else{
            Toast.makeText(MainActivity.this,
                    "that's wrong :-(",
                    Toast.LENGTH_SHORT).show();
            update4Score();
        }


    }



    private void updateScore() {
        mQuestionIndex += 1;
        int remaining = myQuiz.size()-mQuestionIndex;
        String s = String.format("%d/%d with %d to go",mCorrect,myQuiz.size(),remaining);
        mScoreView.setText(mScoreBase+s);
        if (mQuestionIndex < myQuiz.size()){
            askQuestion(mQuestionIndex);

        }
        else {
            Toast.makeText(MainActivity.this,
                    "quiz is over",
                    Toast.LENGTH_SHORT).show();
    mQNum = 0;
            String fin = mScoreBase+ " " + s;

            setContentView(R.layout.done);
            finalScoreView = (TextView) this.findViewById(R.id.score_view);
            finalScoreView.setText(fin);

        }

    }

    private void update4Score() {
        mQuestionIndex += 1;
        int remaining = my4Quiz.size()-mQuestionIndex;
        String s = String.format("%d/%d \n\n   %d to go",mCorrect,my4Quiz.size(),remaining);
        mScoreView.setText(mScoreBase+s);
        if (mQuestionIndex < my4Quiz.size()){
            ask4Question(mQuestionIndex);

        }
        else {
            Toast.makeText(MainActivity.this,
                    "quiz is over",
                    Toast.LENGTH_SHORT).show();
            mQNum = 1;
            String fin = mScoreBase+ " " +s;

            setContentView(R.layout.done4);
            finalScoreView = (TextView) this.findViewById(R.id.score_view);
            finalScoreView.setText(fin);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id) {
            case R.id.new_2quiz:
                new2Game();
                return true;
            case R.id.new_4quiz:
                new4Game();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void new2Game() {
        setContentView(R.layout.activity_main);
        mQNum = 2;
        myQuiz = new QuizGenerator();
        myQuestionView = (TextView) this.findViewById(R.id.question_text);
        myLeftButton = (Button) this.findViewById(R.id.left_button);
        myRightButton = (Button) this.findViewById(R.id.right_button);
        mScoreView = (TextView) this.findViewById(R.id.score_view);
        mScoreBase = mScoreView.getText().toString();
        String s = String.format("%d/%d with %d to go",mCorrect,myQuiz.size(),(myQuiz.size()-mQuestionIndex));
        mScoreView.setText(mScoreBase+s);
        mQuestionIndex = 0;
        mCorrect = 0;
        askQuestion(mQuestionIndex);

    }
    private void new2Game2(String[] answers) {
        setContentView(R.layout.activity_main);
        mQNum = 2;
        myQuiz = new QuizGenerator();
        myQuestionView = (TextView) this.findViewById(R.id.question_text);
        myLeftButton = (Button) this.findViewById(R.id.left_button);
        myRightButton = (Button) this.findViewById(R.id.right_button);
        mScoreView = (TextView) this.findViewById(R.id.score_view);
        mScoreBase = mScoreView.getText().toString();


        myRightButton.setText(answers[1]);
        myLeftButton.setText(answers[0]);



    }
    private void new4Game() {
        setContentView(R.layout.four_question);
        mQNum = 4;
        mScoreView = (TextView) this.findViewById(R.id.score_view4);
        mScoreBase = mScoreView.getText().toString();


        my4Quiz = new Quiz4Generator();
        String s = String.format("%d/%d with %d to go",mCorrect,my4Quiz.size(),(my4Quiz.size()-mQuestionIndex));
        mScoreView.setText(mScoreBase+s);
        myQuestionView = (TextView) this.findViewById(R.id.question_text4);
        myBottomLeftButton = (Button) this.findViewById(R.id.bottomleft_button);
        myBottomRightButton = (Button) this.findViewById(R.id.bottomright_button);
        myLeftButton = (Button) this.findViewById(R.id.topleft_button);
        myRightButton = (Button) this.findViewById(R.id.topright_button);


        mQuestionIndex = 0;
        mCorrect = 0;

        ask4Question(mQuestionIndex);


    }
    private void new4Game2(String[] answers) {
        setContentView(R.layout.four_question);
        mQNum = 4;
        mScoreView = (TextView) this.findViewById(R.id.score_view4);
        mScoreBase = mScoreView.getText().toString();
        my4Quiz = new Quiz4Generator();
        myQuestionView = (TextView) this.findViewById(R.id.question_text4);
        myBottomLeftButton = (Button) this.findViewById(R.id.bottomleft_button);
        myBottomRightButton = (Button) this.findViewById(R.id.bottomright_button);
        myLeftButton = (Button) this.findViewById(R.id.topleft_button);
        myRightButton = (Button) this.findViewById(R.id.topright_button);


        myLeftButton.setText(answers[0]);
        myRightButton.setText(answers[1]);
        myBottomLeftButton.setText(answers[2]);
        myBottomRightButton.setText(answers[3]);



    }


    private void askQuestion(int mQI) {
        QuizGenerator.Question q = myQuiz.getQuestion(mQI);
        myQuestionView.setText(q.getQuery());
        mRandom = (int) (Math.random()*2);


        if (mRandom == 1) {
            myLeftButton.setText(q.getCorrectAnswer());
            myRightButton.setText(q.getWrongAnswer());
        }
        else if(mRandom == 0){
            myRightButton.setText(q.getCorrectAnswer());
            myLeftButton.setText(q.getWrongAnswer());
        }

        String rightB = myRightButton.getText().toString();
        String leftB = myLeftButton.getText().toString();
        mAnswers[1]= rightB;
        mAnswers[0]= leftB;
    }

    private void ask4Question(int mQI) {

        Collections.shuffle(shuff);
        Quiz4Generator.Question q = my4Quiz.getQuestion(mQI);
        myQuestionView.setText(q.getQuery());



        myLeftButton.setText(q.getAnswers()[shuff.get(0)]);
        myRightButton.setText(q.getAnswers()[shuff.get(1)]);
        myBottomRightButton.setText(q.getAnswers()[shuff.get(2)]);
        myBottomLeftButton.setText(q.getAnswers()[shuff.get(3)]);

        String rightB = myRightButton.getText().toString();
        String leftB = myLeftButton.getText().toString();
        String leftBB = myBottomLeftButton.getText().toString();
        String rightBB = myBottomRightButton.getText().toString();
        mAnswers[0]= leftB;
        mAnswers[1]= rightB;
        mAnswers[2]= leftBB;
        mAnswers[3]= rightBB;


    }


}
