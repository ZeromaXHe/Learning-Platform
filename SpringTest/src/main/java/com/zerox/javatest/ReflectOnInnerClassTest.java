package com.zerox.javatest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
        public InnerContainer() {
        }

        private class InnerA {
            private String f = InnerA.class.getSimpleName();

            public InnerA() {
            }
        }

        private static class InnerB {
            private String f = InnerB.class.getSimpleName();

            public InnerB() {
            }
        }

        private Runnable r = () -> System.out.println("Method run of Runnable r");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException,
            NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        printInner();
        printAllReflections("com.zerox.javatest.ReflectOnInnerClassTest$InnerContainer");
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
        System.out.println();
        printMethods(cl);
        System.out.println();
        printFields(cl);
        System.out.println();
        System.out.println("}");
    }

    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();

        for (Constructor c : constructors) {
            String name = c.getName();
            System.out.print("   ");
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

    public static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();

        for (Method m : methods) {
            Class retType = m.getReturnType();
            String name = m.getName();

            System.out.print("   ");
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

    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();

        for (Field f : fields) {
            Class type = f.getType();
            String name = f.getName();
            System.out.print("   ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(type.getName() + " " + name + ";");
        }
    }

    private static void printInner() throws InstantiationException, IllegalAccessException, NoSuchFieldException,
            NoSuchMethodException, InvocationTargetException {
        Class clazz = InnerContainer.class;
        InnerContainer container = (InnerContainer) clazz.newInstance();
        Class[] innerClazz = clazz.getDeclaredClasses();
        for (Class cls : innerClazz) {
            System.out.println(cls.getName());
            int mod = cls.getModifiers();
            String modifier = Modifier.toString(mod);
            if (modifier.contains("static")) {
                //构造静态内部类实例
//              Constructor con1 = cls.getDeclaredConstructor();
                Object obj1 = cls.newInstance();
                Field field1 = cls.getDeclaredField("f");
                field1.setAccessible(true);
                System.out.println(field1.get(obj1));
            } else {
                // 构造成员内部类实例
                Constructor con2 = cls.getDeclaredConstructor(clazz);
                con2.setAccessible(true);
                Object obj2 = con2.newInstance(container);
                Field field2 = cls.getDeclaredField("f");
                field2.setAccessible(true);
                System.out.println(field2.get(obj2));
            }
        }
        // 获取匿名内部类实例
        Field field = clazz.getDeclaredField("r");
        field.setAccessible(true);
        Runnable r = (Runnable) field.get(container);
        r.run();
    }
}
