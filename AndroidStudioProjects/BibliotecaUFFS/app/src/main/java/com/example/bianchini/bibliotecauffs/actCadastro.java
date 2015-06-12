package com.example.bianchini.bibliotecauffs;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class actCadastro extends ActionBarActivity implements View.OnClickListener {

    private Button btCancela;
    private Button btAdiciona;
    private EditText edNome;
    private EditText edAutor;
    private EditText edData;

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
        finish();
    }
}
