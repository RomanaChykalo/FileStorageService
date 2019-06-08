package model;

import java.io.Serializable;

public class UserFile implements Serializable {
    private String name;
    private Type type;
    private int size;

    public UserFile(String name, Type type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public UserFile() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFile userFile = (UserFile) o;

        if (!name.equals(userFile.name)) return false;
        if (!type.equals(userFile.type)) return false;
        return size==(userFile.size);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        return "model.UserFile{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", size=" + size +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
