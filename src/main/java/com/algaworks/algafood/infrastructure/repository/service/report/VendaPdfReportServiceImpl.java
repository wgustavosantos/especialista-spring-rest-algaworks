package com.algaworks.algafood.infrastructure.repository.service.report;

import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import com.algaworks.algafood.domain.service.VendaReportService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Service
public class VendaPdfReportServiceImpl implements VendaReportService {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffSet) {

        final InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");

        final HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

        final List<VendaDiaria> vendaDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffSet);
        final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vendaDiarias);

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException(ErrorMessage.RELATORIO_INDISPONIVEL.get(), e);
        }
    }
}
