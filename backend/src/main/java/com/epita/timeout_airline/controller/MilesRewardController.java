package com.epita.timeout_airline.controller;

import com.epita.timeout_airline.model.MilesReward;
import com.epita.timeout_airline.repository.MilesRewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/miles-rewards")
@RequiredArgsConstructor
public class MilesRewardController {

    private final MilesRewardRepository milesRewardRepository;

    @GetMapping
    public ResponseEntity<List<MilesReward>> getAllMilesRewards() {
        return ResponseEntity.ok(milesRewardRepository.findAll());
    }

    // Optional: get rewards for a specific client
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<MilesReward>> getRewardsByClient(@PathVariable Long clientId) {
        List<MilesReward> rewards = milesRewardRepository.findAll()
                .stream()
                .filter(r -> r.getClient().getId().equals(clientId))
                .toList();
        return ResponseEntity.ok(rewards);
    }
}