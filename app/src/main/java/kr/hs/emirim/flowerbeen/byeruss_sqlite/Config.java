package kr.hs.emirim.flowerbeen.byeruss_sqlite;

public class Config {

    public static final String DATABASE_NAME = "byeruss_room.db";

    //멤버들이 있는 방 테이블
    public static final String ROOM_TABLE_NAME = "byeruss_make_room";
    public static final String COLUMN_ROOM_NAME = "roomName";
    public static final String COLUMN_ROOM_TIME = "roomTime";
    public static final String COLUMN_ROOM_PLACE = "roomPlace";

    //방에 있는 멤버 테이블
    public static final String MEMBER_TABLE_NAME = "byeruss_room_members";
    public static final String COLUMN_MEMBER_ID = "myRoomId";
    public static final String COLUMN_MEMBER_1 = "membersId1";
    public static final String COLUMN_MEMBER_2 = "membersId2";
    public static final String COLUMN_MEMBER_3 = "membersId3";
    public static final String COLUMN_MEMBER_4 = "membersId4";
    public static final String COLUMN_MEMBER_5 = "membersId5";
    public static final String COLUMN_MEMBER_6 = "membersId6";


    public static final String TITLE = "title";
    public static final String CREATE_ROOM = "create_room";
    public static final String UPDATE_ROOM = "update_room";
    public static final String FIND_ROOM = "find_room";

}
