package model;
import model.interfaces.Printable;

public abstract class BaseEntity implements Printable {
    protected int id;
    protected String name;
    protected BaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BaseEntity() {
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    public void printInfo() {
        System.out.println(getInfo());
    }

    public abstract String getInfo();
    public abstract void validate();
}



