package ru.vtb.monitoring.vtb112.db.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vtb.monitoring.vtb112.db.models.PushTokens;

public interface PushTokenRepository extends JpaRepository<PushTokens, Integer> {

}
