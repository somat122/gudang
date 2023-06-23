package org.anakrimba.gudang.controller;

import org.anakrimba.gudang.model.Gudang;
import org.anakrimba.gudang.repository.GudangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http:localhost//8080")
@RestController
@RequestMapping("/api")
public class GudangController {
    @Autowired
    GudangRepo gudangRepo;

    @GetMapping("/gudangs")
    public ResponseEntity<List<Gudang>> getAllPasien(@RequestParam(required = false) String namaBarang) {
        List<Gudang> gudangs = new ArrayList<>();

        if (namaBarang == null) {
            gudangRepo.findAll().forEach(gudangs::add);
        } else {
            gudangRepo.findByNamaBarangContaining(namaBarang).forEach(gudangs::add);
        }

        if (gudangs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(gudangs, HttpStatus.OK);

    }



    @GetMapping("/gudangs/{id}")
    public ResponseEntity<Gudang> getTutorialById(@PathVariable("id") long id) {
        Optional<Gudang> gudangData = gudangRepo.findById(id);

        if (gudangData.isPresent()) {
            return new ResponseEntity<>(gudangData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/gudangs")
    public ResponseEntity<Gudang> createGudang(@RequestBody Gudang gudang) {
        try {
            Gudang _gudang = gudangRepo
                    .save(new Gudang(gudang.getNamaBarang(), gudang.getJumlahBarang()));
            return new ResponseEntity<Gudang>(_gudang, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/gudangs/{id}")
    public ResponseEntity<Gudang> updateWeapon(@PathVariable("id") long id, @RequestBody Gudang gudang) {
        Optional<Gudang> gudangData = gudangRepo.findById(id);

        if (gudangData.isPresent()) {
            Gudang _gudang = gudangData.get();
            _gudang.setNamaBarang(gudang.getNamaBarang());
            _gudang.setJumlahBarang(gudang.getJumlahBarang());
            return new ResponseEntity<>(gudangRepo.save(_gudang), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/gudangs/{id}")
    public ResponseEntity<HttpStatus> deleteGudangs(@PathVariable("id") long id) {
        try {
            gudangRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/gundangs")
    public ResponseEntity<HttpStatus> deleteAllGudangs() {
        try {
            gudangRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }
}

