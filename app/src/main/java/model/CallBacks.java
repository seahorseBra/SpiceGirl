package model;

/**
 * Created by zchao on 2016/5/30.
 */
public interface CallBacks<T, E> {
    void getSuccess(T t);
    void ShowFailMsg(E e);
}
