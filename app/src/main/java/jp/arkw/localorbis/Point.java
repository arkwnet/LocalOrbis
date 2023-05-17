package jp.arkw.localorbis;

class Point {
    String name;
    double latitude;
    double longitude;
    int limit;
    int status;

    Point(String name, double latitude, double longitude, int limit, int status) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.limit = limit;
        this.status = status;
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

    public int getStatus() { return this.status; }
}
