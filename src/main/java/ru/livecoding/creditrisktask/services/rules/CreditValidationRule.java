package ru.livecoding.creditrisktask.services.rules;

import ru.livecoding.creditrisktask.models.enums.RuleStatus;
import ru.livecoding.creditrisktask.models.enums.RuleSysNames;
import ru.livecoding.creditrisktask.models.request.CreditApplication;

public interface CreditValidationRule {

    RuleSysNames getSysName();

    RuleStatus validateRule(CreditApplication creditApplication);
}
