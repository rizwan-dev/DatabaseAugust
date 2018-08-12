package com.example.felix_its.databasedemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.felix_its.databasedemo.R;
import com.example.felix_its.databasedemo.db.DBHelper;
import com.example.felix_its.databasedemo.db.Employee;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    EditText edtName, edtAddress, edtPhone, edtSalary;
    Button btnSave;


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        edtName = view.findViewById(R.id.edtName);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtSalary = view.findViewById(R.id.edtSalary);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String salaryString = edtSalary.getText().toString().trim();

                int salary = Integer.parseInt(salaryString);

                Employee employee = new Employee(name, address, phone, salary);

                DBHelper dbHelper = new DBHelper(getActivity());
                boolean isAdded = dbHelper.addEmployee(employee);
                if(isAdded){
                    Toast.makeText(getActivity(), "Record added", Toast.LENGTH_SHORT).show();
                }

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
