package Monitoring.Monitoring.db.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import Monitoring.Monitoring.db.models.PushTokens;
import org.springframework.stereotype.Repository;

@Repository
public interface PushTokenRepository {
    Boolean pushToken(PushTokens pushTokens);
}
