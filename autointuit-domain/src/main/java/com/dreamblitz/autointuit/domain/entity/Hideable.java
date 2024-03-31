package com.dreamblitz.autointuit.domain.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface Hideable {

    default void hideCommon(Object d1, Object d2) {
        Class<?> cls = d1.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                if(field.get(this) instanceof Hideable) {
                    Hideable h = (Hideable) field.get(this);
                    h.hideCommon(field.get(d1), field.get(d2));
                }
                if(field.get(this).equals( field.get(d1)) && field.get(d1).equals(field.get(d2))) {
                    field.set(this, null);
                    field.set(d1, null);
                    field.set(d2, null);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    default List<Object> getSubList(Object[] list, Field field) {
        List<Object> subList  = new ArrayList<>();
        try {
            for(Object obj :list) {
                if(field.get(obj) instanceof Hideable) {
                    subList.add(field.get(obj));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return subList;
    }

    default void hideCommonFromList(Object[] list) {

        int size  = list.length;
        if(size >1) {
            Class<?> cls = list[0].getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                try {
                    boolean skip = false;
                    for(Object obj :list) {
                        if(field.get(obj) == null ) {
                            skip = true;
                            break;
                        }
                    }
                    if(skip) {
                        continue;
                    }
                    List<Object> subList  = new ArrayList<>();
                    for(Object obj :list) {
                        if(field.get(obj) instanceof Hideable) {
                            subList.add(field.get(obj));
                        }
                    }
                    if(subList.size() == size) {
                        hideCommonFromList(subList.toArray());
                    }
                    int count =1;
                    for(int i = 1; i <size;i++){
                        if(field.get(list[0]).equals( field.get(list[i]))) {
                            count++;
                        }
                    }
                    if(count == size) {
                        for(int i = 0; i <size;i++){
                            field.set(list[i], null);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
