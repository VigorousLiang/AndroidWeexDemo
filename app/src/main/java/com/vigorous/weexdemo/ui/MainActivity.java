package com.vigorous.weexdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.vigorous.weexdemo.R;
import com.vigorous.weexdemo.ui.WeexActivity.WeexLocalActivity;
import com.vigorous.weexdemo.ui.WeexActivity.WeexNetworkActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnJumpToWeex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnJumpToWeex=(Button)findViewById(R.id.btn_jump_to_weex);
        btnJumpToWeex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WeexLocalActivity.class));
            }
        });
    }
}
