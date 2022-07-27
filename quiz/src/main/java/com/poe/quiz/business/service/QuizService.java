package com.poe.quiz.business.service;

import com.poe.quiz.business.Answer;
import com.poe.quiz.business.Player;
import com.poe.quiz.business.Question;
import com.poe.quiz.dao.AnswerRepository;
import com.poe.quiz.dao.PlayerRepository;
import com.poe.quiz.dao.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    /**********************************/
    /************** GAME **************/
    /**********************************/
    public boolean validateAnswer(Long questionId, Long answerId) {

        Optional<Answer> answerOptional = answerRepository.findById(answerId);
        if(answerOptional.isPresent()){
            Answer answer = answerOptional.get();
            if(answer.getQuestion().getId().equals(questionId)){
                return answer.isCorrectAnswer();
            }
        }
        return false;
    }
//    public boolean validateScore(Long questionId, Long answerId, Long playerId) {
//        Optional<Answer> answerOptional = answerRepository.findById(answerId);
//        Optional<Player> playerOptional = playerRepository.findById(playerId);
//        if(answerOptional.isPresent()){
//            Answer answer = answerOptional.get();
//            if(answer.getQuestion().getId().equals(questionId)){
//                if(answer.isCorrectAnswer()){
//
//                }
//                return answer.isCorrectAnswer();
//            }
//        }
//        return false;
//    }

    /**********************************/
    /********** CRUD Question *********/
    /**********************************/
    @Autowired
    QuestionRepository questionRepository;

    // CREATE Question
    public void createQuestion(Question question) {
        for(Answer answer : question.getAnswers()){
            answer.setQuestion(question);
        }
        questionRepository.save(question);
    }
//    public void addQuestionAndAnswers(Question question){
//        questionRepository.save(question);
//        for( int i=0; i<question.getAnswers().size();i++){
//            question.getAnswers().get(i).setQuestion(question);
//        }
//        answerRepository.saveAll(question.getAnswers());
//    }


    // READ Question
    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> findQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    // UPDATE Question
    public boolean updateQuestion(Question question) {
        if(questionRepository.existsById(question.getId())){
            questionRepository.save(question);
            return true;
        }else {
            return false;
        }
    }

    // DELETE Question
    public boolean deleteQuestion(Long id){
        if(questionRepository.existsById(id)){
            questionRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }


    /********************************/
    /********** CRUD Answer *********/
    /********************************/

    @Autowired
    AnswerRepository answerRepository;

    // CREATE Answer
    public void createAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    // READ Answer
    public List<Answer> findAllAnswers() {
        return answerRepository.findAll();
    }

    public Optional<Answer> findAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    // UPDATE Answer
    public boolean updateAnswer(Answer answer) {
        if(answerRepository.existsById(answer.getId())){
            answerRepository.save(answer);
            return true;
        }else {
            return false;
        }
    }

    // DELETE Answer
    public boolean deleteAnswer(Long id){
        if(answerRepository.existsById(id)){
            answerRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }


    /********************************/
    /********** CRUD Player *********/
    /********************************/

    @Autowired
    PlayerRepository playerRepository;

    // CREATE Player
    public void createPlayer(Player player) {
        playerRepository.save(player);
    }

    // READ Player
    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> findPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public List<Player> findAllPlayerByScoreDesc(){
        return playerRepository.findAllByOrderByScoreDesc();
    }

    // UPDATE Player
    public boolean updatePlayer(Player player) {
        if(playerRepository.existsById(player.getId())){
            playerRepository.save(player);
            return true;
        }else {
            return false;
        }
    }

    // DELETE Player
    public boolean deletePlayer(Long id){
        if(playerRepository.existsById(id)){
            playerRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
