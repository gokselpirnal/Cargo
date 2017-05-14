package com.gokselpirnal.cargo.cargo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gokselpirnal on 14/05/2017.
 */

public class Cargo {

    private static List<AddressObject> adresses = new ArrayList<>();

    public static void openTheBranch(Object obj) {
        if (getAdresses(obj).isEmpty()) {
            List<Method> methods = findAddresses(obj);

            if (!methods.isEmpty()) {
                for (Method method : methods) {
                    AddressObject addressObject = new AddressObject();
                    addressObject.setName(obj.getClass().getName());
                    addressObject.setObj(obj);
                    addressObject.setMethod(method);
                    addressObject.setParam(method.getParameterTypes()[0].getName());
                    adresses.add(addressObject);
                }
            }

        }

    }

    public static void closeTheBranch(Object object) {
        for (AddressObject addressObject : getAdresses(object)) {
            adresses.remove(addressObject);
        }
    }

    public static void deliver(final Object pkg) {
        for (final AddressObject addressObject : adresses) {
            if (addressObject.getParam().equals(pkg.getClass().getName())) {
                try {
                    addressObject.getMethod().invoke(addressObject.getObj(), pkg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static List<AddressObject> getAdresses(Object object) {
        List<AddressObject> addressObjects = new ArrayList<>();

        for (AddressObject addressObject : Cargo.adresses) {
            if (addressObject.getName().equals(object.getClass().getName())) {
                addressObjects.add(addressObject);
            }
        }

        return addressObjects;
    }


    public static List<Method> findAddresses(Object object) {
        List<Method> methods = new ArrayList<>();
        for (Method method : object.getClass().getDeclaredMethods()) {
            Address annotation = method.getAnnotation(Address.class);

            if (annotation != null) {
                methods.add(method);
            }
        }

        return methods;
    }

    private static class AddressObject {
        private String name;
        private Object obj;
        private Method method;
        private String param;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }
    }


}
