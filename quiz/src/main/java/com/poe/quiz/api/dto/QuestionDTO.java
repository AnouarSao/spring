package com.poe.quiz.api.dto;

import com.poe.quiz.business.Answer;

import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {

    private Long id;
    private String title;
    private List<Answer> answers =new ArrayList<>();

    public QuestionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
