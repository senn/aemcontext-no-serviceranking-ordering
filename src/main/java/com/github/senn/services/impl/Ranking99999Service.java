package com.github.senn.services.impl;

import com.github.senn.services.RankingService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

@Component
@ServiceRanking(Ranking99999Service.SERVICE_RANKING)
public class Ranking99999Service extends AbstractRankingService implements RankingService {

    static final int SERVICE_RANKING = 99999;

    @Override
    protected int getServiceRanking() {
        return SERVICE_RANKING;
    }
}
