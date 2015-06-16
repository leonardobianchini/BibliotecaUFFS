package com.example.bianchini.bibliotecauffs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.example.bianchini.bibliotecauffs.database.DataBase;
import com.example.bianchini.bibliotecauffs.dominio.RepositorioLivro;
import com.example.bianchini.bibliotecauffs.dominio.entidades.Livro;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


public class actCadastro extends ActionBarActivity implements View.OnClickListener {

    private Button btCancela;
    private Button btAdiciona;
    private EditText edNome;
    private EditText edAutor;
    private EditText edData;

    private DataBase dataBase ;
    private SQLiteDatabase conn ;
    private RepositorioLivro repositorioLivro;
    private Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro);

        edNome = (EditText)findViewById(R.id.edNome);
        edAutor = (EditText)findViewById(R.id.edAutor);
        edData = (EditText)findViewById(R.id.edData);
        btCancela = (Button)findViewById(R.id.btCancela);
        btAdiciona = (Button)findViewById(R.id.btAdiciona);

        btAdiciona.setOnClickListener(this);
        btCancela.setOnClickListener(this);

        exibeDataListener listener = new exibeDataListener();

        edData.setOnClickListener( listener );
        edData.setOnFocusChangeListener(listener);

        livro = new Livro();

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioLivro = new RepositorioLivro(conn);

        } catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o Banco " + ex.getMessage());
            dlg.setNeutralButton("OK",null);
            dlg.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        if (v == btCancela){
            finish();
        } else {
            if (livro == null){
                inserir();
            }
            finish();
        }

    }

    private void inserir() {
        try {

            livro.setNome(edNome.getText().toString());
            livro.setAutor(edAutor.getText().toString());

            repositorioLivro.inserir(livro);
        } catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao inserir " + ex.getMessage());
            dlg.setNeutralButton("OK",null);
            dlg.show();
        }
    }

    private void exibeData(){

        Calendar calendar = Calendar.getInstance();

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this, new selecionaDataListener() , ano, mes, dia);
        dlg.show();

    }

    private class exibeDataListener implements View.OnClickListener, View.OnFocusChangeListener {

        @Override
        public void onClick(View v) {
            exibeData();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus){
                exibeData();
            }
        }
    }

    private class selecionaDataListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, monthOfYear, dayOfMonth);

            Date data = calendar.getTime();

            DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);

            String dt = format.format(data);

            edData.setText(dt);

            livro.setData(data);
        }
    }
}
