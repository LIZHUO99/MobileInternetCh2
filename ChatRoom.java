package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ChatRoom extends AppCompatActivity {

    private TextView info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        info = findViewById(R.id.tv_content_info);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            Bundle num = extra.getBundle("num");
            if (num != null){
                int number = num.getInt("index");
                String text = "Item NO." + Integer.toString(number);
                info.setText(text);
            }
        }
    }
}

