package com.bcp.api.service;

import com.bcp.api.model.Cambio;
import com.bcp.api.model.Divisa;
import com.bcp.api.repository.CambioRepository;
import com.bcp.api.repository.DivisaRepository;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisaServiceImpl implements DivisaService {

    @Autowired
    private DivisaRepository divisaRepository;

    @Autowired
    private CambioRepository cambioRepository;

    @Override
    public Single<List<Divisa>> listarDivisas() {
        return Single.create(subscriber -> {
            subscriber.onSuccess(divisaRepository.findAll());
        });
    }

    @Override
    public Single<Divisa> registrarDivisa(Divisa request) {
        return Single.create(subscriber -> {
            subscriber.onSuccess(divisaRepository.save(request));
        });
    }

    @Override
    public Single<Cambio> registrarCambio(Cambio request) {
        return Single.create(subscriber -> {
            Divisa divisa = divisaRepository.findByMonedaOrigenAndMonedaDestino(request.getMonedaOrigen(), request.getMonedaDestino());

            Double montoDestino = request.getMontoOrigen() * divisa.getTipoCambio();

            request.setTipoCambio(divisa.getTipoCambio());
            request.setMontoDestino(montoDestino);

            subscriber.onSuccess(cambioRepository.save(request));
        });
    }
}
