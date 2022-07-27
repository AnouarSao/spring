package com.poe.quiz;

import com.poe.quiz.business.Question;
import com.poe.quiz.business.service.QuizService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuizApplicationTests {

	@Autowired
	QuizService quizService;

	@Test
	void contextLoads() {
		Question question = new Question();
		int size = quizService.findAllQuestions().size();
		question.setTitle("Comment t'appelles-tu ?");
		quizService.createQuestion(question);

		Assertions.assertEquals(size + 1, quizService.findAllQuestions().size());
	}
}
