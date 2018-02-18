package compsci290.edu.duke.qm;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpcox
 * Edited by Ian
 */

public class XMLQuizParserCox {

    // We don't use namespaces
    private static final String ns = null;

    public Quiz parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readQuiz(parser);
        } finally {
            in.close();
        }
    }

    private Quiz readQuiz(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Question> questionList = new ArrayList<Question>();
        String title = "";
        parser.require(XmlPullParser.START_TAG, ns, "quiz");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals("title")) {
                title = readTitle(parser);
            } else if (name.equals("question")) {
                questionList.add(readQuestion(parser));
            } else {
                skip(parser);
            }
        }
        Quiz temp = new Quiz(title, questionList);
        Log.d("XMLREADQUIZ", temp.getTitle());
        return temp;
    }

    private Question readQuestion(XmlPullParser parser) throws IOException, XmlPullParserException {
        String query = "";
        String type = "";
        String ans = "";
        List<Answer> answerList = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "question");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals("text")) {
                query = readText(parser);
//            } else if (name.equals("type")) {
//                type = readText(parser);
            } else if (name.equals("correctanswer")) {
                ans = readText(parser);
                answerList.add(new Answer(ans, true));
            } else if (name.equals("wronganswer")) {
                ans = readText(parser);
                answerList.add(new Answer(ans, false));
            } else {
                skip(parser);
            }
        }
        return new Question(query, answerList, type);
    }

    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "title");
        return title;
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        System.out.println(result);
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}