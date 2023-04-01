package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

class TraceHandler implements InvocationHandler {

    private Object target;

    public TraceHandler(Object t) {
        target = t;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        // print method name and parameters ...
        // invoke actual method
        System.out.println("proxy class name:" + proxy.getClass().getName());
        System.out.println(this.target + "." + m.getName() + " " + Arrays.deepToString(args));
        return m.invoke(target, args);
    }
}


public class BinarySearch {
    public static void main(String[] args) {
        // construct wrapper
        Class[] interfaces = new Class[]{Comparable.class};

        Object[] elements = new Object[1000];
        // fill elements with proxies for the integers 1 . . . 1000
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value); // construct proxy for one or more interfaces
            // 代理类是运行过程中动态创建的
            // All proxy classes extend the class Proxy

            // A proxy class has only one instance field—the invocation handler, which is defined in the Proxy superclass.

            // Any additional data required to carry out the proxy objects’ tasks must be stored in the invocation handler

            // All proxy classes override the toString, equals, and hashCode methods of the Object class. Like all
            // proxy methods, these methods simply call invoke on the invocation handler.
            // The other methods of the Object class (such as clone and getClass) are not redefined.

            elements[i] = Proxy.newProxyInstance(null, interfaces, handler); // proxy for value;
        }

        // construct a random integer
        Integer key = new Random().nextInt(elements.length) + 1;
        // search for the key
        int result = Arrays.binarySearch(elements, key);
        // print match if found
        if (result >= 0) System.out.println(elements[result]);
    }
}
