package compsci290.edu.duke.qm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ian Hua on 2/18/2018.
 */

public class Quiz {

    private ArrayList<Question> myQuestions;
    private final String mTitle;
    private int mQIndex;
    private int score;

    public Quiz(String title, ArrayList<Question> questions) {
        mTitle = title;
        myQuestions = questions;
        mQIndex = 0;
        score = 0;
    }

    public String getTitle() {
        return mTitle;
    }

    public int size() {
        return myQuestions.size();
    }

    public Question getQuestion(int index) {
        if (0 <= index && index < myQuestions.size()) {
            return myQuestions.get(index);
        }
        throw new IndexOutOfBoundsException("bad index " + index);
    }

    public Question getCurrentQuestion() {
        if (0 <= mQIndex && mQIndex < myQuestions.size()) {
            return myQuestions.get(mQIndex);
        }
        throw new IndexOutOfBoundsException("bad index " + mQIndex);
    }

    public void setNextQuestion() {
        if (mQIndex + 1 < size()) {
            mQIndex++;
        } else {
            mQIndex = 0;
        }
    }

    public int getQIndex() {
        return mQIndex;
    }

    public void updateScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public boolean nextIsEnd() {
        if (mQIndex + 1 == size()) {
            return true;
        } else {
            return false;
        }
    }
}
