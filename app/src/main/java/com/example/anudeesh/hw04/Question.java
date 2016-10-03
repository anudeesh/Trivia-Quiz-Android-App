package com.example.anudeesh.hw04;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Anudeesh on 9/22/2016.
 */
public class Question implements Parcelable {
    String questionText, questionURL, questionID, answer;
    ArrayList options = new ArrayList<String>();

    /*static public Question createQuestion(JSONObject jsonObject) throws JSONException {
        Question question = new Question();
        question.setAnswer(jsonObject.getString("text"));
        return question;
    }
   */

    public Question(String questionText, String questionURL, String questionID, String answer, ArrayList<String> options) {
        this.questionText = questionText;
        this.questionURL = questionURL;
        this.questionID = questionID;
        this.answer = answer;
        this.options = options;
    }

    protected Question(Parcel in) {
        questionText = in.readString();
        questionURL = in.readString();
        questionID = in.readString();
        answer = in.readString();
        options = in.createStringArrayList();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionURL() {
        return questionURL;
    }

    public void setQuestionURL(String questionURL) {
        this.questionURL = questionURL;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", questionURL='" + questionURL + '\'' +
                ", questionID='" + questionID + '\'' +
                ", answer='" + answer + '\'' +
                ", options=" + options +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionText);
        dest.writeString(questionURL);
        dest.writeString(questionID);
        dest.writeString(answer);
        dest.writeStringList(options);
    }
}
