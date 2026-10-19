package ru.livecoding.creditrisktask.services.impls;

import ru.livecoding.creditrisktask.models.configs.ValidationRuleConfig;
import ru.livecoding.creditrisktask.services.ConfigService;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class ConfigServiceImpl implements ConfigService {

    private final ObjectMapper objectMapper;

    public ConfigServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ValidationRuleConfig getValidationConfig() {
        String configSrc = """
                { "enabledRules": {
                    "AGE_RESTRICTION": true,
                    "DEBT_BURDEN": true,
                    "BLACKLIST_COUNTRY": true
                  },
                  "rulesPriority": [
                    "AGE_RESTRICTION", "DEBT_BURDEN", "BLACKLIST_COUNTRY"
                  ],
                  "isFailFast": true
                }""";
        return objectMapper.readValue(configSrc, ValidationRuleConfig.class);
    }
}
