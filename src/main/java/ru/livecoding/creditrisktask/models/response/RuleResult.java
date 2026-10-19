package ru.livecoding.creditrisktask.models.response;

import ru.livecoding.creditrisktask.models.enums.RuleStatus;
import lombok.Data;

/**
 * Результат выполнения одного правила для кредитной заявки.
 */
@Data
public class RuleResult {
    private String ruleId;
    private RuleStatus status;
    private String message;
}
