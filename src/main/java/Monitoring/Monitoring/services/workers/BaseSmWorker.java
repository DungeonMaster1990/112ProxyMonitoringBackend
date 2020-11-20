package Monitoring.Monitoring.services.workers;

import Monitoring.Monitoring.config.AppConfig;
import Monitoring.Monitoring.db.models.BaseSmModel;
import Monitoring.Monitoring.db.models.Updates;
import Monitoring.Monitoring.db.repositories.interfaces.SmRepository;
import Monitoring.Monitoring.db.repositories.interfaces.UpdatesRepository;
import Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers.VmModelWrapper;
import Monitoring.Monitoring.dto.services.viewmodels.response.modelwrappers.VmBaseResponseWrapper;
import Monitoring.Monitoring.mappers.wrappers.SmMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.util.UriEncoder;

import javax.transaction.NotSupportedException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseSmWorker <T, TT extends VmBaseResponseWrapper<T>, U extends BaseSmModel> {

    private AppConfig appConfig;
    private SmRepository<U> repository;
    private UpdatesRepository updatesRepository;
    private SmMapper<U, T> smMapper;

    private Class<T> vmModelClassType;
    private Class<TT> vmModelWrapperType;
    private Class<U> dbModelClassType;
    private String workerName;
    private String url;

    public BaseSmWorker(
            AppConfig appConfig,
            SmRepository<U> repository,
            SmMapper<U, T> smMapper,
            UpdatesRepository updatesRepository,
            Class<T> vmModelClassType,
            Class<TT> vmModelWrapperType,
            Class<U> dbModelClassType,
            String workerName,
            String requestString){
        this.appConfig = appConfig;
        this.smMapper = smMapper;
        this.repository = repository;
        this.updatesRepository = updatesRepository;
        this.vmModelClassType = vmModelClassType;
        this.vmModelWrapperType = vmModelWrapperType;
        this.dbModelClassType = dbModelClassType;
        this.workerName = workerName;
        this.url = requestString;
    }

    protected void process() throws NotSupportedException {
        Updates update;
        ResponseEntity<TT> response;

        try {
            RestTemplate restTemplate = buildRestTemplate();
            update = updatesRepository.getUpdateEntityByServiceName(workerName);
            Map<String, Object> request = Map.of(
                    "view", "expand" ,
                    "query", getQueryString(update));
            response = restTemplate.getForEntity(url, vmModelWrapperType, request);
        }
        catch(Exception exception){
            log.error("Ошибка при передаче инцидента на сервис отправки уведомлений.", exception);
            return;
        }

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null || response.getBody().getReturnCode() > 0){
            log.error(String.format("The SM service: %s returns response: %s", workerName, response.toString()));;
            return;
        }

        List<T> resultBody = Arrays.stream(response.getBody().getContent())
                .map(VmModelWrapper::getModel)
                .collect(Collectors.toList());

            List<U> models = resultBody.stream()
                    .map(element -> {
                        try {
                            return smMapper.map(element, dbModelClassType, vmModelClassType);
                        } catch (NotSupportedException e) {
                            log.error(String.format("The Convert from %s to %s Is Not Supported", vmModelClassType.toString(), dbModelClassType.toString()));
                            return null;
                        }
                    })
                    .collect(Collectors.toList());

        models.stream()
                .max(Comparator.comparing(U::getUpdatedAt))
                .ifPresent(bm -> update.setUpdateTime(bm.getUpdatedAt()));

        repository.putModels(models);
        updatesRepository.putUpdate(update);
    }

    private RestTemplate buildRestTemplate(){
        String loginBasicEncoded = Base64.getEncoder().encodeToString(appConfig.getSmUserLoginPass().getBytes());

        RestTemplate restTemplate = new RestTemplateBuilder(rt -> rt.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Basic  " + loginBasicEncoded);
            return execution.execute(request, body);
        })).build();

        return restTemplate;
    }

    private String getQueryString(Updates update){
        String dateTimeString = update.getUpdateTime().toString();
        String queryString = String.format("UpdatedAt>'%s'", dateTimeString);
        String result = UriEncoder.encode(queryString);
        return result;
    }
}
