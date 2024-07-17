package com.github.senn.services.impl;

import com.github.senn.services.RankingService;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "service.ranking:Integer=" + Ranking100Service.SERVICE_RANKING
        }
)
public class Ranking100Service extends AbstractRankingService implements RankingService {

    static final int SERVICE_RANKING = 100;

    @Override
    protected int getServiceRanking() {
        return SERVICE_RANKING;
    }
}
