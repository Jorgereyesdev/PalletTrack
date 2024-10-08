package com.riwi.PalletTrack.domain.ports.services;

import com.riwi.PalletTrack.application.dtos.request.LoadRequest;
import com.riwi.PalletTrack.application.dtos.response.LoadResponse;

import java.util.List;

public interface ILoadService {
    // Obtener todas las cargas (solo administradores)
    List<LoadResponse> getAllLoads();

    // Obtener detalles de una carga por ID (solo administradores)
    LoadResponse getLoadById(Long id);

    // Registrar una nueva carga y asignarla a un palet (solo administradores)
    LoadResponse createLoad(LoadRequest request);

    // Modificar una carga existente (solo administradores)
    LoadResponse updateLoad(Long id, LoadRequest request);

    // Actualizar el estado de una carga (Transportista o administrador)
    LoadResponse updateLoadStatus(Long id, String status);

    // Reportar daños en una carga (Transportista o administrador)
    LoadResponse reportLoadDamage(Long id, String damageReport);

    // Obtener todas las cargas asignadas a un palet específico
    List<LoadResponse> getLoadsByPalletId(Long palletId);
}
