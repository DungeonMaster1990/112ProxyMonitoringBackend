package Monitoring.Monitoring.services.workers;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.models.BaseSmModel;
import Monitoring.Monitoring.db.models.Updates;
import Monitoring.Monitoring.db.repositories.interfaces.SmRepository;
import Monitoring.Monitoring.db.repositories.interfaces.UpdatesRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.mainmodels.VmBaseSmModel;
import Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers.VmModelWrapper;
import Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers.VmResponseWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.transaction.NotSupportedException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseSmWorker <T extends VmBaseSmModel<T>, U extends BaseSmModel<U>> {

    private AppConfig appConfig;
    private SmRepository<U> repository;
    private UpdatesRepository updatesRepository;
    private ModelMapper modelMapper;

    public BaseSmWorker(AppConfig appConfig, SmRepository<U> repository, ModelMapper modelMapper,UpdatesRepository updatesRepository){
        this.appConfig = appConfig;
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.updatesRepository = updatesRepository;
    }

    public abstract Class<VmResponseWrapper<T>> getVmModelWrapperType();
    public abstract Class<U> getDbModelClassType();
    public abstract String getWorkerName();

    protected List<T> loadData() throws NotSupportedException {
        RestTemplate restTemplate = new RestTemplate();
        String workerName = getWorkerName();
        Updates update = updatesRepository.getUpdateEntityByServiceName(workerName);
        ResponseEntity<VmResponseWrapper<T>> result = restTemplate.postForEntity(appConfig.getPusherUrl(), getRequestString(update.getUpdateTime()), getVmModelWrapperType());

        List<T> resultBody = Arrays.stream(result.getBody().getContent())
                .map(VmModelWrapper::getModel)
                .collect(Collectors.toList());

        List<U> models = mapList(resultBody, getDbModelClassType());

        ZonedDateTime updatedAt = models.stream()
                .max(Comparator.comparing(U::getUpdatedAt))
                .get()
                .getUpdatedAt();

        update.setUpdateTime(updatedAt);
        repository.putModels(models);
        updatesRepository.putUpdate(update);


        throw new NotSupportedException();
    }
    public abstract String getRequestString(ZonedDateTime startTime);

    <SS, TT> List<TT> mapArray(SS[] source, Class<TT> targetClass) {
        return Arrays.stream(source)
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
    <SS, TT> List<TT> mapList(List<SS> source, Class<TT> targetClass) {
        return source.stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
