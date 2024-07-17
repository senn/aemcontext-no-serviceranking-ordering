package com.github.senn.services.impl;

import com.github.senn.services.RankingService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

import static com.github.senn.services.impl.NegativeRankingService.SERVICE_RANKING;

@Component
@ServiceRanking(SERVICE_RANKING)
public class NegativeRankingService extends AbstractRankingService implements RankingService {

    static final int SERVICE_RANKING = -20;

    @Override
    protected int getServiceRanking() {
        return SERVICE_RANKING;
    }
}
