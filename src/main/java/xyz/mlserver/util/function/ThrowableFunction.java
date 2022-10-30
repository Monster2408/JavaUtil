package xyz.mlserver.util.function;

import org.jetbrains.annotations.NotNull;
import xyz.mlserver.util.Chain;
import xyz.mlserver.util.ThrowableActionableResult;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowableFunction<T, R> extends Function<T, ThrowableActionableResult<R>>, Chain<ThrowableFunction<T, R>> {
    @Override
    @NotNull
    default ThrowableActionableResult<R> apply(T t) {
        try {
            return ThrowableActionableResult.success(run(t));
        } catch (Throwable throwable) {
            return ThrowableActionableResult.error(throwable);
        }
    }

    R run(T t) throws Throwable;
}
