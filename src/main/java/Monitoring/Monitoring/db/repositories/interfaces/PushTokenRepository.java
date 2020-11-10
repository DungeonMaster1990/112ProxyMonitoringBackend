package Monitoring.Monitoring.db.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import Monitoring.Monitoring.db.models.PushTokens;

public interface PushTokenRepository extends JpaRepository<PushTokens, Integer> {

}
