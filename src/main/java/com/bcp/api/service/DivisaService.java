package com.bcp.api.service;

import com.bcp.api.model.Cambio;
import com.bcp.api.model.Divisa;
import io.reactivex.Single;

import java.util.List;

public interface DivisaService {
    Single<List<Divisa>> listarDivisas();
    Single<Divisa> registrarDivisa(Divisa request);
    Single<Cambio> registrarCambio(Cambio request);
}
