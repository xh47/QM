package compsci290.edu.duke.qm;

import android.graphics.drawable.Drawable;

import static java.sql.Types.NULL;

/**
 * Created by Ian Hua on 2/18/2018.
 */

public class Answer {
    String mName;
    Boolean mCorrect;
    int mPhotoId;

    public Answer(String name, Boolean correct) {
        mName = name;
        mCorrect = correct;
    }

    public Answer(String name, Boolean correct, int photoID) { //change for photoURL?
        mName = name;
        mCorrect = correct;
        mPhotoId = photoID;
    }

    public String getText() {
        return mName;
    }

    public Boolean isCorrect() {
        return mCorrect;
    }

    public int getPhotoID() {
        return mPhotoId;
    }

}
