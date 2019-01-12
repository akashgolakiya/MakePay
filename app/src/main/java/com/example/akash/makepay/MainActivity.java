package com.example.akash.makepay;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
ImageButton btn_scanner;
ListView m_listView;
public static Firebase m_ref;
public static  TextView result_text;
    DatabaseReference databaseReference;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    public static ArrayList<String> m_product_list = new ArrayList<>();
    public static ArrayAdapter<String > arrayAdapter;
Button btn_cart;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_scanner=(ImageButton)findViewById(R.id.btn_scanner);
        result_text=(TextView)findViewById(R.id.product_code);
     m_ref=new Firebase("https://scannerapp-d5a45.firebaseio.com/productDetails");
        m_listView=(ListView)findViewById(R.id.list_item);
         arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,m_product_list);
        m_listView.setAdapter(arrayAdapter);

        btn_cart=(Button)findViewById(R.id.btn_cart);
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        }
        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
            }
        });


     /*  m_ref.addChildEventListener(new com.firebase.client.ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               for (DataSnapshot ds : dataSnapshot.getChildren()){
                   Map<String, String> map = dataSnapshot.getValue(Map.class);

                   String code=map.get("P_code");
                   String name=map.get("P_name");
                   String price= map.get("p_price");

                   m_product_list.add(code);
                   m_product_list.add(name);
                   m_product_list.add(price);
                   arrayAdapter.notifyDataSetChanged();

               }
           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(FirebaseError firebaseError) {

           }
       });*/

   /*m_ref.addValueEventListener(new ValueEventListener() {
       @Override
       public void onDataChange(DataSnapshot dataSnapshot) {
           for (DataSnapshot ds : dataSnapshot.getChildren()){
               String code=ds.child("P_code").getValue().toString();
               String name=ds.child("P_name").getValue().toString();
               String price=ds.child("p_price").getValue().toString();
               final String text=result_text.getText().toString();
               if (code.matches(text)) {
                   m_product_list.add(code);
                   m_product_list.add(name);
                   m_product_list.add(price);
                   arrayAdapter.notifyDataSetChanged();
               }
               else {
                   Toast.makeText(MainActivity.this, "Failed to Load", Toast.LENGTH_LONG).show();

               }

           }
       }

       @Override
       public void onCancelled(FirebaseError firebaseError) {

       }
   });
*/

/* btn_cart.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Intent i=new Intent(MainActivity.this,Main3Activity.class);
         startActivity(i);
     }
 });*/

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==MY_CAMERA_REQUEST_CODE)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(MainActivity.this, "camera permission granted", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(MainActivity.this, "camera permission denied", Toast.LENGTH_LONG).show();

            }
    }
}
