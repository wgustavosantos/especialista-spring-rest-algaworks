package com.algaworks.algafood.infrastructure.repository.service.report;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.service.VendaReportService;
import org.springframework.stereotype.Service;

@Service
public class VendaPdfReportServiceImpl implements VendaReportService {


    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffSet) {
        return null;
    }
}
