package ru.livecoding.creditrisktask.models.configs;

import ru.livecoding.creditrisktask.models.enums.RuleSysNames;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ValidationRuleConfig {
    private Map<RuleSysNames, Boolean> enabledRules;
    private List<RuleSysNames> rulesPriority;
    private boolean isFailFast;
}
