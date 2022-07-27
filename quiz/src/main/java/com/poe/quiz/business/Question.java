package com.poe.quiz.business;

import com.poe.quiz.api.dto.AnswerDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(mappedBy = "question", cascade = {CascadeType.ALL})
    private List<Answer> answers =new ArrayList<>();

    public Question() {
    }

    public Question(String title) {
        this.title = title;
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

    public void addAnswer(Answer answer){
        answer.setQuestion(this);
        answers.add(answer);
    }


    // varargs:
    // Méthode qui prend un nombre variable de paramètres
    // On recupere les parametres dans un tableau
    // exemple appel : question.addAnswers(a1, a2, a3);
//    public void addAnswers(Answer... answers){
//        System.out.println(answers.length);
//        for (int i = 0; i < answers.length; i++) {
//            System.out.println(answers[i]);
//            this.answers.add(answers[i]);
//        }
//    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", answers=" + answers +
                '}';
    }

}
