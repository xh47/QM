package compsci290.edu.duke.qm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian Hua on 2/18/2018.
 */

public class Question {

    private String mQuery;
    private List<Answer> mAnswers;
    private String mType; //images, text
    public Boolean attempted;

    public Question(String q, List<Answer> answers, String type) {
        mQuery = q;
        mAnswers = answers;
        mType = type;
        attempted = false;
    }

    public String getQuery() {
        return mQuery;
    }

    public String getType() {
        return mType;
    }

    public Boolean getAttempted() {
        return attempted;
    }

    public List<Answer> getChoices() {
        return mAnswers;
    }
}