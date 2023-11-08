package com.example.practica2dsm.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.practica2dsm.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ListView lv_console;
    private EditText txt_name;
    private EditText txt_description;
    private Button btn_add;

    private ArrayList<String> consoleList;
    private ArrayAdapter<String> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        lv_console = binding.lvConsole;
        txt_name = binding.txtName;
        txt_description = binding.txtDescription;
        btn_add = binding.btnAdd;

        consoleList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, consoleList);

        lv_console.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txt_name.getText().toString();
                String description = txt_description.getText().toString();

                if (!name.isEmpty() && !description.isEmpty()) {
                    consoleList.add(name);
                    adapter.notifyDataSetChanged();
                    txt_name.setText("");
                    txt_description.setText("");
                    Toast.makeText(getContext(), "Consola agregada.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lv_console.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String console = ((TextView) view).getText().toString();
                Toast.makeText(getContext(), "Consola seleccionada: " + console, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}