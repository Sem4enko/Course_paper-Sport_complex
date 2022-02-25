package com.example.test_task.controller;

import com.example.test_task.entity.Team;
import com.example.test_task.repository.TeamRepository;
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
public class TeamController {

    TeamRepository teamRepository;


    @GetMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Team>> getAllTeam() {
        return new ResponseEntity(teamRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(
            value = "/new_team",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team newTeam = teamRepository.save(new Team(
                team.getName(),
                team.getCountry(),
                team.getCapital()));
        return new ResponseEntity<>(newTeam, HttpStatus.CREATED);

    }

    @GetMapping(value = "/teams/{team_Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> getTeamById(@PathVariable(value = "team_Id") Integer id) {
        Team team = teamRepository.findById(id).orElseThrow();
        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(team, HttpStatus.OK);
    }

    @DeleteMapping(value = "/teams/{team_Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> deleteTeamById(@PathVariable(value = "team_Id") Integer id) {
        Team team = teamRepository.findById(id).orElseThrow();
        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        teamRepository.delete(team);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
