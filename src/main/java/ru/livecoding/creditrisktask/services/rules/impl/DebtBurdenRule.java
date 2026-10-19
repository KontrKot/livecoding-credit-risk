package ru.livecoding.creditrisktask.services.rules.impl;

import ru.livecoding.creditrisktask.models.enums.RuleStatus;
import ru.livecoding.creditrisktask.models.enums.RuleSysNames;
import ru.livecoding.creditrisktask.models.request.CreditApplication;
import ru.livecoding.creditrisktask.services.rules.CreditValidationRule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DebtBurdenRule implements CreditValidationRule {

    private static final BigDecimal sixtyPercent = BigDecimal.valueOf(0.6);
    private static final BigDecimal fortyPercent = BigDecimal.valueOf(0.4);

    @Override
    public RuleStatus validateRule(CreditApplication creditApplication) {
        BigDecimal debtBurden = creditApplication.getMonthlyDebt().divide(creditApplication.getMonthlyIncome(), RoundingMode.HALF_UP);
        if (debtBurden.compareTo(sixtyPercent) > 0) {
            return RuleStatus.ERROR;
        } else if (debtBurden.compareTo(fortyPercent) > 0) {
            return RuleStatus.WARN;
        }
        return RuleStatus.OK;
    }

    @Override
    public RuleSysNames getSysName() {
        return RuleSysNames.DEBT_BURDEN;
    }
}
