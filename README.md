# AemContext No ServiceRanking Ordering

Just a small demo app that can be deployed in an AEM environment for example.

It shows that the ordering of referenced services in a collection, when tesing in an AemContext (or SlingContext / OsgiContext), is not as it would be in an actual OSGi environment.

## Setup

- A service interface `RankingService`
- Some implementations of this `RankingService` with different service rankings (from negative to `Integer.MAX_VALUE`)
- A service `MyCallingService` that has a list of all these implementations - injected by OSGi

### Actual OSGi environment

When printing the list of implementations, we get a correctly ordered list like this

```text
com.github.senn.services.MyCallingService {serviceRanking=-20}
com.github.senn.services.MyCallingService {serviceRanking=0}
com.github.senn.services.MyCallingService {serviceRanking=100}
com.github.senn.services.MyCallingService {serviceRanking=99999}
com.github.senn.services.MyCallingService {serviceRanking=2147483647}
```

The lowest service ranking has the first index in the list.

### Testing context

In a testing context the order of the list is _always_ exactly the opposite of the order in which the services were registered using `context.registerService(RankingService.class, impl)`.

See the unit test for more info.  The test fails for every level of context implementation (AEM / Sling / Osgi).

## Expectation

A representative list of implementations as it would be in an actual OSGi environment.

## Remarks

I noticed that this service ranking only works correctly when using `@ServiceRanking`...
When I remove this annotation and use the component property `service.ranking`, the ordering is also wrong in OSGi itself...?!?