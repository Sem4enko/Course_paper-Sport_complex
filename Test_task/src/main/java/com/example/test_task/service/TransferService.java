package com.example.test_task.service;

import com.example.test_task.entity.Player;
import com.example.test_task.entity.Team;
import com.example.test_task.repository.PlayerRepository;
import com.example.test_task.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferService {
    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;


    private Integer id;
    private Integer idTeam;


    public Team findNewTeam(Integer id) {
        Team newTeam = teamRepository.findById(idTeam).orElseThrow();
        return newTeam;
    }

    public Integer priceForPlayer() {
        Player player = playerRepository.findById(id).orElseThrow();
        if (player == null) {
            player.setTeam(findNewTeam(idTeam));
        }
        Integer playerPrice = player.getExperience() * 12 * 100000 / player.getAge();
        return playerPrice;
    }

    public Double teamCommission() {
        Team playersTeam = playerRepository.findById(id).get().getTeam();
        Double commission = (double) priceForPlayer() / 100 * playersTeam.getId();
        return commission;
    }


    public void fullPrice() {
        Team newTeam = findNewTeam(idTeam);
        Team playersTeam = playerRepository.findById(id).get().getTeam();
        Double fullPrice = priceForPlayer() + teamCommission();
        newTeam.setCapital(newTeam.getCapital() - fullPrice);
        playersTeam.setCapital(playersTeam.getCapital() + fullPrice);
    }
}
