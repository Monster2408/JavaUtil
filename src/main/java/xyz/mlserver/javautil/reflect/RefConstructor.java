package xyz.mlserver.javautil.reflect;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.mlserver.javautil.SneakyThrow;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;

public class RefConstructor<T> extends RefExecutable implements RefModifierEditor<RefConstructor<T>, Constructor<T>> {
    @NotNull
    private final Constructor<T> constructor;

    public RefConstructor(@NotNull Constructor<T> constructor) {
        super(constructor);
        this.constructor = constructor;
    }

    @NotNull
    public T newInstance(Object... o) {
        try {
            return this.constructor.newInstance(o);
        } catch (ReflectiveOperationException e) { return SneakyThrow.sneaky(e); }
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(@Nullable Object o) { return this.constructor.equals(o); }

    public boolean equals(@NotNull RefConstructor<?> o) { return this.constructor.equals(o.constructor); }

    @NotNull
    public RefConstructor<T> ifEquals(@NotNull RefConstructor<?> refConstructor) {
        if (!this.constructor.equals(refConstructor.constructor)) throw new IllegalStateException("Constructor isn't equals another constructor!");
        return this;
    }

    @Contract("_ -> this")
    @NotNull
    public <S> RefConstructor<T> ifEquals(@NotNull Constructor<S> constructor) {
        if (!this.constructor.equals(constructor)) throw new IllegalStateException("Constructor isn't equals another constructor!");
        return this;
    }

    @Contract("_ -> this")
    @NotNull
    public RefConstructor<T> accessible(boolean flag) {
        this.constructor.setAccessible(flag);
        return this;
    }

    @NotNull
    public Constructor<T> getConstructor() { return constructor; }

    @Override
    @NotNull
    public String toString() { return this.constructor.toString(); }

    @Override
    public @NotNull Member getMember() { return constructor; }
}
