package github.helpers.annotations;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class LocalTestExtensions {

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @ExtendWith(LocalExecutionCondition.class)
    public @interface LocalTest {
    }

    public static class LocalExecutionCondition implements ExecutionCondition {

        @Override
        public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
            return com.codeborne.selenide.Configuration.remote == null
                    || com.codeborne.selenide.Configuration.remote.isEmpty()
                    ? ConditionEvaluationResult.enabled("Test enabled locally")
                    : ConditionEvaluationResult.disabled("Test disabled when running remotely");
        }
    }
}