package ru.livecoding.creditrisktask.services.rules.impl;

import ru.livecoding.creditrisktask.models.enums.RuleStatus;
import ru.livecoding.creditrisktask.models.enums.RuleSysNames;
import ru.livecoding.creditrisktask.models.request.CreditApplication;
import ru.livecoding.creditrisktask.services.rules.CreditValidationRule;
import org.springframework.stereotype.Component;

@Component
public class AgeRestrictionRule implements CreditValidationRule {

    @Override
    public RuleStatus validateRule(CreditApplication creditApplication) {
        int age = creditApplication.getAge();
        if (age < 18) {
            return RuleStatus.ERROR;
        } else if (age < 21) {
            return RuleStatus.WARN;
        }
        return RuleStatus.OK;
    }

    @Override
    public RuleSysNames getSysName() {
        return RuleSysNames.AGE_RESTRICTION;
    }
}
