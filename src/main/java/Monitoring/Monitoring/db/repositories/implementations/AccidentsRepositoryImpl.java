//package Monitoring.Monitoring.db.repositories.implementations;
//
//import Monitoring.Monitoring.db.models.Accident;
//import Monitoring.Monitoring.db.repositories.interfaces.AccidentsRepository;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class AccidentsRepositoryImpl implements AccidentsRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public List<Accident> getAccidents(){
//        return entityManager.createQuery("from Accidents a order by id", Accident.class)
//                .getResultList();
//    }
//
//    @Override
//    public <S extends Accident> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends Accident> Iterable<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<Accident> findById(Long aLong) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return false;
//    }
//
//    @Override
//    public Iterable<Accident> findAll() {
//        return null;
//    }
//
//    @Override
//    public Iterable<Accident> findAllById(Iterable<Long> longs) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//
//    }
//
//    @Override
//    public void delete(Accident entity) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Accident> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//}
