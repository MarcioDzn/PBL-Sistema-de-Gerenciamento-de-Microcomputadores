package com.example.pbl.utils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ManipuladorArquivo{
    public static <T> void guardarBinario(List<T> obj, String nomearq){
        try {
            FileOutputStream arquivo = new FileOutputStream(nomearq);
            arquivo.write(new byte[0]); // Remove tudo antes de adicionar a nova lista

            ObjectOutputStream objArquivo = new ObjectOutputStream(arquivo);

            objArquivo.writeObject(obj);
            objArquivo.flush();
            objArquivo.close();

        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static <T> LinkedList<T> recuperarBinario(String nomearq){
        try {
            // Se o arquivo não existir cria um arquivo novo com uma lista vazia dentro
            File file = new File(nomearq);
            if (!file.exists()) {
                guardarBinario(new LinkedList<>(), nomearq);
            }
            
            FileInputStream arquivo = new FileInputStream(nomearq);
            ObjectInputStream objArquivo = new ObjectInputStream(arquivo);

            return (LinkedList<T>) objArquivo.readObject();

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);

        }
    }
}
