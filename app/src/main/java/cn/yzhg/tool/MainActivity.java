package cn.yzhg.tool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button but_toast = findViewById(R.id.but_toast);
        but_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.showToast(MainActivity.this,"哈哈哈哈哈");
            }
        });
    }
}
