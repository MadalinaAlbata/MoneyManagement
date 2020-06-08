package com.example.licenta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DialogWindow extends AppCompatDialogFragment {
    private EditText edit_descriere,edit_price,edit_data;
    private TextView text_type;

    private ExampleDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.activity_dialog_transaction,null);
        builder.setView(view).setTitle("Login")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String descriere= edit_descriere.getText().toString();
                        String price= edit_price.getText().toString();
                        String data= edit_data.getText().toString();

                        listener.applyTexts(descriere,price,data);

                    }

                });

        edit_data=view.findViewById(R.id.edit_data);
        edit_descriere=view.findViewById(R.id.edit_description);
        edit_price=view.findViewById(R.id.edit_price);
        text_type=view.findViewById(R.id.edit_type);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener=(ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement ExampleDialogListener");
        }

    }

    public interface ExampleDialogListener{
        void applyTexts(String description, String price,String data);
    }
}
