package pojo;

public class User {
    private String name;

    public User() {
        System.out.println("通过无参构造器构造对象");
    }

//    public User(String name) {
//        this.name = name;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
