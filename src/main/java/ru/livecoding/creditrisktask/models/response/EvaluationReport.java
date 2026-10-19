package ru.livecoding.creditrisktask.models.response;

import ru.livecoding.creditrisktask.models.enums.RuleStatus;
import lombok.Data;

import java.util.List;

/**
 * Итоговый отчёт проверки кредитной заявки бизнес-правилами.
 */
@Data
public class EvaluationReport {
    private String applicationId;
    private RuleStatus overallStatus; // OK если все правила OK;
    // WARN если хотя бы одно WARN;
    // ERROR если хотя бы одно ERROR
    private List<RuleResult> ruleResults;
}
