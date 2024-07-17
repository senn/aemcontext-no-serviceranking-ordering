package com.github.senn.services;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component(service = MyCallingService.class, immediate = true)
public class MyCallingService {

    private static final Logger log = LoggerFactory.getLogger(MyCallingService.class);

    @Reference
    private volatile List<RankingService> rankingServices;

    public void printServices() {
        rankingServices
                .stream()
                .map(Object::toString)
                .forEach(log::warn);
    }

    public List<RankingService> getServices() {
        return rankingServices;
    }

}
