package com.example.createnavigationbutton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.createnavigationbutton.BackEnd.ApiClient;
import com.example.createnavigationbutton.BackEnd.ApiInterface;
import com.example.createnavigationbutton.Models.Mahasiswa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {

    EditText upNama, upEmail, upPassword;
    Button btnUpdateData;

    String nama, email, password;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        upNama = findViewById(R.id.editNama);
        upEmail = findViewById(R.id.editEmail);
        upPassword = findViewById(R.id.editPassword);
        btnUpdateData = findViewById(R.id.btnUpdateData);

        //parsing data intent ke intent selanjutnya
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        nama = intent.getStringExtra("nama");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        upNama.setText(nama);
        upEmail.setText(email);
        upPassword.setText(password);

        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = upNama.getText().toString().trim();
                String email = upEmail.getText().toString().trim();
                String password = upPassword.getText().toString().trim();

                if (nama.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(EditActivity.this, "Isi Semua Dulu", Toast.LENGTH_SHORT).show();
                } else{
                    updateDataUser(id);
                }
            }
        });

    }
    private void updateDataUser(final int id) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Loading ...");
        pd.setMessage("Sedang Update Data");
        pd.setCancelable(false);
        pd.show();

        String nama = upNama.getText().toString();
        String email = upEmail.getText().toString();
        String password = upPassword.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mahasiswa> call = apiInterface.updateMahasiswa(id, nama, email, password);

        call.enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                pd.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(EditActivity.this, message, Toast.LENGTH_SHORT).show();
                    upNama.setText("");
                    upEmail.setText("");
                    upPassword.setText("");
                } else {
                    Toast.makeText(EditActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(EditActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
