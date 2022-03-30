package com.zerox.javatest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @Author: zhuxi
 * @Time: 2022/3/29 10:08
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class ReflectOnInnerClassTest {
    static class InnerContainer {
        public void localInnerTest() {
            // 局部内部类
            class LocalInner {
                private String f = LocalInner.class.getSimpleName();

                public void test() {
                    System.out.println(f);
                }
            }

            new LocalInner().test();
        }

        // 普通内部类
        class Inner {
            private String f = Inner.class.getSimpleName();
        }

        // 静态内部类
        static class StaticInner {
            private String f = StaticInner.class.getSimpleName();
        }

        // 匿名内部类
        Runnable anonymousInner = new Runnable() {
            @Override
            public void run() {
                System.out.println("Method run of Runnable anonymousInner");
            }
        };

        Runnable lambda = () -> System.out.println("Method run of Runnable lambda");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        printAllReflections("com.zerox.javatest.ReflectOnInnerClassTest$InnerContainer");
        printAllReflections("com.zerox.javatest.ReflectOnInnerClassTest$InnerContainer$Inner");
        printAllReflections("com.zerox.javatest.ReflectOnInnerClassTest$InnerContainer$StaticInner");
    }

    public static void printAllReflections(String className) throws ClassNotFoundException {
        Class cl = Class.forName(className);
        Class superCl = cl.getSuperclass();
        String modifiers = Modifier.toString(cl.getModifiers());
        if (modifiers.length() > 0) {
            System.out.print(modifiers + " ");
        }
        System.out.print("class " + className);
        if (superCl != null && superCl != Object.class) {
            System.out.print(" extends " + superCl.getName());
        }
        System.out.print("\n{\n");
        printConstructors(cl);
        printMethods(cl);
        printFields(cl);
        printInner(cl);
        System.out.println("}");
    }

    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 0) {
            System.out.println("    构造方法：");
            for (Constructor c : constructors) {
                String name = c.getName();
                System.out.print("    ");
                String modifiers = Modifier.toString(c.getModifiers());
                if (modifiers.length() > 0) {
                    System.out.print(modifiers + " ");
                }
                System.out.print(name + "(");

                Class[] paramTypes = c.getParameterTypes();
                for (int i = 0; i < paramTypes.length; i++) {
                    if (i > 0) {
                        System.out.print(", ");
                    }
                    System.out.print(paramTypes[i].getName());
                }
                System.out.println(");");
            }
        }
    }

    public static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        if (methods.length > 0) {
            System.out.println("    方法：");
            for (Method m : methods) {
                Class retType = m.getReturnType();
                String name = m.getName();

                System.out.print("    ");
                String modifiers = Modifier.toString(m.getModifiers());
                if (modifiers.length() > 0) {
                    System.out.print(modifiers + " ");
                }
                System.out.print(retType.getName() + " " + name + "(");

                Class[] paramTypes = m.getParameterTypes();
                for (int i = 0; i < paramTypes.length; i++) {
                    if (i > 0) {
                        System.out.print(", ");
                    }
                    System.out.print(paramTypes[i].getName());
                }
                System.out.println(");");
            }
        }
    }

    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();
        if (fields.length > 0) {
            System.out.println("    成员变量：");
            for (Field f : fields) {
                Class type = f.getType();
                String name = f.getName();
                System.out.print("    ");
                String modifiers = Modifier.toString(f.getModifiers());
                if (modifiers.length() > 0) {
                    System.out.print(modifiers + " ");
                }
                System.out.println(type.getName() + " " + name + ";");
            }
        }
    }

    private static void printInner(Class cl) {
        Class[] innerClazz = cl.getDeclaredClasses();
        if (innerClazz.length > 0) {
            System.out.println("    内部类：");
            for (Class cls : innerClazz) {
                System.out.print("    ");
                String modifiers = Modifier.toString(cls.getModifiers());
                if (modifiers.length() > 0) {
                    System.out.print(modifiers + " ");
                }
                System.out.println(cls.getName());
            }
        }
    }
}
