package com.epita.timeout_airline.controller;

import com.epita.timeout_airline.model.MilesReward;
import com.epita.timeout_airline.service.MilesRewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/miles-rewards")
@RequiredArgsConstructor
public class MilesRewardController {

    private final MilesRewardService milesRewardService;

    @PostMapping
    public ResponseEntity<MilesReward> createMilesReward(@RequestBody java.util.Map<String, Object> payload) {
        Long clientId = Long.valueOf(payload.get("clientId").toString());
        Long flightId = Long.valueOf(payload.get("flightId").toString());
        String discountCode = payload.get("discountCode") != null ?
                (String) payload.get("discountCode") : null;

        MilesReward reward = milesRewardService.createMilesReward(clientId, flightId, discountCode);
        return ResponseEntity.ok(reward);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MilesReward> getMilesReward(@PathVariable Long id) {
        return milesRewardService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MilesReward>> getAllMilesRewards() {
        return ResponseEntity.ok(milesRewardService.findAll());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<MilesReward>> getRewardsByClient(@PathVariable Long clientId) {
        List<MilesReward> rewards = milesRewardService.findByClientId(clientId);
        return ResponseEntity.ok(rewards);
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<MilesReward>> getRewardsByFlight(@PathVariable Long flightId) {
        List<MilesReward> rewards = milesRewardService.findByFlightId(flightId);
        return ResponseEntity.ok(rewards);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MilesReward> updateMilesReward(
            @PathVariable Long id,
            @RequestBody java.util.Map<String, Object> payload) {

        String discountCode = payload.get("discountCode") != null ?
                (String) payload.get("discountCode") : null;

        MilesReward updated = milesRewardService.updateDiscountCode(id, discountCode);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilesReward(@PathVariable Long id) {
        milesRewardService.deleteMilesReward(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/client/{clientId}")
    public ResponseEntity<Void> deleteRewardsByClient(@PathVariable Long clientId) {
        milesRewardService.deleteByClientId(clientId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/flight/{flightId}")
    public ResponseEntity<Void> deleteRewardsByFlight(@PathVariable Long flightId) {
        milesRewardService.deleteByFlightId(flightId);
        return ResponseEntity.noContent().build();
    }
}