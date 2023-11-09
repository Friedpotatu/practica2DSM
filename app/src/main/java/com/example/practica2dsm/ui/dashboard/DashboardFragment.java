package com.example.practica2dsm.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.practica2dsm.R;
import com.example.practica2dsm.databinding.FragmentDashboardBinding;
import com.example.practica2dsm.ui.home.HomeFragment;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private EditText txt_rut, txt_name2, txt_age;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txt_rut = binding.txtRut;
        txt_age = binding.txtAge;
        txt_name2 = binding.txtNombre2;

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOnSharedPreference();
                Toast.makeText(getContext(), "Datos guardados.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchUser();
                Toast.makeText(getContext(), "Datos cargados.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnPrevios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_navigation_dashboard_to_navigation_home);
            }
        });

        return root;
    }

    private void previosUser() {
    }

    private void searchUser() {
        SharedPreferences preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String nombre = preferences.getString(txt_rut.getText().toString(), "");
        String edad = preferences.getString(txt_rut.getText().toString()+"a", "");
        txt_name2.setText(nombre);
        txt_age.setText(edad);
    }

    private void saveOnSharedPreference() {
        SharedPreferences pref = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        edt.putString(txt_rut.getText().toString(), txt_name2.getText().toString());
        edt.putString(txt_rut.getText().toString()+"a", txt_age.getText().toString());
        edt.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}