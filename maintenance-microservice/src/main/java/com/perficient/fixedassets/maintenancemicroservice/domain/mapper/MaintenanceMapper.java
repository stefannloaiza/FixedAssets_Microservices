package com.perficient.fixedassets.maintenancemicroservice.domain.mapper;

import com.perficient.fixedassets.maintenancemicroservice.domain.entity.Maintenance;
import com.perficient.fixedassets.maintenancemicroservice.domain.models.dto.MaintenanceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaintenanceMapper {
    MaintenanceMapper INSTANCE = Mappers.getMapper(MaintenanceMapper.class);

    Maintenance maintenanceDTOToMaintenance(MaintenanceDTO maintenanceDTO);

    MaintenanceDTO maintenanceToMaintenanceDTO(Maintenance maintenance);
}