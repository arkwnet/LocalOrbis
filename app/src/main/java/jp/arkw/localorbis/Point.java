package jp.arkw.localorbis;

class Point {
    String name;
    double latitude;
    double longitude;
    int limit;
    int status;
    boolean voice;

    Point(String name, double latitude, double longitude, int limit, int status) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.limit = limit;
        this.status = status;
        this.voice = false;
    }

    public String getName() {
        return this.name;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public int getLimit() {
        return this.limit;
    }

    /*
    ステータス
    0: 通常時
    1: 2km手前
    2: 1km手前
    3: 500m手前
    4: 通過済
     */

    public int getStatus() { return this.status; }

    public void setStatus(int status) {
        this.status = status;
        this.setVoice(false);
    }

    public boolean getVoice() { return this.voice; }

    public void setVoice(boolean voice) { this.voice = voice; }
}
