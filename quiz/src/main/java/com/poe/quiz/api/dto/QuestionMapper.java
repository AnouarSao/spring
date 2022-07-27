package com.poe.quiz.api.dto;

import com.poe.quiz.business.Question;

public class QuestionMapper {

    public static QuestionDTO convertToDto(Question question){
        QuestionDTO dto = new QuestionDTO();

        dto.setId(question.getId());
        dto.setTitle(question.getTitle());
        dto.setAnswers(question.getAnswers());

        return dto;
    }

    public static Question convertToEntity( QuestionDTO dto){
        Question question = new Question();

        question.setId(dto.getId());
        question.setTitle(dto.getTitle());
        question.setAnswers(dto.getAnswers());

        return question;
    }
}
