package com.github.senn.services.impl;

import com.github.senn.services.RankingService;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "service.ranking:Integer=" + Ranking0Service.SERVICE_RANKING
        }
)
public class Ranking0Service extends AbstractRankingService implements RankingService {

    static final int SERVICE_RANKING = 0;

    @Override
    protected int getServiceRanking() {
        return SERVICE_RANKING;
    }
}
