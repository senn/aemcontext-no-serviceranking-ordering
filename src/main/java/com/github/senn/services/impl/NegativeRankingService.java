package com.github.senn.services.impl;

import com.github.senn.services.RankingService;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "service.ranking:Integer=" + NegativeRankingService.SERVICE_RANKING
        }
)
public class NegativeRankingService extends AbstractRankingService implements RankingService {

    static final int SERVICE_RANKING = -20;

    @Override
    protected int getServiceRanking() {
        return SERVICE_RANKING;
    }
}
