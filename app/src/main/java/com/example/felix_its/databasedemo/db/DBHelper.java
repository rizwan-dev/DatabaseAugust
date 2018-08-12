package com.example.felix_its.databasedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "employee.sqlite";
    private static final  int DB_VERSION = 1;

    private static final String EMP_TABLE = "employee";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String SALARY = "salary";

    // Database creation
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    // Table creation
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table "+EMP_TABLE
                + " ( "+ID + " integer primary key autoincrement, "
                +NAME + " text, "+ADDRESS + " text, "+ PHONE + " text, "+
                SALARY + " integer )";

        Log.e("DBQuery", "========"+query);

        sqLiteDatabase.execSQL(query);

    }

    // Add Employee
    public boolean addEmployee(Employee employee){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, employee.getName());
        values.put(ADDRESS, employee.getAddress());
        values.put(PHONE, employee.getPhone());
        values.put(SALARY, employee.getSalary());

        long no = db.insert(EMP_TABLE, null, values);

        db.close();

        if(no == 0){
            return false;
        }
        else {
            return true;
        }
    }

    // Get All Employee
    public List<Employee> getAllEmployee(){
        List<Employee> employees = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from "+EMP_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String address = cursor.getString(cursor.getColumnIndex(ADDRESS));
            String phone = cursor.getString(cursor.getColumnIndex(PHONE));
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            int salary = cursor.getInt(cursor.getColumnIndex(SALARY));
            Employee employee = new Employee(name, address, phone, salary);
            employee.setId(id);
            employees.add(employee);
        }

        if(cursor!=null && !cursor.isClosed()){
            cursor.close();
        }
        db.close();
        return employees;
    }

    // Delete Employee
    public boolean deleteEmployee(int id){
        SQLiteDatabase db = getWritableDatabase();
        long row = db.delete(EMP_TABLE,ID + " = "+id, null);

        if(row>0){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
