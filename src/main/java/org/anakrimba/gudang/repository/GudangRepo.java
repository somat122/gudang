package org.anakrimba.gudang.repository;

import org.anakrimba.gudang.model.Gudang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GudangRepo extends JpaRepository<Gudang,Long> {
List<Gudang>findByNamaBarangContaining(String namaBarang);

}
