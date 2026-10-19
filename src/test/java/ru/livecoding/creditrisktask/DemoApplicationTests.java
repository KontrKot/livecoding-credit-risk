package ru.livecoding.creditrisktask;

import ru.livecoding.creditrisktask.models.enums.RuleStatus;
import ru.livecoding.creditrisktask.models.request.CreditApplication;
import ru.livecoding.creditrisktask.models.response.EvaluationReport;
import ru.livecoding.creditrisktask.models.response.RuleResult;
import ru.livecoding.creditrisktask.services.ValidationCreditRuleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private ValidationCreditRuleService validationCreditRuleService;

    @Test
    void validateCreditRuleOkResult() {
        CreditApplication creditApplication = new CreditApplication();
        creditApplication.setApplicationId("test-application-id");
        creditApplication.setAge(30);
        creditApplication.setCountry("RUS");
        creditApplication.setUserId("test-user-id");
        creditApplication.setMonthlyIncome(BigDecimal.valueOf(100_000));
        creditApplication.setMonthlyDebt(BigDecimal.valueOf(10));
        creditApplication.setVipClient(false);

        EvaluationReport actual = validationCreditRuleService.validateRules(creditApplication);


        EvaluationReport expected = new EvaluationReport();
        expected.setOverallStatus(RuleStatus.OK);
        expected.setApplicationId("test-application-id");

        RuleResult ageRuleResult = new RuleResult();
        ageRuleResult.setRuleId("AGE_RESTRICTION");
        ageRuleResult.setStatus(RuleStatus.OK);

        RuleResult debtBurdenRuleResult = new RuleResult();
        debtBurdenRuleResult.setRuleId("DEBT_BURDEN");
        debtBurdenRuleResult.setStatus(RuleStatus.OK);

        RuleResult blackListRuleResult = new RuleResult();
        blackListRuleResult.setRuleId("BLACKLIST_COUNTRY");
        blackListRuleResult.setStatus(RuleStatus.OK);

        expected.setRuleResults(List.of(ageRuleResult, debtBurdenRuleResult, blackListRuleResult));


        Assertions.assertEquals(expected, actual);
    }

}
