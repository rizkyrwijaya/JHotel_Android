package projectoop.jhotel_android_rizkyramadianwijaya;

public class Lokasi {
    private double x_coord;
    private double y_coord;
    private String deskripsiLokasi;

    public Lokasi(double x_coord, double y_coord, String deskripsiLokasi) {
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.deskripsiLokasi = deskripsiLokasi;
    }

    public double getX_coord() {
        return x_coord;
    }

    public void setX_coord(double x_coord) {
        this.x_coord = x_coord;
    }

    public double getY_coord() {
        return y_coord;
    }

    public void setY_coord(double y_coord) {
        this.y_coord = y_coord;
    }

    public String getDeskripsiLokasi() {
        return deskripsiLokasi;
    }

    public void setDeskripsiLokasi(String deskripsiLokasi) {
        this.deskripsiLokasi = deskripsiLokasi;
    }


}
