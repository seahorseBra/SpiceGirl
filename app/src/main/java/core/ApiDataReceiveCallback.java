package core;

/**
 * Created by zchao on 2016/6/1.
 */
public interface ApiDataReceiveCallback<T> {
    void onDataReceived(T t, boolean isSuccessful, Exception e);
}
