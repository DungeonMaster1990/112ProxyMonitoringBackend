package Monitoring.Monitoring.services.workers;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.repositories.interfaces.SmRepository;
import org.modelmapper.ModelMapper;

import javax.transaction.NotSupportedException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BaseSmWorker <T>{

    private AppConfig appConfig;
    private SmRepository<T> repository;
    private ModelMapper modelMapper;

    public BaseSmWorker(AppConfig appConfig, SmRepository<T> repository, ModelMapper modelMapper){
        this.appConfig = appConfig;
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    protected List<T> loadData() throws NotSupportedException {
        throw new NotSupportedException();
    }

    <S, T> List<T> mapArray(S[] source, Class<T> targetClass) {
        return Arrays.stream(source)
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
    <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
