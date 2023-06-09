package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.openapi.controller.EstatisticasControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import com.algaworks.algafood.domain.service.VendaReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Autowired
    private VendaReportService vendaReportService;

    @Autowired
    private AlgaLinks algaLinks;


    @CheckSecurity.Estatisticas.PodeConsultar
    @Override
    public EstatisticasDTO estatisticas() {
        final EstatisticasDTO estatisticasDTO = new EstatisticasDTO();

        estatisticasDTO.add(algaLinks.linkToEstatisticasVendasDiarias("vendas-diarias"));

        return estatisticasDTO;
    }

    @CheckSecurity.Estatisticas.PodeConsultar
    @Override
    @GetMapping(path ="/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter vendaDiariaFilter,
                                                    @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet){
        return vendaQueryService.consultarVendasDiarias(vendaDiariaFilter, timeOffSet);
    }

    @CheckSecurity.Estatisticas.PodeConsultar
    @Override
    @GetMapping(path ="/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter vendaDiariaFilter,
                                                            @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet){
        final byte[] bytesPdf = vendaReportService.emitirVendasDiarias(vendaDiariaFilter, timeOffSet);

        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(bytesPdf);
    }

    public static class EstatisticasDTO extends RepresentationModel<EstatisticasDTO> {
    }
}
