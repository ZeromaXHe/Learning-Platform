package com.zerox.fundamental;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

/**
 * @Author: zhuxi
 * @Time: 2022/5/28 13:56
 * @Description:
 * @ModifiedBy: zhuxi
 */
public interface Lesson16JupiterInstanceLifecycle {
    class MyTestInstancePostProcessor implements TestInstancePostProcessor {
        @Override
        public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {
            System.out.println("--MyTestInstancePostProcessor--start postPreocessTestInstance---" + o);
            System.out.println(extensionContext.getTestInstanceLifecycle());
            System.out.println("--MyTestInstancePostProcessor--end postPreocessTestInstance---" + o);
        }
    }

    class MyBeforeAllCallback implements BeforeAllCallback {
        @Override
        public void beforeAll(ExtensionContext extensionContext) throws Exception {
            System.out.println("--MyBeforeAllCallback--beforeAll---");
        }
    }

    class MyBeforeEachCallback implements BeforeEachCallback {
        @Override
        public void beforeEach(ExtensionContext extensionContext) throws Exception {
            System.out.println("--MyBeforeEachCallback--beforeEach---");
        }
    }

    class MyBeforeTestExecutionCallback implements BeforeTestExecutionCallback {
        @Override
        public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
            System.out.println("--MyBeforeTestExecutionCallback--beforeTestExecution---");
        }
    }

    class MyTestExecutionExceptionHandler implements TestExecutionExceptionHandler {
        @Override
        public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
            System.out.println("--MyTestExecutionExceptionHandler--handleTestExecutionException---" + throwable);
        }
    }

    class MyAfterTestExecutionCallback implements AfterTestExecutionCallback {
        @Override
        public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
            System.out.println("--MyAfterTestExecutionCallback--afterTestExecution---");
        }
    }

    class MyAfterEachCallback implements AfterEachCallback {
        @Override
        public void afterEach(ExtensionContext extensionContext) throws Exception {
            System.out.println("--MyAfterEachCallback--afterEach---");
        }
    }

    class MyAfterAllCallback implements AfterAllCallback {
        @Override
        public void afterAll(ExtensionContext extensionContext) throws Exception {
            System.out.println("--MyAfterAllCallback--afterAll---");
        }
    }
}
