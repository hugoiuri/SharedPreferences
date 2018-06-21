package br.com.hugo.sharedpreferences;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText edtNome;
    private TextView txtName;

    private static final String PREFERENCES = "NomeArquivo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.edtNomeId);
        txtName = findViewById(R.id.txtNomeId);
    }

    public void print(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void onClickSalvar(View view) {
        String nome = edtNome.getText().toString();

        if (nome == null || nome.equals("")){
            print("Obrigatório informar nome!");
        } else {
            SharedPreferences shared = getSharedPreferences(PREFERENCES, 0);
            SharedPreferences.Editor editor = shared.edit();

            editor.putString("nome", nome);
            editor.commit();

            print("Dado salvo com sucesso!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences shared = getSharedPreferences(PREFERENCES, 0);

        if (shared.contains("nome")){
            txtName.setText(shared.getString("nome", "Texto não informado!"));
            print("Texto carredado com sucesso!");
        }
    }
}
