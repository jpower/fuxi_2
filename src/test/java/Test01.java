import org.junit.Test;

import java.util.HashMap;

/**
 * Created by 周大侠
 * 2019-01-17 9:54
 */
public class Test01 {
    @Test
    public void fun1(){
        HashMap map = new HashMap();
        map.put("1",new Person(1,"wmh"));
        map.put("1",new Person(2,"wyy"));
        for (Object value : map.values()) {
            System.out.println(value);
        }
    }
}
class Person{
    private int age;
    private String name;

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
