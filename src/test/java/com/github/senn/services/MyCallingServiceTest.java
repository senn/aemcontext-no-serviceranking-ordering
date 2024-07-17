package com.github.senn.services;

import com.github.senn.services.impl.*;
import io.wcm.testing.mock.aem.context.AemContextImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import org.apache.sling.testing.mock.osgi.context.OsgiContextImpl;
import org.apache.sling.testing.mock.sling.context.SlingContextImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testcase that proves that the AemContext / AemContextImpl / SlingContextImpl / OsgiContextImpl sorts services
 * in the order they are registered in the test, and <b>not</b> in the order they would be expected as in an
 * actual OSGi environment.
 */
class MyCallingServiceTest {

    @Test
    void testServiceRankingsInCorrectlyRegisteredOrder_AemContext() {
        final AemContext context = new AemContext();
        //registering in specific correct order
        RankingService first = context.registerService(RankingService.class, new NegativeRankingService());
        RankingService second = context.registerService(RankingService.class, new Ranking0Service());
        RankingService third = context.registerService(RankingService.class, new Ranking100Service());
        RankingService fourth = context.registerService(RankingService.class, new Ranking99999Service());
        RankingService fifth = context.registerService(RankingService.class, new IntegerMaxRankingService());

        MyCallingService svc = context.registerInjectActivateService(new MyCallingService());
        List<RankingService> rankingServices = svc.getServices();

        assertEquals(first, rankingServices.get(0));
        assertEquals(second, rankingServices.get(1));
        assertEquals(third, rankingServices.get(2));
        assertEquals(fourth, rankingServices.get(3));
        assertEquals(fifth, rankingServices.get(4));

        //does not work, ordered in reverse
        //IntegerMax > 99999 > 100 > 0 > negative
    }

    @Test
    void testServiceRankingsInRandomRegisteredOrder_AemContext() {
        final AemContext context = new AemContext();
        //registering in random order
        RankingService third = context.registerService(RankingService.class, new Ranking100Service());
        RankingService first = context.registerService(RankingService.class, new NegativeRankingService());
        RankingService fifth = context.registerService(RankingService.class, new IntegerMaxRankingService());
        RankingService second = context.registerService(RankingService.class, new Ranking0Service());
        RankingService fourth = context.registerService(RankingService.class, new Ranking99999Service());

        MyCallingService svc = context.registerInjectActivateService(new MyCallingService());
        List<RankingService> rankingServices = svc.getServices();

        assertEquals(first, rankingServices.get(0));
        assertEquals(second, rankingServices.get(1));
        assertEquals(third, rankingServices.get(2));
        assertEquals(fourth, rankingServices.get(3));
        assertEquals(fifth, rankingServices.get(4));

        //does not work, ordered in the reverse order they are registered
        // 99999 > 0 > IntegerMax > negative > 100
    }

    @Test
    void testServiceRankingsInCorrectlyRegisteredOrder_AemContextImpl() {
        final AemContextImpl context = new AemContextImpl();
        //registering in specific correct order
        RankingService first = context.registerService(RankingService.class, new NegativeRankingService());
        RankingService second = context.registerService(RankingService.class, new Ranking0Service());
        RankingService third = context.registerService(RankingService.class, new Ranking100Service());
        RankingService fourth = context.registerService(RankingService.class, new Ranking99999Service());
        RankingService fifth = context.registerService(RankingService.class, new IntegerMaxRankingService());

        MyCallingService svc = context.registerInjectActivateService(new MyCallingService());
        List<RankingService> rankingServices = svc.getServices();

        assertEquals(first, rankingServices.get(0));
        assertEquals(second, rankingServices.get(1));
        assertEquals(third, rankingServices.get(2));
        assertEquals(fourth, rankingServices.get(3));
        assertEquals(fifth, rankingServices.get(4));

        //does not work, ordered in reverse
        //IntegerMax > 99999 > 100 > 0 > negative
    }

    @Test
    void testServiceRankingsInRandomRegisteredOrder_AemContextImpl() {
        final AemContextImpl context = new AemContextImpl();
        //registering in random order
        RankingService third = context.registerService(RankingService.class, new Ranking100Service());
        RankingService first = context.registerService(RankingService.class, new NegativeRankingService());
        RankingService fifth = context.registerService(RankingService.class, new IntegerMaxRankingService());
        RankingService second = context.registerService(RankingService.class, new Ranking0Service());
        RankingService fourth = context.registerService(RankingService.class, new Ranking99999Service());

        MyCallingService svc = context.registerInjectActivateService(new MyCallingService());
        List<RankingService> rankingServices = svc.getServices();

        assertEquals(first, rankingServices.get(0));
        assertEquals(second, rankingServices.get(1));
        assertEquals(third, rankingServices.get(2));
        assertEquals(fourth, rankingServices.get(3));
        assertEquals(fifth, rankingServices.get(4));

        //does not work, ordered in the reverse order they are registered
        // 99999 > 0 > IntegerMax > negative > 100
    }

    @Test
    void testServiceRankingsInCorrectlyRegisteredOrder_SlingContext() {
        final SlingContextImpl context = new SlingContextImpl();
        //registering in specific correct order
        RankingService first = context.registerService(RankingService.class, new NegativeRankingService());
        RankingService second = context.registerService(RankingService.class, new Ranking0Service());
        RankingService third = context.registerService(RankingService.class, new Ranking100Service());
        RankingService fourth = context.registerService(RankingService.class, new Ranking99999Service());
        RankingService fifth = context.registerService(RankingService.class, new IntegerMaxRankingService());

        MyCallingService svc = context.registerInjectActivateService(new MyCallingService());
        List<RankingService> rankingServices = svc.getServices();

        assertEquals(first, rankingServices.get(0));
        assertEquals(second, rankingServices.get(1));
        assertEquals(third, rankingServices.get(2));
        assertEquals(fourth, rankingServices.get(3));
        assertEquals(fifth, rankingServices.get(4));

        //does not work, ordered in reverse
        //IntegerMax > 99999 > 100 > 0 > negative
    }

    @Test
    void testServiceRankingsInRandomRegisteredOrder_SlingContextImpl() {
        final SlingContextImpl context = new SlingContextImpl();
        //registering in random order
        RankingService third = context.registerService(RankingService.class, new Ranking100Service());
        RankingService first = context.registerService(RankingService.class, new NegativeRankingService());
        RankingService fifth = context.registerService(RankingService.class, new IntegerMaxRankingService());
        RankingService second = context.registerService(RankingService.class, new Ranking0Service());
        RankingService fourth = context.registerService(RankingService.class, new Ranking99999Service());

        MyCallingService svc = context.registerInjectActivateService(new MyCallingService());
        List<RankingService> rankingServices = svc.getServices();

        assertEquals(first, rankingServices.get(0));
        assertEquals(second, rankingServices.get(1));
        assertEquals(third, rankingServices.get(2));
        assertEquals(fourth, rankingServices.get(3));
        assertEquals(fifth, rankingServices.get(4));

        //does not work, ordered in the reverse order they are registered
        // 99999 > 0 > IntegerMax > negative > 100
    }

    @Test
    void testServiceRankingsInCorrectlyRegisteredOrder_OsgiContextImpl() {
        final OsgiContextImpl context = new OsgiContextImpl();
        //registering in specific correct order
        RankingService first = context.registerService(RankingService.class, new NegativeRankingService());
        RankingService second = context.registerService(RankingService.class, new Ranking0Service());
        RankingService third = context.registerService(RankingService.class, new Ranking100Service());
        RankingService fourth = context.registerService(RankingService.class, new Ranking99999Service());
        RankingService fifth = context.registerService(RankingService.class, new IntegerMaxRankingService());

        MyCallingService svc = context.registerInjectActivateService(new MyCallingService());
        List<RankingService> rankingServices = svc.getServices();

        assertEquals(first, rankingServices.get(0));
        assertEquals(second, rankingServices.get(1));
        assertEquals(third, rankingServices.get(2));
        assertEquals(fourth, rankingServices.get(3));
        assertEquals(fifth, rankingServices.get(4));

        //does not work, ordered in reverse
        //IntegerMax > 99999 > 100 > 0 > negative
    }

    @Test
    void testServiceRankingsInRandomRegisteredOrder_OsgiContextImpl() {
        final OsgiContextImpl context = new OsgiContextImpl();
        //registering in random order
        RankingService third = context.registerService(RankingService.class, new Ranking100Service());
        RankingService first = context.registerService(RankingService.class, new NegativeRankingService());
        RankingService fifth = context.registerService(RankingService.class, new IntegerMaxRankingService());
        RankingService second = context.registerService(RankingService.class, new Ranking0Service());
        RankingService fourth = context.registerService(RankingService.class, new Ranking99999Service());

        MyCallingService svc = context.registerInjectActivateService(new MyCallingService());
        List<RankingService> rankingServices = svc.getServices();

        assertEquals(first, rankingServices.get(0));
        assertEquals(second, rankingServices.get(1));
        assertEquals(third, rankingServices.get(2));
        assertEquals(fourth, rankingServices.get(3));
        assertEquals(fifth, rankingServices.get(4));

        //does not work, ordered in the reverse order they are registered
        // 99999 > 0 > IntegerMax > negative > 100
    }

}