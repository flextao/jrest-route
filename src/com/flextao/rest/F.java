
package com.flextao.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class F {
    public static String read(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuffer buffer = new StringBuffer();
        try {
            while (true) {
                char[] cbuf = new char[1024];
                int len = reader.read(cbuf);
                if (len == -1) {
                    break;
                }
                buffer.append(cbuf, 0, len);
            }
        } finally {
            in.close();
        }
        return buffer.toString();
    }

    public static String uri(String... args) {
        List<String> paths = new ArrayList<String>();
        for (int i = 0; i < args.length; i++) {
            String path = args[i].replaceAll("(\\/$)|(\\\\$)", "");
            if (i > 0) {
                path = path.replaceAll("(^\\/)|(^\\\\)", "");
            }
            paths.add(path);
        }
        return join(paths, "/");
    }

    public static boolean isBlank(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).length() == 0;
        }
        if (obj instanceof Collection<?>) {
            return ((Collection<?>) obj).isEmpty();
        }
        return false;
    }

    public static String join(Collection<?> collections, String sep) {
        StringBuffer result = new StringBuffer();
        for (Iterator<?> iter = collections.iterator(); iter.hasNext();) {
            result.append(iter.next());
            if (iter.hasNext()) {
                result.append(sep);
            }
        }
        return result.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T invoke(Method m, Object obj, Object... args) {
        boolean isNotSingleArrayParam = m.getParameterTypes().length != 1 || !m.getParameterTypes()[0].isArray();
        boolean isSingleArrayArgs = args.length == 1 && args[0] instanceof Object[];
        if (isSingleArrayArgs && isNotSingleArrayParam) {
            args = (Object[]) args[0];
        }
        try {
            m.setAccessible(true);
            return (T) m.invoke(obj, args);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            } else {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    public static Method getMethod(Class<?> type, String methodName, Class<?>... paramTypes) {
        try {
            return type.getMethod(methodName, paramTypes);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            if (paramTypes.length == 0) {
                for (Method m : type.getMethods()) {
                    if (m.getName().equals(methodName)) {
                        return m;
                    }
                }
            }
            throw new RuntimeException(e);
        }
    }

    public static Class<?> forName(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Calendar calendar(int year, int month, int day) {
        if (day < 1) {
            throw new IllegalArgumentException("Do you really understand what's mean day == 0 in java Calendar?");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        return startOfDate(calendar);
    }

    public static Date date(int year, int month, int date) {
        return calendar(year, month, date).getTime();
    }

    public static Calendar startOfDate(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
}
