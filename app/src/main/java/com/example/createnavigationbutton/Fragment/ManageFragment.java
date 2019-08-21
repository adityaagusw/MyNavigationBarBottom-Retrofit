package com.example.createnavigationbutton.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.createnavigationbutton.BackEnd.ApiClient;
import com.example.createnavigationbutton.BackEnd.ApiInterface;
import com.example.createnavigationbutton.Models.Mahasiswa;
import com.example.createnavigationbutton.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageFragment extends Fragment {

    EditText edtNama, edtEmail, edtPassword;
    Button btnKirimData;

    //untuk memanggil layout fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manage, container, false);
    }

    //untuk membuat onCreate seperti biasa
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtNama = view.findViewById(R.id.edtNama);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);

        btnKirimData = view.findViewById(R.id.btnKirimData);

        btnKirimData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = edtNama.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (nama.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "Isi dulu semua nya", Toast.LENGTH_SHORT).show();
                } else {
                    sendData();
                }

            }
        });

    }

    private void sendData() {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("Loading ...");
        pd.setMessage("Sedang Mengirim Data");
        pd.setCancelable(false);
        pd.show();

        String nama = edtNama.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mahasiswa> call = apiInterface.tambahMahasiswa(nama, email, password);

        call.enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                pd.dismiss();
                Toast.makeText(getActivity(), "Berhasil", Toast.LENGTH_SHORT).show();
                edtNama.setText("");
                edtEmail.setText("");
                edtPassword.setText("");
            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    /*
    Catatan :
    untuk membuat layout seperti ini, dipastikan contextnya adalah getActivity() / getApplicationContext()
    tapi biasanya ya getActivity()
    */


}
