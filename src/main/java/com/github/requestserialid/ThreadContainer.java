package com.github.requestserialid;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadContainer {

    /**
     * concurrent保证一直性
     */
    @SuppressWarnings("rawtypes")
    private static Map<String, ThreadLocal> threadParams = new ConcurrentHashMap<String, ThreadLocal>(0);

    /**
     * 取得threadLocal
     * 
     * @author sunmanhua
     * @param index
     * @param threadLocal
     * @return boolean
     */
    public static ThreadLocal getMyThreadLocal(String index) {
        return threadParams.get(index);
    }

    /**
     * 添加threadLocal
     * 
     * @author sunmanhua
     * @param index
     * @param threadLocal
     * @return boolean
     */
    public static boolean addMyThreadLocal(String index, ThreadLocal threadLocal) {
        if (threadLocal == null)
            return false;
        if (getMyThreadLocal(index) != null) {
            if (threadLocal.equals(getMyThreadLocal(index)))
                return true;
            else
                return false;
        }
        threadParams.put(index, threadLocal);
        return true;
    }

    /**
     * 
     * 
     * @author sunmanhua
     * @param index
     *            void
     */
    public static Map<String, Object> getAllThreadLocalSnapshot() {
        if (threadParams == null)
            return null;
        Map<String,Object> snapshot = new HashMap<String,Object>();
        Set<String> keys = threadParams.keySet();
        for (String key : keys) {
            if (threadParams.get(key) != null && threadParams.get(key).get() != null)
                snapshot.put(key, threadParams.get(key).get());
        }
        return snapshot;
    }

    /**
     * 
     * 
     * @author sunmanhua
     * @param index
     *            void
     */
    public static void cleanAllThreadLocal() {
        if (threadParams == null)
            return;
        Set<String> keys = threadParams.keySet();
        for (String key : keys) {
            cleanThreadLocal(key);
        }
    }

    /**
     * 清出threadLocal
     * 
     * @author sunmanhua
     * @param index
     *            void
     */
    public static void cleanThreadLocal(String index) {

        ThreadLocal local = getMyThreadLocal(index);
        if (local == null)
            return;
        local.remove();
    }

    /**
     * 清空集合
     * 
     * @author sunmanhua void
     */
    public static void destoryAllThreadLocal() {
        if (threadParams == null)
            return;
        Set<String> keys = threadParams.keySet();
        for (String key : keys) {
            destoryThreadLocal(key);
        }
        threadParams = null;
    }

    /**
     * 清空单个threadLocal
     * 
     * @author sunmanhua void
     */
    public static void destoryThreadLocal(String index) {
        ThreadLocal local = getMyThreadLocal(index);
        if (local != null && threadParams != null)
            threadParams.remove(index);

    }
}