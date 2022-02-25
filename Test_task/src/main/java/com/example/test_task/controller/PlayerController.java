package com.example.test_task.controller;

import com.example.test_task.entity.Player;
import com.example.test_task.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PlayerController {

    PlayerRepository playerRepository;

    @GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Player>> getAllPlayers(){
        return new ResponseEntity(playerRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(
            value = "/new_player",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Player> createPlayer(@RequestBody Player player){
        Player newPlayer = playerRepository.save(new Player(
                player.getName(),
                player.getSurname(),
                player.getAge(),
                player.getExperience()));
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @GetMapping(value = "/players/{player_Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player>  getPlayerById(@PathVariable(value = "player_Id") Integer id){
        Player player = playerRepository.findById(id).orElseThrow();
        if (player == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(player,HttpStatus.OK);
    }

    @DeleteMapping(value = "/players/{player_Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player>  deletePlayerById(@PathVariable(value = "player_Id") Integer id){
        Player player = playerRepository.findById(id).orElseThrow();
        if (player == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerRepository.delete(player);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
