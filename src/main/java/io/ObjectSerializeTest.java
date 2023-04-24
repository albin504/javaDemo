package io;

import java.io.*;

// serializing such an inner class instance will result in serialization of its associated outer class instance as well
// 序列化内部类，必须同时对外部类做序列化
public class ObjectSerializeTest implements Serializable {

    class Employer implements Serializable {
        public String name;
        public int age;

        Employer(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    class Manager extends Employer {
        public Employer secretary; // 每个经理都有一个秘书

        Manager(String name, int age) {
            super(name, age);
        }

    }

    class Secretary extends Employer {
        Secretary(String name, int age) {
            super(name, age);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectSerializeTest test = new ObjectSerializeTest();

        // 俩经理共用一个秘书。序列化时步骤：
        // 1. 给每一个对象引用分配一个serial number
        // 2. When encountering an object reference for the first time, save the object data to the output stream
        // 3. If it has been saved previously, just write “same as the previously saved object with serial number x.”
        Secretary secretary = test.new Secretary("mishu", 20);

        Manager employer1 = test.new Manager("zhangsan", 40);
        employer1.secretary = secretary;

        Manager employer2 = test.new Manager("lisi", 50);
        employer2.secretary = secretary;
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("/tmp/employer.log"));
        output.writeObject(employer1);
        output.writeObject(employer2);

        ObjectInputStream input = new ObjectInputStream(new FileInputStream("/tmp/employer.log"));
        Manager employer3 = (Manager) input.readObject();
        Manager employer4 = (Manager) input.readObject();
        System.out.println("经理1:" + employer3.name + " 的秘书叫 " + employer3.secretary.name); // zhangsan
        System.out.println("经理2:" + employer4.name + " 的秘书叫 " + employer4.secretary.name); // lisi
    }
}
