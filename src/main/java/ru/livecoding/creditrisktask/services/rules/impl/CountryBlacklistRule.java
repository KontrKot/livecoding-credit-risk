package ru.livecoding.creditrisktask.services.rules.impl;

import ru.livecoding.creditrisktask.models.enums.RuleStatus;
import ru.livecoding.creditrisktask.models.enums.RuleSysNames;
import ru.livecoding.creditrisktask.models.request.CreditApplication;
import ru.livecoding.creditrisktask.services.rules.CreditValidationRule;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CountryBlacklistRule implements CreditValidationRule {

    private static final Set<String> blacklistCountries = Set.of(
            "ENG",
            "US",
            "SOME_MORE"
    );

    @Override
    public RuleStatus validateRule(CreditApplication creditApplication) {
        return blacklistCountries.contains(creditApplication.getCountry()) ? RuleStatus.ERROR : RuleStatus.OK;
    }

    @Override
    public RuleSysNames getSysName() {
        return RuleSysNames.BLACKLIST_COUNTRY;
    }
}
