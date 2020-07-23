package com.bcp.api.repository;

import com.bcp.api.model.Divisa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivisaRepository extends JpaRepository<Divisa, Long> {
    Divisa findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);
}
