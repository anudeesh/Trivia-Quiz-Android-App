package com.example.anudeesh.hw04;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anudeesh on 9/22/2016.
 */
public class QuestionUtil {
    static public class QuestionJSONparser {
        static ArrayList<Question> questionsList;
        public static ArrayList<Question> parseQuestions(String in) throws JSONException {
            questionsList = new ArrayList<Question>();
            JSONObject root = new JSONObject(in);
            JSONArray quesArray = root.getJSONArray("questions");

            for(int i=0; i<quesArray.length();i++) {
                JSONObject quesJSONObject = quesArray.getJSONObject(i);
                JSONObject optionsJSONObj = quesJSONObject.getJSONObject("choices");
                JSONArray optionsArray = optionsJSONObj.getJSONArray("choice");

                ArrayList<String> optionsList = new ArrayList<String>();
                //Question ques = Question.createPerson(personJSONObject);
                //personsList.add(person);

                if (optionsArray != null) {
                    for (int j=0;j<optionsArray.length();j++){
                        optionsList.add(optionsArray.get(j).toString());
                    }
                }
                if(quesJSONObject.has("image"))
                {
                    Question newQuestion = new Question(quesJSONObject.getString("text"),quesJSONObject.getString("image"),quesJSONObject.getString("id"),optionsJSONObj.getString("answer"),optionsList);
                    questionsList.add(newQuestion);
                }
                else {
                    Question newQuestion = new Question(quesJSONObject.getString("text"),"",quesJSONObject.getString("id"), optionsJSONObj.getString("answer"),optionsList);
                    questionsList.add(newQuestion);
                }
            }
            return questionsList;
        }
    }
}
