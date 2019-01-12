package com.example.akash.makepay;

import android.content.ClipData;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.zxing.Result;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.akash.makepay.MainActivity.m_product_list;
import static com.example.akash.makepay.MainActivity.m_ref;
import static com.example.akash.makepay.MainActivity.result_text;

public class Main2Activity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);


    }

    @Override
    public void handleResult(Result result) {

        /*Integer integer=1612;*/
        result_text.setText("Product Code:"+result.getText());


      /*String text=result_text.getText().toString().trim();
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("productDetails");
        Query query = FirebaseDatabase.getInstance().getReference().orderByChild("id").equalTo(text);
        query.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot ds : dataSnapshot.getChildren()){
                    String code=ds.child("P_code").getValue().toString();
                    String name=ds.child("P_name").getValue().toString();
                    String price=ds.child("p_price").getValue().toString();
                    *//*final String text=result_text.getText().toString();*//*

                    MainActivity.m_product_list.add(code);
                    MainActivity.m_product_list.add(name);
                    MainActivity.m_product_list.add(price);
                    MainActivity.arrayAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/

        /*final String text= result_text.getText().toString();*/

    m_ref.addValueEventListener(new ValueEventListener() {
    @Override

    public void onDataChange(DataSnapshot dataSnapshot) {
        m_product_list.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {

               String  code = ds.child("P_code").getValue().toString();
            String name = ds.child("P_name").getValue().toString();
            String  price = ds.child("p_price").getValue().toString();


                MainActivity.m_product_list.add("Product Code :"+code);
                MainActivity.m_product_list.add("Product Name :"+name);
                MainActivity.m_product_list.add("Product Price:"+price);
                MainActivity.arrayAdapter.notifyDataSetChanged();
            }
                   /* else {
                        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                    }*/


    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
    });
        onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}
