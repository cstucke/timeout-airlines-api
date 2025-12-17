package com.epita.timeout_airline.service;

import com.epita.timeout_airline.model.Client;
import com.epita.timeout_airline.model.Flight;
import com.epita.timeout_airline.model.MilesReward;
import com.epita.timeout_airline.repository.ClientRepository;
import com.epita.timeout_airline.repository.FlightRepository;
import com.epita.timeout_airline.repository.MilesRewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MilesRewardService {

    private final MilesRewardRepository milesRewardRepository;
    private final ClientRepository clientRepository;
    private final FlightRepository flightRepository;

    @Transactional
    public MilesReward createMilesReward(Long clientId, Long flightId, String discountCode) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + flightId));

        MilesReward reward = new MilesReward();
        reward.setClient(client);
        reward.setFlight(flight);
        reward.setDate(LocalDate.now());
        reward.setDiscountCode(discountCode);

        return milesRewardRepository.save(reward);
    }

    @Transactional(readOnly = true)
    public Optional<MilesReward> findById(Long id) {
        return milesRewardRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<MilesReward> findAll() {
        return milesRewardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<MilesReward> findByClientId(Long clientId) {
        return milesRewardRepository.findAll()
                .stream()
                .filter(r -> r.getClient().getId().equals(clientId))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MilesReward> findByFlightId(Long flightId) {
        return milesRewardRepository.findAll()
                .stream()
                .filter(r -> r.getFlight().getId().equals(flightId))
                .collect(Collectors.toList());
    }

    @Transactional
    public MilesReward updateDiscountCode(Long id, String discountCode) {
        MilesReward reward = milesRewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MilesReward not found with id: " + id));

        reward.setDiscountCode(discountCode);
        return milesRewardRepository.save(reward);
    }

    @Transactional
    public void deleteMilesReward(Long id) {
        if (!milesRewardRepository.existsById(id)) {
            throw new RuntimeException("MilesReward not found with id: " + id);
        }
        milesRewardRepository.deleteById(id);
    }

    @Transactional
    public void deleteByClientId(Long clientId) {
        List<MilesReward> rewards = findByClientId(clientId);
        milesRewardRepository.deleteAll(rewards);
    }

    @Transactional
    public void deleteByFlightId(Long flightId) {
        List<MilesReward> rewards = findByFlightId(flightId);
        milesRewardRepository.deleteAll(rewards);
    }
}