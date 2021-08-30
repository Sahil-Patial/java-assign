package com.sahil.multitenancy.async;

import com.sahil.multitenancy.core.ThreadLocalStorage;
import org.springframework.core.task.TaskDecorator;

public class TenantAwareTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        String tenantName = ThreadLocalStorage.getTenantName();
        return () -> {
            try {
                ThreadLocalStorage.setTenantName(tenantName);
                runnable.run();
            } finally {
                ThreadLocalStorage.setTenantName(null);
            }
        };
    }
}