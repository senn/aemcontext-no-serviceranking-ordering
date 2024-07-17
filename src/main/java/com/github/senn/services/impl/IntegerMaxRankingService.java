package com.github.senn.services.impl;

import com.github.senn.services.RankingService;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "service.ranking:Integer=" + Integer.MAX_VALUE
        }
)
public class IntegerMaxRankingService extends AbstractRankingService implements RankingService {

    @Override
    protected int getServiceRanking() {
        return Integer.MAX_VALUE;
    }
}
