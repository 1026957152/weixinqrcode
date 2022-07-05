package com.coalvalue.configuration;


import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;
import io.grpc.services.HealthStatusManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Stream;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent>, Ordered {
    private Logger logger = LoggerFactory.getLogger(ApplicationReadyEventListener.class);
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    @Autowired
    private HealthStatusManager healthStatusManager;

    @Autowired
    private AbstractApplicationContext applicationContext;



    private <T> Stream<String> getBeanNamesByTypeWithAnnotation(Class<? extends Annotation> annotationType, Class<T> beanType) throws Exception {

        return Stream.of(applicationContext.getBeanNamesForType(beanType))
                .filter(name -> {
                    final BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition(name);
                    final Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(annotationType);

                    if (beansWithAnnotation.containsKey(name)) {
                        return true;
                    } else if (beanDefinition.getSource() instanceof AnnotatedTypeMetadata) {
                        return AnnotatedTypeMetadata.class.cast(beanDefinition.getSource()).isAnnotated(annotationType.getName());

                    }

                    return false;
                });
    }
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        logger.debug("begin --------------------------------------------- register service, to master");
        System.out.println("healthStatusManager.SERVICE_NAME_ALL_SERVICES"+healthStatusManager.SERVICE_NAME_ALL_SERVICES);

        healthStatusManager.getHealthService();
        healthStatusManager.toString();

/*
        try {
            getBeanNamesByTypeWithAnnotation(GRpcService.class, BindableService.class)
            .forEach(name -> {
                BindableService srv = applicationContext.getBeanFactory().getBean(name, BindableService.class);
                ServerServiceDefinition serviceDefinition = srv.bindService();
                GRpcService gRpcServiceAnn = applicationContext.findAnnotationOnBean(name, GRpcService.class);

                String serviceName = serviceDefinition.getServiceDescriptor().getName();

                System.out.println(serviceName+"'{}' service has been registered."+ srv.getClass().getName());

            });
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*

        Map map = new HashMap<>();
        map.put("id", ServiceTypeEnum.MESSAGE_TEMPLATE.getText());
        map.put("Body","I like pie");
        Map date = new HashMap<>();
        date.put("name", ServiceTypeEnum.MESSAGE_TEMPLATE.getDisplayText());

        map.put("data",date);

        String jsonObject = JSON.toJSONString(map);

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    JSONObject result = TulingApiProcess.getTulingResult(serviceUrl,"");

                    String apiUrl = result.getString("register_url");
                    TulingApiProcess.getTulingResultPostStateEvent(apiUrl, null,jsonObject);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
*/

        logger.debug("end --------------------------------------------- register service, to master");

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }


}