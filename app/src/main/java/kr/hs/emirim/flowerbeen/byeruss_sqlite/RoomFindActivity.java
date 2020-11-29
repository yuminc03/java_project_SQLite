package kr.hs.emirim.flowerbeen.byeruss_sqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RoomFindActivity extends AppCompatActivity {

    private EditText text_input_code;
    private Button btn_check;
    //private Button btn_cancel;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;

    //SQLiteDatabase getWriteableDatabase()
    // 읽기, 쓰기 모드로 데이터베이스를 오픈. 만약 처음으로 호출되는 경우에는 onCreate()가 호출.
    // 만약 데이터베이스 스키마가 달라지는 경우에는 onUpgrade()가 호출.
    //SQLiteDatabase getReadableDatabase()
    // 읽기 전용 모드로 데이터베이스를  오픈. 근본적으로 getWritableDatabase()와 같지만
    // 디스크가 꽉찼거나 권한이 없어서 데이터베이스를 읽기 전용으로만 오픈할때 사용.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_find);

        text_input_code = findViewById(R.id.text_input_code);
        btn_check = findViewById(R.id.btn_check);
        //btn_cancel = findViewById(R.id.btn_cancel);

//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RoomFindActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text_input_code.getText() != null){
                    findRoom();
                }
                else{
                    Toast.makeText(RoomFindActivity.this, "모임이름을 입력해주세요!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void findRoom(){
        try{
            sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();
            String sql = "SELECT * FROM byeruss_make_room where roomName='"
                    + text_input_code.getText().toString() + "';";
            cursor = sqLiteDatabase.rawQuery(sql, null);

            if(cursor.moveToFirst()){
                Toast.makeText(RoomFindActivity.this, "모임이 존재합니다!", Toast.LENGTH_LONG).show();
                cursor.close();
                sqLiteDatabase.close();
                dialogSignUp();
            }
            else{
                Toast.makeText(RoomFindActivity.this, "그 모임은 없습니다!", Toast.LENGTH_LONG).show();
                cursor.close();
                sqLiteDatabase.close();
            }

        }catch(Exception e){
            e.getStackTrace();
        }
    }

    public void dialogSignUp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(RoomFindActivity.this);
        builder.setTitle("모임!");
        builder.setMessage("이 모임에 가입하시겠습니까? ");

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}