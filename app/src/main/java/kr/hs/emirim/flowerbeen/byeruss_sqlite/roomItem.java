package kr.hs.emirim.flowerbeen.byeruss_sqlite;

public class roomItem {
    private String RoomName;
    private String RoomTime;
    private String RoomPlace;

    public roomItem(String RoomName, String RoomTime, String RoomPlace){
        this.RoomName = RoomName;
        this.RoomTime = RoomTime;
        this.RoomPlace = RoomPlace;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getRoomTime() {
        return RoomTime;
    }

    public void setRoomTime(String roomTime) {
        RoomTime = roomTime;
    }

    public String getRoomPlace() {
        return RoomPlace;
    }

    public void setRoomPlace(String roomPlace) {
        RoomPlace = roomPlace;
    }
}
