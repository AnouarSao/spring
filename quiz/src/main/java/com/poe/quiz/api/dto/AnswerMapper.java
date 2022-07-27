package com.poe.quiz.api.dto;

import com.poe.quiz.business.Answer;

import java.util.HashMap;

public class AnswerMapper {

    public static  AnswerDTO convertToDto(Answer answer){
        AnswerDTO dto = new AnswerDTO();

        dto.setId(answer.getId());
        dto.setTitle(answer.getTitle());
        dto.setCorrectAnswer(answer.isCorrectAnswer());
        dto.setQuestion(answer.getQuestion());

        return dto;
    }

    public HashMap<String, Object> convertToAnswerMap(Answer answer){
        HashMap<String, Object> commeTuVeux = new HashMap<>();

        commeTuVeux.put("id", answer.getId());
        commeTuVeux.put("title", answer.getTitle());
        commeTuVeux.put("isCorrectAnswer", answer.isCorrectAnswer());
        commeTuVeux.put("question",answer.getQuestion().getTitle());

        return commeTuVeux;
    }

    public static Answer convertToEntity(AnswerDTO dto){
        Answer answer = new Answer();

        answer.setId(dto.getId());
        answer.setTitle(dto.getTitle());
        answer.setCorrectAnswer(dto.isCorrectAnswer());
        answer.setQuestion(dto.getQuestion());

        return answer;
    }
}
