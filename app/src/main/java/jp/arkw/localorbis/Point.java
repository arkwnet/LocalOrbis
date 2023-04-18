package jp.arkw.localorbis;

class Point {
    String name;
    double latitude;
    double longitude;
    int limit;

    Point(String name, double latitude, double longitude, int limit) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.limit = limit;
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
}
