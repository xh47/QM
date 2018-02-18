package compsci290.edu.duke.qm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

import java.util.*;

/**
 * Created by Ian Hua on 2/18/2018.
 */

public class QuizGenerator {

    private ArrayList<Question> myQuestions;
    private int mIndex;

    public class Question {
        private String mQuery;
        private String mCorrect;
        private String [] mWrong = new String[4];

        public Question(String q, String right, String [] wrong) {

            mQuery = q;
            mCorrect = right;
            mWrong = wrong;
        }
        public String getQuery() {
            return mQuery;
        }

        public String getCorrectAnswer() {
            return mCorrect;
        }

        public String getWrongAnswer() {
            Random rand = new Random();

            int  x = rand.nextInt(3);
            return mWrong[x];
        }
    }

    public int size(){
        return myQuestions.size();
    }

    public Question getQuestion(){
        if (mIndex < myQuestions.size()) {
            Question q = myQuestions.get(mIndex);
            mIndex++;
            return q;
        }
        return null;
    }
    public Question getQuestion(int index){
        if (0 <= index && index < myQuestions.size()){
            return myQuestions.get(index);
        }
        throw new IndexOutOfBoundsException("bad index "+index);
    }

    public QuizGenerator(){
        myQuestions = new ArrayList<Question>();
        makeQuiz();
    }

    private void makeQuiz() {
        String [] wrongs1 = {"The Imitation Game","The Circle","The Social Network"};
        String [] wrongs2 = {"6:10","4:55","7:30"};
        String [] wrongs3 = {"Selena Gomez","Beyonce","Miley Cyrus"};
        String [] wrongs4 = {"North Carolina","California","New York"};
        String [] wrongs5 = {"Migos", "Young Thug", "Childish Gambino"};


        myQuestions.add(new Question("What movie has Professor Astrachan’s brother produced?","Short Term 12 ",wrongs1));
        myQuestions.add(new Question("What is Professor Astrachan’s mile time?", "5:29", wrongs2));
        myQuestions.add(new Question("Which big name celebrity does Professor Astrachan subscribe to on Youtube?","Taylor Swift", wrongs3));
        myQuestions.add(new Question("Where was Landon on 25 Nov 2008?","Michigan",wrongs4));
        myQuestions.add(new Question("Which rapper does Landon follow on Twitter?","Chance the Rapper",wrongs5));
    }
}
