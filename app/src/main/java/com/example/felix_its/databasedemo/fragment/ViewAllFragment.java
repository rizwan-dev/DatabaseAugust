package com.example.felix_its.databasedemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.felix_its.databasedemo.R;
import com.example.felix_its.databasedemo.adapter.EmployeeListAdapter;
import com.example.felix_its.databasedemo.db.DBHelper;
import com.example.felix_its.databasedemo.db.Employee;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewAllFragment extends Fragment {

    RecyclerView rvUsers;


    public ViewAllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_all, container, false);

        rvUsers = view.findViewById(R.id.rvUsers);

        rvUsers.setLayoutManager(new LinearLayoutManager(getActivity()));

        DBHelper dbHelper = new DBHelper(getActivity());

        List<Employee> employees = dbHelper.getAllEmployee();

        EmployeeListAdapter adapter = new EmployeeListAdapter(employees, dbHelper);

        rvUsers.setAdapter(adapter);

        return view;
    }

}
