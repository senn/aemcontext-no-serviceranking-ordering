package com.github.senn.services;

import io.wcm.testing.mock.aem.junit5.AemContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InjectionTimeOrderingTest {

    public final AemContext context = new AemContext();

    @Test
    void testServiceOrderingBasedOnServiceRankingWhenRegisteredBeforeCallingComponent() {
        //these classes are registered in a "random" order - so not the same as one would expect them to be in the injected collection
        RankingService ranking0Svc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", 0));
        RankingService integerMaxSvc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", Integer.MAX_VALUE));
        RankingService negativeRankingSvc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", -20));

        MyCallingService svc = context.registerInjectActivateService(new MyCallingService());
        final List<RankingService> rankingServices = svc.getServices();
        assertEquals(3, rankingServices.size());
        assertEquals(negativeRankingSvc, rankingServices.get(0));
        assertEquals(ranking0Svc, rankingServices.get(1));
        assertEquals(integerMaxSvc, rankingServices.get(2));
    }

    @Test
    void testServiceOrderingBasedOnServiceRankingWhenRegisteredAfterCallingComponent() {
        //register the calling component first
        MyCallingService svc = context.registerInjectActivateService(new MyCallingService());

        //these classes are registered in a "random" order - so not the same as one would expect them to be in the injected collection
        RankingService ranking0Svc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", 0));
        RankingService integerMaxSvc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", Integer.MAX_VALUE));
        RankingService negativeRankingSvc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", -20));

        final List<RankingService> rankingServices = svc.getServices();
        //make sure the 3 services are injected
        assertEquals(3, rankingServices.size());

        //the following does not work because when injected AFTER the calling component, OSGi mocks just 'add' the component to the collection
        assertEquals(negativeRankingSvc, rankingServices.get(0));
        assertEquals(ranking0Svc, rankingServices.get(1));
        assertEquals(integerMaxSvc, rankingServices.get(2));
    }

    @Test
    void testServiceOrderingBasedOnServiceRankingMixedInjectionTimes() {
        //these classes are registered in a "random" order - so not the same as one would expect them to be in the injected collection
        RankingService ranking0Svc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", 0));
        RankingService rankingMaxSvc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", Integer.MAX_VALUE));
        RankingService ranking1Svc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", 1));
        RankingService rankingNegative5Svc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", -5));

        MyCallingService svc = context.registerInjectActivateService(new MyCallingService());
        final List<RankingService> rankingServices = svc.getServices();
        assertEquals(4, rankingServices.size()); //all 4 services injected
        //all 4 will be in the correct order
        assertEquals(rankingNegative5Svc, rankingServices.get(0));
        assertEquals(ranking0Svc, rankingServices.get(1));
        assertEquals(ranking1Svc, rankingServices.get(2));
        assertEquals(rankingMaxSvc, rankingServices.get(3));

        //now register some more services AFTER the calling component was registered
        //in a real-life Felix these will be dynamically injected in the right position in the collection
        RankingService rankingMinSvc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", Integer.MIN_VALUE)); //should get index 0
        RankingService ranking420Svc = context.registerService(
                RankingService.class,
                Mockito.mock(RankingService.class),
                Map.of("service.ranking", 420)); //should get index 4 (and rankingMaxSvc should get 5)

        assertEquals(6, rankingServices.size()); //all 6 services injected
        //all 6 should be in the correct order but are not
        assertEquals(rankingMinSvc, rankingServices.get(0));
        assertEquals(rankingNegative5Svc, rankingServices.get(1));
        assertEquals(ranking0Svc, rankingServices.get(2));
        assertEquals(ranking1Svc, rankingServices.get(3));
        assertEquals(ranking420Svc, rankingServices.get(4));
        assertEquals(rankingMaxSvc, rankingServices.get(5));
    }

}
