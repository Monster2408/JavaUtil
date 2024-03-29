package xyz.mlserver.javautil;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Serializer implements Serializable {
    public static final long serialVersionUID = 2L;

    private final Object object;

    public Serializer(Object o) {
        this.object = o;
    }

    @NotNull
    public static Serializer fromString(String s) {
        try {
            byte[] data = Base64.getDecoder().decode(s);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Object o = ois.readObject();
            ois.close();
            return new Serializer(o);
        } catch (IOException | ClassNotFoundException e) {
            SneakyThrow.sneaky(e);
            return null;
        }
    }

    @NotNull
    public String asString() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this.object);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            SneakyThrow.sneaky(e);
            return null;
        }
    }

    public Object getObject() { return object; }

    @SuppressWarnings("unchecked")
    public <T> T get() { return (T) object; }
}
