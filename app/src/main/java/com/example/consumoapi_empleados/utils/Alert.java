package com.example.consumoapi_empleados.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.consumoapi_empleados.R;

public class Alert {

    private TextView textAlert;
    private Button btnOk;
    Context context;
    public Alert(Context context){this.context = context;}

    public void getAlert(String text){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.alert_dialog,null);
        textAlert = view.findViewById(R.id.textAlert);
        textAlert.setText(text);
        btnOk = view.findViewById(R.id.btnOk);
        alert.setView(view);
        AlertDialog dialog = alert.setCancelable(false).create();
        dialog.show();
        btnOk.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }


}
