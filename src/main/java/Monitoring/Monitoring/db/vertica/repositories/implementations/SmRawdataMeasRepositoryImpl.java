package Monitoring.Monitoring.db.vertica.repositories.implementations;

import Monitoring.Monitoring.db.vertica.models.SmRawdataMeasVertica;
import Monitoring.Monitoring.db.vertica.repositories.interfaces.SmRawdataMeasRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SmRawdataMeasRepositoryImpl implements SmRawdataMeasRepository {

    @Override
    public void putSmRawdataMeasVertica(List<SmRawdataMeasVertica> smRawdataMeasVerticas) {

    }
}
