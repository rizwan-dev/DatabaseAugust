package com.example.felix_its.databasedemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.felix_its.databasedemo.R;
import com.example.felix_its.databasedemo.db.DBHelper;
import com.example.felix_its.databasedemo.db.Employee;

import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {

    List<Employee> employees;
    DBHelper dbHelper;

    public EmployeeListAdapter(List<Employee> employees, DBHelper dbHelper) {
        this.employees = employees;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_employee_list, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        final Employee employee = employees.get(position);
        holder.txtName.setText("Name : " + employee.getName());
        holder.txtAddress.setText("Address : " + employee.getAddress());
        holder.txtPhone.setText("Phone : " + employee.getPhone());
        holder.txtSalary.setText("Salary : " + employee.getSalary());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDeleted = dbHelper.deleteEmployee(employee.getId());
                if(isDeleted){
                    employees.remove(employee);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }
}
