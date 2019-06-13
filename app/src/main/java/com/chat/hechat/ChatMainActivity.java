package com.chat.hechat;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.chat.hechat.chat.ChatFragment;
import com.chat.hechat.contact.ContactFragment;

public class ChatMainActivity extends AppCompatActivity {
    private ChatFragment chatFragment;
    private ContactFragment contactFragment;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainchat);
        chatFragment = new ChatFragment();
        contactFragment =new ContactFragment();
        transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        transaction.add(R.id.layout_frame,chatFragment);
        transaction.add(R.id.layout_frame,contactFragment);
        transaction.show(chatFragment);
        transaction.commit();

        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        //bottomNavigationView Item 选择监听
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("123", "onNavigationItemSelected is click: ");

                hideAllFragment(transaction);
                switch (item.getItemId()){
                    case R.id.navigation_home:
                            transaction.show(chatFragment);

                        break;
                    case R.id.navigation_dashboard:
                        transaction.show(contactFragment);
                        break;
                }
                transaction.commit();

                return false;
            }
        });
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(chatFragment!=null){
            transaction.hide(chatFragment);
        }
        if(contactFragment!=null){
            transaction.hide(contactFragment);
        }

    }

}
