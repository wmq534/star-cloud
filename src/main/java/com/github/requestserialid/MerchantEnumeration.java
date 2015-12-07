package com.github.requestserialid;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;

public class MerchantEnumeration<T> implements Enumeration<T> {

        private Enumeration<T> source = null;

        private List<T> addNames = null;

        private boolean endSource = false;

        private int index = 0;

        MerchantEnumeration(Enumeration<T> enumeration) {
            this.source = enumeration;
        }


        @Override
        public boolean hasMoreElements() {
            if (source.hasMoreElements())
                return true;
            endSource = true;
            if (addNames == null)
                return false;
            return addNames.size() > index ? true : false;
        }

        @Override
        public T nextElement() {
            if (!endSource) {
                return source.nextElement();
            }
            if (addNames == null) {
                throw new NoSuchElementException();
            }
            if (addNames.size() <= index) {
                throw new NoSuchElementException();
            }
            T result = addNames.get(index++);
            return result;

        }

        public void addNames(T o) {
            if (o == null)
                return;
            if (addNames == null)
                addNames = new ArrayList<T>();
            addNames.add(o);
        }
    }