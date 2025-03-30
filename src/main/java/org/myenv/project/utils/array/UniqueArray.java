package org.myenv.project.utils.array;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Objects.isNull;

public class UniqueArray<T> {

    T[] array;

    public boolean contains(T element) {
        return Arrays.asList(array).contains(element);
    }

    public T get(int index) {
        return array[index];
    }

    public boolean add(T element) {
        if (contains(element)) return false;
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = element;
        return true;
    }

    public boolean remove(T element) {
        if (!contains(element)) return false;
        ArrayList<T> arrayListRemove = convertToArrayList();
        arrayListRemove.remove(element);
        this.array = (T[]) arrayListRemove.toArray();
        return true;
    }

    private ArrayList<T> convertToArrayList() {
        return isNull(this.array) ? new ArrayList<>() : new ArrayList<>(Arrays.asList(array));
    }
}
