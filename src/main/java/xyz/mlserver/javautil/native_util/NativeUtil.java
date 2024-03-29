package xyz.mlserver.javautil.native_util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.jar.JarFile;

@SuppressWarnings("unused")
public class NativeUtil {
    @Contract(pure = true)
    private static @Nullable Void getVoid() {
        return null;
    }

    public static void appendToBootstrapClassLoaderSearch(@NotNull File file) {
        try {
            appendToBootstrapClassLoaderSearch(file.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds an url to bootstrap class loader.
     * @param url an url to add
     * @see java.lang.instrument.Instrumentation#appendToBootstrapClassLoaderSearch(JarFile)
     */
    public static void appendToBootstrapClassLoaderSearch(@NotNull String url) {
        Objects.requireNonNull(url);
        NativeAccessor.appendToBootstrapClassLoaderSearch(url);
    }

    public static void appendToSystemClassLoaderSearch(@NotNull File file) {
        try {
            appendToSystemClassLoaderSearch(file.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds an url to system class loader.
     * @param url an url to add
     * @see java.lang.instrument.Instrumentation#appendToSystemClassLoaderSearch(JarFile)
     */
    public static void appendToSystemClassLoaderSearch(@NotNull String url) {
        Objects.requireNonNull(url);
        NativeAccessor.appendToSystemClassLoaderSearch(url);
    }

    /**
     * Creates new instance without calling constructor of it. See: sun.misc.Unsafe#allocateInstance(Class)
     * @param clazz the class
     * @param <T> the type of the class/instance
     * @return the created instance
     */
    public static <T> T allocateInstance(Class<T> clazz) {
        Objects.requireNonNull(clazz);
        return NativeAccessor.allocateInstance(clazz);
    }

    /**
     * Set value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     * @param value the new value of field
     */
    public static void setBoolean(@NotNull Field field, @Nullable Object instance, boolean value) {
        Objects.requireNonNull(field);
        NativeAccessor.setBoolean(field, instance, value);
    }

    /**
     * Set value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     * @param value the new value of field
     */
    public static void setByte(@NotNull Field field, @Nullable Object instance, byte value) {
        Objects.requireNonNull(field);
        NativeAccessor.setByte(field, instance, value);
    }

    /**
     * Set value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     * @param value the new value of field
     */
    public static void setChar(@NotNull Field field, @Nullable Object instance, char value) {
        Objects.requireNonNull(field);
        NativeAccessor.setChar(field, instance, value);
    }

    /**
     * Set value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     * @param value the new value of field
     */
    public static void setDouble(@NotNull Field field, @Nullable Object instance, double value) {
        Objects.requireNonNull(field);
        NativeAccessor.setDouble(field, instance, value);
    }

    /**
     * Set value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     * @param value the new value of field
     */
    public static void setFloat(@NotNull Field field, @Nullable Object instance, float value) {
        Objects.requireNonNull(field);
        NativeAccessor.setFloat(field, instance, value);
    }

    /**
     * Set value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     * @param value the new value of field
     */
    public static void setInt(@NotNull Field field, @Nullable Object instance, int value) {
        Objects.requireNonNull(field);
        NativeAccessor.setInt(field, instance, value);
    }

    /**
     * Set value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     * @param value the new value of field
     */
    public static void setLong(@NotNull Field field, @Nullable Object instance, long value) {
        Objects.requireNonNull(field);
        NativeAccessor.setLong(field, instance, value);
    }

    /**
     * Set value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     * @param value the new value of field
     */
    public static void setShort(@NotNull Field field, @Nullable Object instance, short value) {
        Objects.requireNonNull(field);
        NativeAccessor.setShort(field, instance, value);
    }

    /**
     * Set value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     * @param value the new value of field
     */
    public static void setObject(@NotNull Field field, @Nullable Object instance, Object value) {
        Objects.requireNonNull(field);
        NativeAccessor.setObject(field, instance, value);
    }

    public static void set(@NotNull Field field, @Nullable Object instance, Object value) {
        Objects.requireNonNull(field);
        if (field.getType() == boolean.class) {
            NativeAccessor.setBoolean(field, instance, (boolean) value);
        } else if (field.getType() == byte.class) {
            NativeAccessor.setByte(field, instance, (byte) value);
        } else if (field.getType() == char.class) {
            NativeAccessor.setChar(field, instance, (char) value);
        } else if (field.getType() == double.class) {
            NativeAccessor.setDouble(field, instance, (double) value);
        } else if (field.getType() == float.class) {
            NativeAccessor.setFloat(field, instance, (float) value);
        } else if (field.getType() == int.class) {
            NativeAccessor.setInt(field, instance, (int) value);
        } else if (field.getType() == long.class) {
            NativeAccessor.setLong(field, instance, (long) value);
        } else if (field.getType() == short.class) {
            NativeAccessor.setShort(field, instance, (short) value);
        } else {
            NativeAccessor.setObject(field, instance, value);
        }
    }

    /**
     * Get value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     */
    public static boolean getBoolean(@NotNull Field field, @Nullable Object instance) {
        Objects.requireNonNull(field);
        return NativeAccessor.getBoolean(field, instance);
    }

    /**
     * Get value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     */
    public static byte getByte(@NotNull Field field, @Nullable Object instance) {
        Objects.requireNonNull(field);
        return NativeAccessor.getByte(field, instance);
    }

    /**
     * Get value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     */
    public static char getChar(@NotNull Field field, @Nullable Object instance) {
        Objects.requireNonNull(field);
        return NativeAccessor.getChar(field, instance);
    }

    /**
     * Get value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     */
    public static double getDouble(@NotNull Field field, @Nullable Object instance) {
        Objects.requireNonNull(field);
        return NativeAccessor.getDouble(field, instance);
    }

    /**
     * Get value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     */
    public static float getFloat(@NotNull Field field, @Nullable Object instance) {
        Objects.requireNonNull(field);
        return NativeAccessor.getFloat(field, instance);
    }

    /**
     * Get value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     */
    public static int getInt(@NotNull Field field, @Nullable Object instance) {
        Objects.requireNonNull(field);
        return NativeAccessor.getInt(field, instance);
    }

    /**
     * Get value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     */
    public static long getLong(@NotNull Field field, @Nullable Object instance) {
        Objects.requireNonNull(field);
        return NativeAccessor.getLong(field, instance);
    }

    /**
     * Get value of a field without calling {@link Field#setAccessible(boolean)}.
     * @param field the field
     * @param instance the instance
     */
    public static short getShort(@NotNull Field field, @Nullable Object instance) {
        Objects.requireNonNull(field);
        return NativeAccessor.getShort(field, instance);
    }

    /**
     * Get value of a field without calling {@link Field#setAccessible(boolean)}.
     * <p><strong>WARNING: If you are trying to call this method with a field of primitive type, you should rethink your life.</strong>
     * @param field the field
     * @param instance the instance
     */
    public static Object getObject(@NotNull Field field, @Nullable Object instance) {
        Objects.requireNonNull(field);
        return NativeAccessor.getObject(field, instance);
    }

    public static Object get(@NotNull Field field, @Nullable Object instance) {
        Objects.requireNonNull(field);
        if (field.getType() == boolean.class) {
            return NativeAccessor.getBoolean(field, instance);
        } else if (field.getType() == byte.class) {
            return NativeAccessor.getByte(field, instance);
        } else if (field.getType() == char.class) {
            return NativeAccessor.getChar(field, instance);
        } else if (field.getType() == double.class) {
            return NativeAccessor.getDouble(field, instance);
        } else if (field.getType() == float.class) {
            return NativeAccessor.getFloat(field, instance);
        } else if (field.getType() == int.class) {
            return NativeAccessor.getInt(field, instance);
        } else if (field.getType() == long.class) {
            return NativeAccessor.getLong(field, instance);
        } else if (field.getType() == short.class) {
            return NativeAccessor.getShort(field, instance);
        } else {
            return NativeAccessor.getObject(field, instance);
        }
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static void invokeVoid(@NotNull Method method, @Nullable Object instance, Object... args) {
        Objects.requireNonNull(method);
        NativeAccessor.invokeVoid(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static boolean invokeBoolean(@NotNull Method method, @Nullable Object instance, Object... args) {
        Objects.requireNonNull(method);
        return NativeAccessor.invokeBoolean(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static byte invokeByte(@NotNull Method method, @Nullable Object instance, Object... args) {
        Objects.requireNonNull(method);
        return NativeAccessor.invokeByte(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static char invokeChar(@NotNull Method method, @Nullable Object instance, Object... args) {
        Objects.requireNonNull(method);
        return NativeAccessor.invokeChar(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static double invokeDouble(@NotNull Method method, @Nullable Object instance, Object... args) {
        Objects.requireNonNull(method);
        return NativeAccessor.invokeDouble(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static float invokeFloat(@NotNull Method method, @Nullable Object instance, Object... args) {
        Objects.requireNonNull(method);
        return NativeAccessor.invokeFloat(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static int invokeInt(@NotNull Method method, @Nullable Object instance, Object... args) {
        Objects.requireNonNull(method);
        return NativeAccessor.invokeInt(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static long invokeLong(@NotNull Method method, @Nullable Object instance, Object... args) {
        Objects.requireNonNull(method);
        return NativeAccessor.invokeLong(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static short invokeShort(@NotNull Method method, @Nullable Object instance, Object... args) {
        Objects.requireNonNull(method);
        return NativeAccessor.invokeShort(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static Object invokeObject(@NotNull Method method, @Nullable Object instance, Object... args) {
        Objects.requireNonNull(method);
        return NativeAccessor.invokeObject(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    @Contract("_, _, _ -> _")
    public static Object invoke(@NotNull Method method, @Nullable Object instance, Object... args) {
        Objects.requireNonNull(method);
        if (method.getReturnType() == void.class) {
            invokeVoid(method, instance, args);
            return getVoid();
        } else if (method.getReturnType() == boolean.class) {
            return NativeAccessor.invokeBoolean(method, instance, args);
        } else if (method.getReturnType() == byte.class) {
            return NativeAccessor.invokeByte(method, instance, args);
        } else if (method.getReturnType() == char.class) {
            return NativeAccessor.invokeChar(method, instance, args);
        } else if (method.getReturnType() == double.class) {
            return NativeAccessor.invokeDouble(method, instance, args);
        } else if (method.getReturnType() == float.class) {
            return NativeAccessor.invokeFloat(method, instance, args);
        } else if (method.getReturnType() == int.class) {
            return NativeAccessor.invokeInt(method, instance, args);
        } else if (method.getReturnType() == long.class) {
            return NativeAccessor.invokeLong(method, instance, args);
        } else if (method.getReturnType() == short.class) {
            return NativeAccessor.invokeShort(method, instance, args);
        } else {
            return NativeAccessor.invokeObject(method, instance, args);
        }
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static void invokeNonvirtualVoid(@NotNull Method method, @NotNull Object instance, Object... args) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(instance);
        NativeAccessor.invokeNonvirtualVoid(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static boolean invokeNonvirtualBoolean(@NotNull Method method, @NotNull Object instance, Object... args) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(instance);
        return NativeAccessor.invokeNonvirtualBoolean(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static byte invokeNonvirtualByte(@NotNull Method method, @NotNull Object instance, Object... args) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(instance);
        return NativeAccessor.invokeNonvirtualByte(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static char invokeNonvirtualChar(@NotNull Method method, @NotNull Object instance, Object... args) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(instance);
        return NativeAccessor.invokeNonvirtualChar(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static double invokeNonvirtualDouble(@NotNull Method method, @NotNull Object instance, Object... args) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(instance);
        return NativeAccessor.invokeNonvirtualDouble(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static float invokeNonvirtualFloat(@NotNull Method method, @NotNull Object instance, Object... args) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(instance);
        return NativeAccessor.invokeNonvirtualFloat(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static int invokeNonvirtualInt(@NotNull Method method, @NotNull Object instance, Object... args) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(instance);
        return NativeAccessor.invokeNonvirtualInt(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static long invokeNonvirtualLong(@NotNull Method method, @NotNull Object instance, Object... args) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(instance);
        return NativeAccessor.invokeNonvirtualLong(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static short invokeNonvirtualShort(@NotNull Method method, @NotNull Object instance, Object... args) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(instance);
        return NativeAccessor.invokeNonvirtualShort(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    public static Object invokeNonvirtualObject(@NotNull Method method, @NotNull Object instance, Object... args) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(instance);
        return NativeAccessor.invokeNonvirtualObject(method, instance, args);
    }

    /**
     * Invoke a method without calling {@link Method#setAccessible(boolean)}.
     * @param method the method
     * @param instance the instance
     */
    @Contract("_, _, _ -> _")
    public static Object invokeNonvirtual(@NotNull Method method, @NotNull Object instance, Object... args) {
        Objects.requireNonNull(method);
        Objects.requireNonNull(instance);
        if (method.getReturnType() == void.class) {
            invokeNonvirtualVoid(method, instance, args);
            return getVoid();
        } else if (method.getReturnType() == boolean.class) {
            return NativeAccessor.invokeNonvirtualBoolean(method, instance, args);
        } else if (method.getReturnType() == byte.class) {
            return NativeAccessor.invokeNonvirtualByte(method, instance, args);
        } else if (method.getReturnType() == char.class) {
            return NativeAccessor.invokeNonvirtualChar(method, instance, args);
        } else if (method.getReturnType() == double.class) {
            return NativeAccessor.invokeNonvirtualDouble(method, instance, args);
        } else if (method.getReturnType() == float.class) {
            return NativeAccessor.invokeNonvirtualFloat(method, instance, args);
        } else if (method.getReturnType() == int.class) {
            return NativeAccessor.invokeNonvirtualInt(method, instance, args);
        } else if (method.getReturnType() == long.class) {
            return NativeAccessor.invokeNonvirtualLong(method, instance, args);
        } else if (method.getReturnType() == short.class) {
            return NativeAccessor.invokeNonvirtualShort(method, instance, args);
        } else {
            return NativeAccessor.invokeNonvirtualObject(method, instance, args);
        }
    }

    /**
     * Create new instance without calling {@link Constructor#setAccessible(boolean)}.
     * @param constructor the constructor to call
     */
    @NotNull
    public static <T> T newInstance(@NotNull Constructor<T> constructor, Object... args) {
        Objects.requireNonNull(constructor);
        return NativeAccessor.newInstance(constructor, args);
    }

    @Contract(pure = true)
    @NotNull
    public static Method getStaticMethod(@NotNull Class<?> clazz, @NotNull String name, @NotNull String signature) throws NoSuchElementException {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(name);
        Objects.requireNonNull(signature);
        return NativeAccessor.getStaticMethod(clazz, name, signature);
    }

    @Contract(pure = true)
    @NotNull
    public static Method getNonstaticMethod(@NotNull Class<?> clazz, @NotNull String name, @NotNull String signature) throws NoSuchElementException {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(name);
        Objects.requireNonNull(signature);
        return NativeAccessor.getNonstaticMethod(clazz, name, signature);
    }

    @Contract(pure = true)
    @NotNull
    public static Method getMethod(@NotNull Class<?> clazz, @NotNull String name, @NotNull String signature) throws NoSuchElementException {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(name);
        Objects.requireNonNull(signature);
        try {
            return NativeAccessor.getStaticMethod(clazz, name, signature);
        } catch (NoSuchElementException ignore) {
            return NativeAccessor.getNonstaticMethod(clazz, name, signature);
        }
    }

    @Contract(pure = true)
    @NotNull
    public static Field getStaticField(@NotNull Class<?> clazz, @NotNull String name, @NotNull String signature) throws NoSuchElementException {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(name);
        Objects.requireNonNull(signature);
        return NativeAccessor.getStaticField(clazz, name, signature);
    }

    @Contract(pure = true)
    @NotNull
    public static Field getNonstaticField(@NotNull Class<?> clazz, @NotNull String name, @NotNull String signature) throws NoSuchElementException {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(name);
        Objects.requireNonNull(signature);
        return NativeAccessor.getNonstaticField(clazz, name, signature);
    }

    @Contract(pure = true)
    @NotNull
    public static Field getField(@NotNull Class<?> clazz, @NotNull String name, @NotNull String signature) throws NoSuchElementException {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(name);
        Objects.requireNonNull(signature);
        try {
            return NativeAccessor.getStaticField(clazz, name, signature);
        } catch (NoSuchElementException ignore) {
            return NativeAccessor.getNonstaticField(clazz, name, signature);
        }
    }

    @NotNull
    public static Class<?> defineClass(@NotNull String name, @NotNull ClassLoader classLoader, byte[] buf, int len) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(classLoader);
        Objects.requireNonNull(buf);
        return NativeAccessor.defineClass(name, classLoader, buf, len);
    }

    public static void forceGarbageCollection() {
        NativeAccessor.forceGarbageCollection();
    }

    @NotNull
    public static Class<?>[] getLoadedClasses() {
        return NativeAccessor.getLoadedClasses();
    }

    @NotNull
    public static Class<?>[] getClassLoaderClasses(@NotNull ClassLoader classLoader) {
        return NativeAccessor.getClassLoaderClasses(classLoader);
    }

    public static void retransformClasses(@NotNull Class<?>[] classes) {
        Objects.requireNonNull(classes);
        NativeAccessor.retransformClasses(classes);
    }

    public static long getObjectSize(@NotNull Object o) {
        Objects.requireNonNull(o);
        return NativeAccessor.getObjectSize(o);
    }

    public static int getObjectHashcode(@NotNull Object o) {
        Objects.requireNonNull(o);
        return NativeAccessor.getObjectHashcode(o);
    }

    public static void registerClassLoadHook(@NotNull ClassLoadHook classLoadHook) {
        Objects.requireNonNull(classLoadHook);
        NativeAccessor.registerClassLoadHook(classLoadHook);
    }
}
