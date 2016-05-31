package javaBean;

/**
 * Created by mavin on 2016/5/30.
 */
public class CacheObj<T> {
    private T t;
    private long cacheTime;
    private String key;

    public CacheObj(T t, long cacheTime, String key) {
        this.t = t;
        this.cacheTime = cacheTime;
        this.key = key;
    }

    public boolean isOutOfDate(long cacheTimeLong) {
        return Math.abs(cacheTime - System.currentTimeMillis()) >= cacheTimeLong;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void setCacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getT() {
        return t;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public String getKey() {
        return key;
    }
}
