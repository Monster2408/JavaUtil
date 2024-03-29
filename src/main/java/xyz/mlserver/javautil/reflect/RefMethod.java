package xyz.mlserver.javautil.reflect;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.mlserver.javautil.SneakyThrow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class RefMethod<T> extends RefExecutable implements RefModifierEditor<RefMethod<T>, Method> {
    @NotNull
    private final Method method;

    @NotNull
    public Method getMethod() { return method; }

    public Object invokeObj(Object obj, Object... args) {
        try {
            return this.method.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return SneakyThrow.sneaky(e);
        }
    }

    public RefMethod(@NotNull Method method) {
        super(method);
        this.method = method;
    }

    public Object invoke(T obj, Object... args) {
        try {
            return this.method.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return SneakyThrow.sneaky(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <R> R call(T obj, Object... args) {
        return (R) invoke(obj, args);
    }

    @SuppressWarnings("unchecked")
    public <R> R callObj(Object obj, Object... args) {
        return (R) invokeObj(obj, args);
    }

    @Contract(pure = true)
    public boolean isBridge() {
        return this.method.isBridge();
    }

    @Contract(pure = true)
    public boolean isPublic() {
        return Modifier.isPublic(this.method.getModifiers());
    }

    @Override
    @NotNull
    @Contract(pure = true)
    public String toString() {
        return this.method.toString();
    }

    @Contract("_ -> this")
    @NotNull
    public RefMethod<T> accessible(boolean flag) {
        setAccessible(flag); return this;
    }

    @Override
    public @NotNull Member getMember() {
        return method;
    }
}
