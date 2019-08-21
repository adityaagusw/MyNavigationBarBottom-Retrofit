package com.example.createnavigationbutton.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.createnavigationbutton.EditActivity;
import com.example.createnavigationbutton.Models.Mahasiswa;
import com.example.createnavigationbutton.R;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {

    private Context mCtx;
    private List<Mahasiswa> mahasiswaList;

    public MahasiswaAdapter(Context mCtx, List<Mahasiswa> mahasiswaList) {
        this.mCtx = mCtx;
        this.mahasiswaList = mahasiswaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.row_list, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.listNama.setText(mahasiswaList.get(i).getNama());
        myViewHolder.listEmail.setText(mahasiswaList.get(i).getEmail());
        myViewHolder.listPassword.setText(mahasiswaList.get(i).getPassword());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method click for send EditActivity.class

                Intent pindah = new Intent(v.getContext(), EditActivity.class);

                pindah.putExtra("id", mahasiswaList.get(i).getId());
                pindah.putExtra("nama", mahasiswaList.get(i).getNama());
                pindah.putExtra("email", mahasiswaList.get(i).getEmail());
                pindah.putExtra("password", mahasiswaList.get(i).getPassword());

                v.getContext().startActivity(pindah);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView listNama, listEmail, listPassword;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            listNama = itemView.findViewById(R.id.listNama);
            listEmail = itemView.findViewById(R.id.listEmail);
            listPassword = itemView.findViewById(R.id.listPassword);

        }
    }
}
