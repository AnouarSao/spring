package com.poe.quiz.api;
import com.poe.quiz.business.Player;
import com.poe.quiz.business.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class PlayerController {
    @Autowired
    QuizService quizService;

    // CREATE
    @PostMapping("players")
    public void create(@RequestBody Player player){
        quizService.createPlayer(player);
    }

    //READ
    @GetMapping("players")
    public List<Player> findAll(){
        return quizService.findAllPlayers();
    }

    @GetMapping("players/{id}")
    public ResponseEntity<Player> findById(@PathVariable("id") Long id){
        Optional<Player> op= quizService.findPlayerById(id);
        if(op.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(op.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // UPDATE
    @PutMapping("players/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Player player) {
        if (!id.equals(player.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("L'identifiant dans URL ne correspond pas à l'identifiant dans Body");
        } else {
            if (quizService.updatePlayer(player)) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'identifiant ne correspond à aucun Joueur");
            }
        }
    }

    // DELETE
    @DeleteMapping("players/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        boolean hasDeleted = quizService.deletePlayer(id);
        if(hasDeleted){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'identifiant ne correspond à aucun Joueur");
        }
    }
}
