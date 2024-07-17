package com.github.senn.services.impl;

import com.github.senn.services.RankingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRankingService implements RankingService {

    private static final Logger log = LoggerFactory.getLogger(AbstractRankingService.class);

    protected abstract int getServiceRanking();

    @Override
    public void printServiceRanking() {
        log.info("Service ranking: {}", getServiceRanking());
    }

    @Override
    public String toString() {
        return String.format("{serviceRanking=%s}", getServiceRanking());
    }
}
