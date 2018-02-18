package compsci290.edu.duke.qm;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ian Hua on 2/18/2018.
 */

public class XMLQuizGenerator {

    private static Map<String, Quiz> ourQuizzes;
    private static final String sDEFAULT = "Basketball Quiz";
    private static String sQUIZDIR = "quizzes";

    static {
        ourQuizzes = new HashMap<>();
    }

    private static void createQuiz(Context context, String quizfile) {
        try {
            InputStream is = context.getAssets().open(sQUIZDIR + "/" + quizfile);
            //int length = is.available();
            //byte[] data = new byte[length];
            //is.read(data);
            Quiz q = (new XMLQuizParser().parse(is));
            ourQuizzes.put(q.getTitle(), q);
            Log.d("quizmaster", "XMLQG.createQuiz added " + q.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createQuiz(Context context, String quizfile, String lpcox) {
        try {
            InputStream is = context.getAssets().open("lpcox.quizzes/" + quizfile);
            //int length = is.available();
            //byte[] data = new byte[length];
            //is.read(data);
            Quiz q = (new XMLQuizParserCox().parse(is));
            ourQuizzes.put(q.getTitle(), q);
            Log.d("quizmaster", "XMLQG.createQuiz added " + q.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Read in quizzes from the asset directory and create quizzes for each file found
     *
     * @param context
     */
    public static void createQuizzes(Context context) {
        Log.d("QUIZGEN", "createQuizzes reached");
        try {
            String[] quizfiles = context.getResources().getAssets().list(sQUIZDIR);
            for (String quizfile : quizfiles) {
                Log.d("quizmaster", "XMLQG.createQuizzes" + quizfile);
                createQuiz(context, quizfile);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void createQuizzes(Context context, String lpcox) {
        Log.d("QUIZGEN", "createQuizzes reached");
        try {
            String[] quizfiles = context.getResources().getAssets().list("lpcox.quizzes");
            for (String quizfile : quizfiles) {
                Log.d("quizmaster", "XMLQG.createQuizzes" + quizfile);
                createQuiz(context, quizfile, "lpcox");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * returns a quiz that matches input title
     *
     * @param s title of the quiz
     * @return a quiz matching the title (if found), null if no match
     */
    public static Quiz getQuiz(String s) {
        Log.d("quizmaster", "XMLQG.getQuiz requested " + s);
        Quiz q = ourQuizzes.get(s);
        Log.d("quizmaster", "XMLQG.getQuiz found " + q);
        return q;
    }

    /**
     * returns the default quiz
     *
     * @return the default quiz
     */
    public static Quiz getQuiz() {
        return getQuiz(sDEFAULT);
    }

    public static String getDefaultTitle() {
        return sDEFAULT;
    }

    /**
     * returns quiz titles
     *
     * @return array of quiz titles
     */
    public static String[] getQuizTitles() {
        Set<String> titles = ourQuizzes.keySet();
        return titles.toArray(new String[0]);
    }

    private XMLQuizGenerator() {
    }
}
