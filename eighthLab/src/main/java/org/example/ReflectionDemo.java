package org.example;

import org.example.Classes.Human;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionDemo {
    public int countHumans(List<Object> objects) {
        int count = 0;
        for (Object obj : objects) {
            if (obj instanceof Human) {
                count++;
            }
        }
        return count;
    }

    public List<String> getPublicMethods(Object object) {
        List<String> listPublicNames = new ArrayList<>();

        if (object == null) {
            return listPublicNames;
        }

        Method[] methods = object.getClass().getMethods();

        for (Method m : methods) {
            listPublicNames.add(m.getName());
        }

        return listPublicNames;

    }

    public List<String> getSuperClassNames(Object object) {
        List<String> classNames = new ArrayList<>();

        if (object == null) {
            return classNames;
        }

        Class<?> currentClass = object.getClass();

        while (currentClass != null) {
            classNames.add(currentClass.getName());
            currentClass = currentClass.getSuperclass();
        }

        return classNames;
    }

    public int countExecutableObj(List<Object> objectList) {
        int count = 0;

        for (Object obj : objectList) {
            if (obj instanceof Executable) {
                ((Executable) obj).execute();
                count++;
            }
        }

        return count;
    }

    public List<String> getGetterAndSetter(Object object) {
        List<String> result = new ArrayList<>();

        if (object == null) {
            return result;
        }

        Method[] methods = object.getClass().getMethods();

        for (Method method : methods) {
            int mod = method.getModifiers();

            if (Modifier.isPublic(mod) && !Modifier.isStatic(mod)) {
                String nameMethod = method.getName();

                if (nameMethod.startsWith("get") &&
                        method.getParameterCount() == 0 &&
                        !method.getReturnType().equals(void.class)) {
                    result.add(nameMethod);
                }

                if (nameMethod.startsWith("set") &&
                        method.getParameterCount() == 1 &&
                        method.getReturnType().equals(void.class)) {
                    result.add(nameMethod);
                }
            }
        }

        return result;
    }
}
