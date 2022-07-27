package com.poe.quiz.api;

import com.poe.quiz.api.dto.AnswerDTO;
import com.poe.quiz.api.dto.AnswerMapper;
import com.poe.quiz.business.Answer;
import com.poe.quiz.business.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class AnswerController {

    @Autowired
    QuizService quizService;

    // CREATE
    @PostMapping("answers")
    public void create(@RequestBody AnswerDTO dto){
        Answer answer = AnswerMapper.convertToEntity(dto);
        quizService.createAnswer(answer);
    }
//    public void create(@RequestBody Answer answer){
//        quizService.createAnswer(answer);
//    }

    //READ
    @GetMapping("answers")
//    public List<AnswerDTO> findAll(){
//        List<AnswerDTO> a = new ArrayList<>();
//        for (Answer answer : quizService.findAllAnswers()) {
//            AnswerDTO questionDTO = AnswerMapper.convertToDto(answer);
//            a.add(questionDTO);
//        }
//        return a;
//    }

    // WITH HASHMAP IN CLASS ANSWERMAPPER
    public List<HashMap<String, Object> > findAll(){
        List<HashMap<String, Object> > h = new ArrayList<>();
        for (Answer answer : quizService.findAllAnswers()) {
            AnswerMapper answerMapper = new AnswerMapper();
            HashMap<String, Object>  a = new HashMap<>();
            a = answerMapper.convertToAnswerMap(answer);
            h.add(a);
        }
        return h;
    }

//    public List<Answer> findAll(){
//        return quizService.findAllAnswers();
//    }

    @GetMapping("answers/{id}")
    public ResponseEntity<AnswerDTO> findById(@PathVariable("id") Long id){
        Optional<Answer> oa= quizService.findAnswerById(id);
        if(oa.isPresent()){
            Answer answer = oa.get();
            AnswerDTO answerDTO= AnswerMapper.convertToDto(answer);
            return ResponseEntity.status(HttpStatus.OK).body(answerDTO);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
//    public ResponseEntity<Answer> findById(@PathVariable("id") Long id){
//        Optional<Answer> oa= quizService.findAnswerById(id);
//        if(oa.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(oa.get());
//        }else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }

    // UPDATE
    @PutMapping("answers/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody AnswerDTO dto) {
        Answer answer = AnswerMapper.convertToEntity(dto);
        if (!id.equals(answer.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'identifiant dans URL ne correspond pas à l'identifiant dans Body");
        } else {
            if (quizService.updateAnswer(answer)) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'identifiant ne correspond à aucune Réponse");
            }
        }
    }

    // DELETE
    @DeleteMapping("answers/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        boolean hasDeleted = quizService.deleteAnswer(id);
        if(hasDeleted){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'identifiant ne correspond à aucune Réponse");
        }
    }
}
