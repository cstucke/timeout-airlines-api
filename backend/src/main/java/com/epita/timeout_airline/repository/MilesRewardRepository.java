package com.epita.timeout_airline.repository;

import com.epita.timeout_airline.model.MilesReward;
import com.epita.timeout_airline.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface MilesRewardRepository extends JpaRepository<MilesReward, Long> {
    long countByClientAndDateBetween(Client client, LocalDate start, LocalDate end);
}
