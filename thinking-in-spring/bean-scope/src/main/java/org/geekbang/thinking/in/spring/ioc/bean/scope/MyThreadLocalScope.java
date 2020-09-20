package org.geekbang.thinking.in.spring.ioc.bean.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

public class MyThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thread-local";
    NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal<Map<String, Object>>("threadLocal-scope"){

        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> map = generateContext();
        Object object = map.get(name);
        if (object == null){
            object = objectFactory.getObject();
            map.put(name, object);
        }

        return object;
    }

    public Map<String, Object> generateContext(){
        Map<String, Object> map = threadLocal.get();
        return map;
    }

    @Nullable
    @Override
    public Object remove(String name) {
        return generateContext().remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Nullable
    @Override
    public Object resolveContextualObject(String key) {
        return generateContext().get(key);
    }

    @Nullable
    @Override
    public String getConversationId() {
        long id = Thread.currentThread().getId();
        return String.valueOf(id);
    }
}
