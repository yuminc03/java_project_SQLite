package kr.hs.emirim.flowerbeen.byeruss_sqlite;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RoomFindActivity extends AppCompatActivity {

    private EditText text_input_code;
    private Button btn_check;
    private Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_find);

        text_input_code = findViewById(R.id.text_input_code);
        btn_check = (Button)findViewById(R.id.btn_check);
        btn_cancel = (Button)findViewById(R.id.btn_cancel );
    }
}