package com.poe.quiz.api;

import com.poe.quiz.api.dto.QuestionDTO;
import com.poe.quiz.api.dto.QuestionMapper;
import com.poe.quiz.business.Question;
import com.poe.quiz.business.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class QuestionController {

    @Autowired
    QuizService quizService;

    // CREATE
    @PostMapping("questions")
    public void create(@RequestBody QuestionDTO dto){
        Question question = QuestionMapper.convertToEntity(dto);
        quizService.createQuestion(question);
    }
//    public void create(@RequestBody Question question){
//        quizService.createQuestion(question);
//    }

    //READ
    @GetMapping("questions")
    public List<QuestionDTO> findAll(){
        List<QuestionDTO> q = new ArrayList<>();
        for (Question question : quizService.findAllQuestions()) {
            QuestionDTO questionDTO = QuestionMapper.convertToDto(question);
            q.add(questionDTO);
        }
        return q;
    }
    // SANS DTO
//    public List<Question> findAll(){
//        return quizService.findAllQuestions();
//    }

    @GetMapping("questions/{id}")
    public ResponseEntity<QuestionDTO> findById(@PathVariable("id") Long id){
        Optional<Question> oq= quizService.findQuestionById(id);
        if(oq.isPresent()){
            Question question = oq.get();
            QuestionDTO questionDTO = QuestionMapper.convertToDto(question);
            return ResponseEntity.status(HttpStatus.OK).body(questionDTO);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
        // SANS DTO
//    public ResponseEntity<Question> findById(@PathVariable("id") Long id){
//        Optional<Question> oq= quizService.findQuestionById(id);
//        if(oq.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(oq.get());
//        }else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }

    // UPDATE
    @PutMapping("questions/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody QuestionDTO dto) {
        Question question = QuestionMapper.convertToEntity(dto);
        if (!id.equals(question.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'identifiant dans URL ne correspond pas à l'identifiant dans Body");
        } else {
            if (quizService.updateQuestion(question)) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'identifiant ne correspond à aucune Question");
            }
        }
    }

    // DELETE
    @DeleteMapping("questions/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        boolean hasDeleted = quizService.deleteQuestion(id);
        if(hasDeleted){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'identifiant ne correspond à aucune Question");
        }
    }
}
