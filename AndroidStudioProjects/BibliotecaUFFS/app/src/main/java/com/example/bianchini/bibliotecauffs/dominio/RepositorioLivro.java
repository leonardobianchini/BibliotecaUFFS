package com.example.bianchini.bibliotecauffs.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.widget.*;

import com.example.bianchini.bibliotecauffs.dominio.entidades.Livro;

/**
 * Created by Bianchini on 12/06/2015.
 */
public class RepositorioLivro {

    private SQLiteDatabase conn;
    public RepositorioLivro (SQLiteDatabase conn){
        this.conn = conn;
    }

    public void inserir(Livro livro){

        ContentValues values = new ContentValues();
        values.put("NOME", livro.getNome());
        values.put("AUTOR", livro.getAutor());
        values.put("DATA", livro.getData().getTime());

        conn.insertOrThrow("LIVRO", null, values);
    }

    public void testeInsere(){
        for (int i=0; i < 11 ; i++){
            ContentValues values = new ContentValues();
            values.put("NOME", "oi voce");
            conn.insertOrThrow("LIVRO", null, values);
        }
    }


    public ArrayAdapter<String> buscaContato(Context context){
        ArrayAdapter<String> adpLivros = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query("LIVRO", null, null, null, null, null, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                String nome = cursor.getString(1);
                adpLivros.add(nome);
            } while (cursor.moveToNext());
        }
        return adpLivros;
    }

}
