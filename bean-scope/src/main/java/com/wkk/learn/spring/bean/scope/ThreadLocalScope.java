package com.wkk.learn.spring.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 自定义bean作用域-线程作用域
 * @Author Wangkunkun
 * @Date 2021/5/1 18:08
 */
public class ThreadLocalScope implements Scope {

    public static final String THREAD_LOCAL_SCOPE = "thread-local";

    private final NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal<Map<String, Object>>("thread-local") {
        // 实现initValue方法，防止空指针
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = threadLocal.get();
        Object object = context.get(name);
        if(object == null) {
            object = objectFactory.getObject();
            context.put(name, object);
        }
        return object;
    }

    @Override
    public Object remove(String name) {
        return threadLocal.get().remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // TODO
    }

    @Override
    public Object resolveContextualObject(String key) {
        return threadLocal.get().get(key);
    }

    /**
     * 使用线程id作为唯一标示
     * @return
     */
    @Override
    public String getConversationId() {
        return String.valueOf(Thread.currentThread().getId());
    }
}
