package com.cinema.persistence;

import java.io.*;
import java.util.List;

/**
 * Helper for serialization/deserialization using Java Serialization API.
 */
public class DataSerializer {

    public static void saveToFile(String fileName, List<?> data) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject();
        }
    }
}
