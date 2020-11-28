package kr.hs.emirim.flowerbeen.byeruss_sqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class RoomListActivity extends AppCompatActivity{

    private final String TAG = "RoomListActivity";
    private String DB_PATH =  "/data/data/kr.hs.emirim.flowerbeen.byeruss/byeruss_room.db";

    private ListView room_list_view;
    private String roomName, roomTime, roomPlace;
    //private Button back_to_menu;

    private ArrayList arrayList = new ArrayList<>();
    private ArrayList<roomItem> roomitem = new ArrayList<>();
    private ArrayAdapter listAdapter;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;
    //cursor adapter는 커서로 읽은 정보를 list로 만들어 주는 역할을 한다.
    //따라서 DB에서 읽은 정보를 listview 형태로 보여줄때 사용한다.
    //Simple Cursor Adapter : cursor adatper중에 가장 간단한 adapter 이다.
    //Simple cursor adatper는 cursor에 있는 정보를 textView나 imageView로 보여줄때 사용한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

        room_list_view = findViewById(R.id.room_list_view);
        room_list_view.setOnItemLongClickListener(mLongClickListener);
        //back_to_menu = findViewById(R.id.back_to_menu);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        selectRoom(listAdapter, mySQLiteOpenHelper, room_list_view);

//        back_to_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RoomListActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

        room_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {//모임을 클릭했을 때 intent이동
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MemberListActivity.class);
                intent.putExtra("roomName", roomName);
                startActivity(intent);
            }
        });

    }
    AdapterView.OnItemLongClickListener mLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RoomListActivity.this);
            builder.setTitle("삭제");
            builder.setMessage("정말로 방을 삭제하겠습니까?");

            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    deleteRoom(listAdapter, mySQLiteOpenHelper, room_list_view);
                    Toast.makeText(getApplicationContext(), "모임이 삭제되었습니다!!", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "메뉴로 갔다가 다시 들어오세요~", Toast.LENGTH_LONG).show();
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
            //delete_dialog();

            return true;
        }
    };
    public void deleteRoom(ArrayAdapter listAdapter, MySQLiteOpenHelper mySQLiteOpenHelper, ListView listView){
        int count, checked;
        count = listAdapter.getCount();
        try{
            sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
            sqLiteDatabase.rawQuery("DELETE FROM byeruss_make_room WHERE roomName = " + roomName + ";", null);
            if (count > 0) {
                // 현재 선택된 아이템의 position 획득.
                checked = listView.getCheckedItemPosition();
                if (checked > -1 && checked < count) {
                    // 아이템 삭제
                    roomitem.remove(checked);
                    // listview 선택 초기화.
                    listView.clearChoices();
                    // listview 갱신.
                    listAdapter.notifyDataSetChanged();
                }
            }
            listAdapter.notifyDataSetChanged();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(sqLiteDatabase != null) sqLiteDatabase.close();
        }
    }

    public void selectRoom(ArrayAdapter listAdapter, MySQLiteOpenHelper mySQLiteOpenHelper, ListView listView){
        listAdapter.clear();
        roomitem.clear();

        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM byeruss_make_room", null);
        if(cursor != null){
            cursor.move(0);
            while(cursor.moveToNext()) {
                roomName = cursor.getString(cursor.getColumnIndex("roomName"));
                roomTime = cursor.getString(cursor.getColumnIndex("roomTime"));
                roomPlace = cursor.getString(cursor.getColumnIndex("roomPlace"));
                roomitem.add(new roomItem(roomName, roomTime, roomPlace));//객체에 DB내용 담기
            }
            listView.setAdapter(listAdapter);
            for(roomItem roomitem : roomitem){
                listAdapter.add("모임이름: " + roomitem.getRoomName() + "\n" + "모임시간: " + roomitem.getRoomTime() + "\n" + "모임장소: " + roomitem.getRoomPlace() + "\n");
            }
            listAdapter.notifyDataSetChanged();
        }
    }

}