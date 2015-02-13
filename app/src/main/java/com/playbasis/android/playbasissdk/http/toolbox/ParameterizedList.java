package com.playbasis.android.playbasissdk.http.toolbox;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by gregoire on 1/15/15.
 */
public class ParameterizedList<X> implements ParameterizedType {

    private Class<?> wrapped;

    public ParameterizedList(Class<X> wrapped) {
        this.wrapped = wrapped;
    }

    public Type[] getActualTypeArguments() {
        return new Type[] {wrapped};
    }

    public Type getRawType() {
        return List.class;
    }

    public Type getOwnerType() {
        return null;
    }
}
