package compsci290.edu.duke.qm;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ian Hua on 2/18/2018.
 */

public class Quiz4Generator {
    private ArrayList<Quiz4Generator.Question> myQuestions;
    private int mIndex;

    public class Question {
        private String mQuery;
        private String mCorrect;
        private String [] mWrong = new String[3];
        private String [] answers = new String[4];


        public Question(String q, String right, String [] wrong) {

            mQuery = q;
            mCorrect = right;
            mWrong = wrong;
            answers = wrong;
            answers[3] = right;
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
        public String[] getAnswers() {
            return answers;
        }
    }

    public int size(){
        return myQuestions.size();
    }

    public Quiz4Generator.Question getQuestion(){
        if (mIndex < myQuestions.size()) {
            Quiz4Generator.Question q = myQuestions.get(mIndex);
            mIndex++;
            return q;
        }
        return null;
    }
    public Quiz4Generator.Question getQuestion(int index){
        if (0 <= index && index < myQuestions.size()){
            return myQuestions.get(index);
        }
        throw new IndexOutOfBoundsException("bad index "+index);
    }

    public Quiz4Generator(){
        myQuestions = new ArrayList<Quiz4Generator.Question>();
        makeQuiz();
    }

    private void makeQuiz() {
        String [] wrongs1 = {"The Imitation Game","The Circle","The Social Network", "The Grand Budapest Hotel"};
        String [] wrongs2 = {"6:08","4:51","7:32", "6:59"};
        String [] wrongs3 = {"Selena Gomez","Beyonce","Miley Cyrus","Adele"};
        String [] wrongs4 = {"North Carolina","California","New York", "Texas"};
        String [] wrongs5 = {"Migos", "Young Thug", "Childish Gambino", "Kanye West"};


        myQuestions.add(new Quiz4Generator.Question("What movie has Professor Astrachan’s brother produced?","Short Term 12 ",wrongs1));
        myQuestions.add(new Quiz4Generator.Question("What is Professor Astrachan’s mile time?", "5:29", wrongs2));
        myQuestions.add(new Quiz4Generator.Question("Which big name celebrity does Professor Astrachan subscribe to on Youtube?","Taylor Swift", wrongs3));
        myQuestions.add(new Quiz4Generator.Question("Where was Landon on 25 Nov 2008?","Michigan",wrongs4));
        myQuestions.add(new Quiz4Generator.Question("Which rapper does Landon follow on Twitter?","Chance the Rapper",wrongs5));
    }
}

