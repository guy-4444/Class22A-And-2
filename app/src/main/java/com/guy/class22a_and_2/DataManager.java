package com.guy.class22a_and_2;

import java.util.ArrayList;
import java.util.Arrays;

public class DataManager {

    public static ArrayList<Question> generateQuestions() {
        ArrayList<Question> questions = new ArrayList<>();


        questions.add(new Question()
                .setImage("https://upload.wikimedia.org/wikipedia/commons/6/6a/Bouquetin_adulte_couch%C3%A9.jpg")
                .setAnswers(Arrays.asList("Capra", "Cat", "Horse", "Fish"))
        );

        questions.add(new Question()
                .setImage("https://upload.wikimedia.org/wikipedia/commons/2/2e/MC_Drei-Finger-Faultier.jpg")
                .setAnswers( Arrays.asList("Sloth", "Fox", "Lion", "Tiger"))
        );

        questions.add(new Question()
                .setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/A_chital_stag_1.JPG/1280px-A_chital_stag_1.JPG")
                .setAnswers( Arrays.asList("Bambi", "Bear", "Monkey", "Gorilla"))
        );

        return questions;
    }

}
