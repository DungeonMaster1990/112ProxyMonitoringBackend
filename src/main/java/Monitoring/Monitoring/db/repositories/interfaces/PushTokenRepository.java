package Monitoring.Monitoring.db.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import Monitoring.Monitoring.db.models.PushToken;

public interface PushTokenRepository extends JpaRepository<PushToken, Integer> {

}
