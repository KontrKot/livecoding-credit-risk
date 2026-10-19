package ru.livecoding.creditrisktask.services.impls;

import ru.livecoding.creditrisktask.models.configs.ValidationRuleConfig;
import ru.livecoding.creditrisktask.models.enums.RuleStatus;
import ru.livecoding.creditrisktask.models.request.CreditApplication;
import ru.livecoding.creditrisktask.models.response.EvaluationReport;
import ru.livecoding.creditrisktask.models.response.RuleResult;
import ru.livecoding.creditrisktask.services.ConfigService;
import ru.livecoding.creditrisktask.services.ValidationCreditRuleService;
import ru.livecoding.creditrisktask.services.rules.CreditValidationRule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ValidationCreditRuleServiceImpl implements ValidationCreditRuleService {

    private final List<CreditValidationRule> creditValidationRules;
    private final ConfigService configService;

    public ValidationCreditRuleServiceImpl(List<CreditValidationRule> creditValidationRules, ConfigService configService) {
        this.creditValidationRules = creditValidationRules;
        this.configService = configService;
    }

    @Override
    public EvaluationReport validateRules(CreditApplication creditApplication) {
        ValidationRuleConfig config = configService.getValidationConfig();
        EvaluationReport evaluationReport = new EvaluationReport();
        evaluationReport.setApplicationId(creditApplication.getApplicationId());

        List<CreditValidationRule> sorted = sortByPriority(config);
        List<RuleResult> ruleResults = new ArrayList<>();

        for (CreditValidationRule rule : sorted) {
            RuleResult ruleResult = createRuleResult(rule, creditApplication);
            ruleResults.add(ruleResult);
            if (config.isFailFast() && RuleStatus.ERROR.equals(ruleResult.getStatus())) {
                break;
            }
        }

        evaluationReport.setRuleResults(ruleResults);
        evaluationReport.setOverallStatus(getOverallStatus(ruleResults));
        return evaluationReport;
    }

    private RuleResult createRuleResult(CreditValidationRule rule, CreditApplication creditApplication) {
        RuleStatus ruleStatus = rule.validateRule(creditApplication);
        RuleResult ruleResult = new RuleResult();
        ruleResult.setStatus(ruleStatus);
        ruleResult.setRuleId(rule.getSysName().name());
        return ruleResult;
    }

    private RuleStatus getOverallStatus(List<RuleResult> ruleResults) {
        for (RuleResult ruleResult : ruleResults) {
            if (List.of(RuleStatus.WARN, RuleStatus.ERROR).contains(ruleResult.getStatus())) {
               return ruleResult.getStatus();
            }
        }
        return RuleStatus.OK;
    }

    private List<CreditValidationRule> sortByPriority(ValidationRuleConfig config) {
        return creditValidationRules.stream()
                .sorted(Comparator.comparing(rule -> config.getRulesPriority().indexOf(rule.getSysName())))
                .toList();
    }
}
