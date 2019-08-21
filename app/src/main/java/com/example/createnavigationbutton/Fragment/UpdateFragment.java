package com.example.createnavigationbutton.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.createnavigationbutton.Adapter.MahasiswaAdapter;
import com.example.createnavigationbutton.BackEnd.ApiClient;
import com.example.createnavigationbutton.BackEnd.ApiInterface;
import com.example.createnavigationbutton.Models.Mahasiswa;
import com.example.createnavigationbutton.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateFragment extends Fragment {

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private List<Mahasiswa> mahasiswaList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewUpdate);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("Loading ...");
        pd.setMessage("Sedang Load Data");
        pd.setCancelable(false);
        pd.show();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mahasiswa> call = apiInterface.lihatMahasiswa();

        call.enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                pd.dismiss();
                mahasiswaList = response.body().getResult();
                adapter = new MahasiswaAdapter(getActivity(),mahasiswaList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {
                pd.dismiss();

            }
        });


    }

}
