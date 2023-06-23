package org.anakrimba.gudang.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gudang")
public class Gudang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "namaBarang")
    private String namaBarang;
    @Column(name = "jumlahBarang")
    private String jumlahBarang;

    public Gudang() {
    }

    public long getId() {
        return id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(String jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    @Override
    public String toString() {
        return "Gudang{" +
                "id=" + id +
                ", namaBarang='" + namaBarang + '\'' +
                ", jumlahBarang='" + jumlahBarang + '\'' +
                '}';
    }

    public Gudang(String namaBarang, String jumlahBarang) {
        this.namaBarang = namaBarang;
        this.jumlahBarang = jumlahBarang;
    }
}
