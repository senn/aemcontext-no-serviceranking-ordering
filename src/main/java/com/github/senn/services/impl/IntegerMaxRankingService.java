package com.github.senn.services.impl;

import com.github.senn.services.RankingService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

@Component
@ServiceRanking(Integer.MAX_VALUE)
public class IntegerMaxRankingService extends AbstractRankingService implements RankingService {

    @Override
    protected int getServiceRanking() {
        return Integer.MAX_VALUE;
    }
}
