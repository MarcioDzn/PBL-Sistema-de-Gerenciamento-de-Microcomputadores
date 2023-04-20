package com.example.pbl.utils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ManipuladorArquivo{
    public static <T> void guardarBinario(List<T> obj, String nomearq){
        try {
            FileOutputStream file = new FileOutputStream(nomearq);
            file.write(new byte[0]); // Remove tudo antes de adicionar a nova lista

            ObjectOutputStream objStream = new ObjectOutputStream(file);

            objStream.writeObject(obj);
            objStream.flush();
            objStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> LinkedList<T> recuperarBinario(String nomearq){
        try {
            FileInputStream file = new FileInputStream(nomearq);
            ObjectInputStream objStream = new ObjectInputStream(file);

            return (LinkedList<T>) objStream.readObject();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
