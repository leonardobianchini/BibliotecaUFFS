package com.example.bianchini.bibliotecauffs.dominio.entidades;

import java.util.Date;

/**
 * Created by Bianchini on 13/06/2015.
 */
public class Livro {

    private long id;
    private String nome;
    private String autor;
    private Date data;

    //public livro(){

//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
