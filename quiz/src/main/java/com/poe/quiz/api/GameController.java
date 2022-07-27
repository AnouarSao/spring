package com.poe.quiz.api;

import com.poe.quiz.business.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class GameController {
    @Autowired
    QuizService quizService;

    @PostMapping("questions/{questionId}/play/{answerId}")
    public Boolean validateAnswer(@PathVariable("questionId") Long questionId, @PathVariable("answerId") Long answerId){
        return quizService.validateAnswer(questionId, answerId);
    }
}
