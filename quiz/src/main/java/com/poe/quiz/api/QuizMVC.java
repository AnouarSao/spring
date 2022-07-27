package com.poe.quiz.api;

import com.poe.quiz.business.Player;
import com.poe.quiz.business.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuizMVC {

    @Autowired
    QuizService quizService;

    @GetMapping("/quiz")
    public String quiz(Model model){
        model.addAttribute("question", quizService.findAllQuestions());
        return "quiz.html";
    }

    @GetMapping("/players")
    public String read(Model model){
        model.addAttribute("player", quizService.findAllPlayers());
        return "player.html";
    }

    @PostMapping("/players")
    public String create(Player newPlayer, Model model) {
        quizService.createPlayer(newPlayer);
        model.addAttribute("player", quizService.findAllPlayers());
        return "player.html";
    }

    @GetMapping("/ranks")
    public String ranking(Model model){
        model.addAttribute("player", quizService.findAllPlayerByScoreDesc());
        return "rank.html";
    }
}
