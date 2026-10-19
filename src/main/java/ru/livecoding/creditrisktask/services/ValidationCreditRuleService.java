package ru.livecoding.creditrisktask.services;

import ru.livecoding.creditrisktask.models.request.CreditApplication;
import ru.livecoding.creditrisktask.models.response.EvaluationReport;

public interface ValidationCreditRuleService {

    EvaluationReport validateRules(CreditApplication creditApplication);
}
