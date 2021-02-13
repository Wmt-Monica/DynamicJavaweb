package cn.dreamplume.jsp;

/**
 * @Classname People
 * @Description TODO
 * @Date 2021/2/12 17:17
 * @Created by 翊
 */
public class People {
    private String id;  // 人物序号
    private String name;  // 人物姓名
    private String age;  // 人物年龄
    private String position;  // 任务职业
    private String picture;  // 人物头像路径

    public People() {}

    public People(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", position='" + position + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
