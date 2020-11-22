package kr.hs.emirim.flowerbeen.byeruss_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{

    private TextView tv_user;
    private Button btn_make_room;
    private Button btn_find_room;
    private Button btn_my_room;
    private Button btn_log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_user = findViewById(R.id.tv_user);
        btn_make_room = findViewById(R.id.btn_make_room);
        btn_find_room = findViewById(R.id.btn_find_room);
        btn_my_room = findViewById(R.id.btn_my_room);
        btn_log_out = findViewById(R.id.btn_log_out);

        tv_user.append("유저이름");

//        Intent intent = getIntent();
//        String userID = intent.getStringExtra("유저이름");
//        tv_user.setText(userID);

        btn_make_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RoomCreateActivity.class);
                startActivity(intent);
            }
        });
        btn_find_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RoomFindActivity.class);
                startActivity(intent);
            }
        });
        btn_my_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RoomListActivity.class);
                startActivity(intent);
            }
        });
        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}