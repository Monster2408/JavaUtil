package xyz.mlserver.javautil.reflect;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.mlserver.javautil.ICollectionList;
import xyz.mlserver.javautil.ReflectionHelper;
import xyz.mlserver.javautil.SneakyThrow;
import xyz.mlserver.javautil.ThrowableActionableResult;
import xyz.mlserver.javautil.Validate;
import xyz.mlserver.javautil.magic.Magic;
import xyz.mlserver.javautil.serialization.FieldInfo;

import java.lang.annotation.Annotation;
import java.lang.ref.Reference;
import java.util.AbstractMap;
import java.util.Map;

public class RefClass<T> {
    @NotNull
    private final Class<T> clazz;

    public RefClass(@NotNull Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <C> RefClass<C> forName(@NotNull String clazz) {
        Validate.notNull(clazz, "clazz cannot be null");
        if (clazz.equals("int")) return new RefClass<>((Class<C>) Integer.TYPE);
        if (clazz.equals("void")) return new RefClass<>((Class<C>) Void.TYPE);
        if (clazz.equals("long")) return new RefClass<>((Class<C>) Long.TYPE);
        if (clazz.equals("boolean")) return new RefClass<>((Class<C>) Boolean.TYPE);
        if (clazz.equals("float")) return new RefClass<>((Class<C>) Float.TYPE);
        if (clazz.equals("double")) return new RefClass<>((Class<C>) Double.TYPE);
        if (clazz.equals("byte")) return new RefClass<>((Class<C>) Byte.TYPE);
        if (clazz.equals("short")) return new RefClass<>((Class<C>) Short.TYPE);
        try {
            return new RefClass<>((Class<C>) Class.forName(clazz));
        } catch (ClassNotFoundException e) {
            return SneakyThrow.sneaky(e);
        }
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public static <C> RefClass<C> forName(@NotNull String clazz, boolean initialize, @Nullable ClassLoader classLoader) {
        try {
            return new RefClass<>((Class<C>) Class.forName(clazz, initialize, classLoader));
        } catch (ClassNotFoundException e) {
            return SneakyThrow.sneaky(e);
        }
    }

    @Contract(pure = true)
    @NotNull
    public RefClass<?> unchecked() { return Ref.getClassUnchecked(clazz); }

    @NotNull
    public Class<T> getClazz() { return clazz; }

    @Contract(pure = true)
    @NotNull
    public RefField<T> getDeclaredField(String fieldName) { return Ref.getDeclaredField(this.clazz, fieldName); }

    @Contract(pure = true)
    @NotNull
    public RefField<T> getField(String fieldName) { return Ref.getField(this.clazz, fieldName); }

    @Contract(pure = true)
    @NotNull
    public RefConstructor<T> getConstructor(Class<?>... classes) { return Ref.getConstructor(this.clazz, classes); }

    @Contract(pure = true)
    @NotNull
    public RefConstructor<T> getDeclaredConstructor(Class<?>... classes) { return Ref.getDeclaredConstructor(this.clazz, classes); }

    @Contract(pure = true)
    @Nullable
    public RefConstructor<T> getDeclaredConstructorMaybe(Class<?>... classes) {
        return ThrowableActionableResult.of(() -> getDeclaredConstructor(classes)).get();
    }

    @Contract(pure = true)
    @NotNull
    public RefConstructor<T>[] getConstructors() { return Ref.getConstructors(this.clazz); }

    @Contract(pure = true)
    @NotNull
    public RefConstructor<T>[] getDeclaredConstructors() { return Ref.getDeclaredConstructors(this.clazz); }

    @Contract(pure = true)
    @NotNull
    public RefMethod<T> getMethod(String methodName, Class<?>... classes) { return Ref.getMethod(this.clazz, methodName, classes); }

    @Contract(pure = true)
    @NotNull
    public RefMethod<T> getDeclaredMethod(String methodName, Class<?>... classes) { return Ref.getDeclaredMethod(this.clazz, methodName, classes); }

    @Contract(pure = true)
    @NotNull
    public RefMethod<T>[] getMethods() { return Ref.getMethods(this.clazz); }

    @Contract(pure = true)
    @NotNull
    public RefMethod<T>[] getDeclaredMethods() { return Ref.getDeclaredMethods(this.clazz); }

    @Contract(pure = true)
    @NotNull
    public RefField<T>[] getFields() { return Ref.getFields(this.clazz); }

    @Contract(pure = true)
    @NotNull
    public RefField<T>[] getDeclaredFields() { return Ref.getDeclaredFields(this.clazz); }

    @Contract(pure = true)
    @NotNull
    public <U> RefClass<? extends U> asSubClass(Class<U> clazz) { return new RefClass<>(this.clazz.asSubclass(clazz)); }

    @Contract(pure = true)
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) { return this.clazz.getAnnotation(annotationClass); }

    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) { return this.clazz.isAnnotationPresent(annotationClass); }

    @Contract(pure = true)
    @NotNull
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationClass) { return this.clazz.getAnnotationsByType(annotationClass); }

    @NotNull
    public Annotation[] getAnnotations() { return this.clazz.getAnnotations(); }

    public <A extends Annotation> A getDeclaredAnnotation(Class<A> annotationClass) { return this.clazz.getDeclaredAnnotation(annotationClass); }

    @NotNull
    public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationClass) { return this.clazz.getDeclaredAnnotationsByType(annotationClass); }

    @NotNull
    public Annotation[] getDeclaredAnnotations() { return this.clazz.getDeclaredAnnotations(); }

    @Contract(value = "_ -> param1", pure = true)
    public T cast(Object obj) { return this.clazz.cast(obj); }

    public T[] getEnumConstants() { return this.clazz.getEnumConstants(); }

    public boolean isExtends(Class<?> clazz) {
        return ReflectionHelper.getSupers(this.clazz).contains(clazz);
    }

    @NotNull
    public T newInstance() {
        return this.getDeclaredConstructor().newInstance();
    }

    @Nullable
    public Map.Entry<RefConstructor<T>, Object[]> tryFindConstructor(Object[] args, @NotNull ICollectionList<FieldInfo<?>> fields) {
        RefConstructor<T> constructor;
        if ((constructor = getDeclaredConstructorMaybe(fields.map(FieldInfo::getClazz).toArray(new Class[0]))) != null) return new AbstractMap.SimpleImmutableEntry<>(constructor, args);
        Class<?>[] classes1 = fields.map(field -> {
            if (field.getActualType() != null) return field.getActualType();
            return field.getClazz();
        }).toArray(new Class[0]);
        Object[] args1 = args == null ? null : ICollectionList.asList(args).map((o, i) -> {
            if (o == null) return null;
            if (o instanceof Reference) {
                FieldInfo<?> field = fields.get(i);
                if (field != null && field.getActualType() != null) {
                    return ((Reference<?>) o).get();
                } else {
                    return o;
                }
            } else {
                return o;
            }
        }).toArray();
        if ((constructor = getDeclaredConstructorMaybe(classes1)) != null) return new AbstractMap.SimpleImmutableEntry<>(constructor, args1);
        return null;
    }

    /**
     * Return the Virtual Machine's Class object for the named primitive type. The result class is equivalent
     * to <i>&lt;BoxedPrimitiveType&gt;.TYPE</i>.
     * <p><strong>Remember: This is forbidden magic.</strong>
     * @throws ClassNotFoundException when class was not found by specified type.
     * @throws NullPointerException when null was provided to the type.
     * @return primitive class
     * @deprecated Scheduled to be removed in 0.15. Please use {@link util.magic.Magic.ForbiddenMagic#getPrimitiveClass(String)}  Magic.getForbiddenMagic().getPrimitiveClass(String)} instead.
     */
    @Deprecated
    @NotNull
    public static Class<?> getPrimitiveClass(@NotNull String type) throws ClassNotFoundException, NullPointerException {
        return Magic.getForbiddenMagic().getPrimitiveClass(type);
    }

    /**
     * Return the Virtual Machine's Class object for the named primitive type. The result class is equivalent
     * to <i>&lt;BoxedPrimitiveType&gt;.TYPE</i>. This method is similar to {@link #getPrimitiveClass(String)},
     * but it throws {@link RuntimeException} instead of {@link ClassNotFoundException} when could not find the class.
     * <p><strong>Remember: This is forbidden magic.</strong>
     * @throws RuntimeException when class was not found by specified type.
     * @throws NullPointerException when null was provided to the type.
     * @return primitive class
     * @deprecated Scheduled to be removed in 0.15. Please use {@link util.magic.Magic.ForbiddenMagic#getPrimitiveClassSneaky(String) Magic.getForbiddenMagic().getPrimitiveClassSneaky(String)} instead.
     */
    @Deprecated
    @NotNull
    public static Class<?> getPrimitiveClassSneaky(@NotNull String type) throws NullPointerException {
        return Magic.getForbiddenMagic().getPrimitiveClassSneaky(type);
    }
}
