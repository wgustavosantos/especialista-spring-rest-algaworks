package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoAssembler;
import com.algaworks.algafood.api.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.api.model.inputDto.FormaPagamentoInputDTO;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.FormaPagamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/formapagamentos")
public class FormaPagamentoController {

    @Autowired
    public FormaPagamentoService formaPagamentoService;

    @Autowired
    public FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public FormaPagamentoAssembler fPAssembler;

    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> adicionar(@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInputDTO){
        final FormaPagamento formaPagamentoAtual = formaPagamentoService.salvar(fPAssembler.DTOtoDomainObject(formaPagamentoInputDTO));
        final FormaPagamentoDTO formaPagamentoDTO = fPAssembler.toDTO(formaPagamentoAtual);

        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoDTO);
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoDTO> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request){
        final FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        final OffsetDateTime dataUltimaAtualizacaoId = formaPagamentoRepository.getDataUltimaAtualizacaoId(formaPagamentoId);
        if(dataUltimaAtualizacaoId != null)
            eTag = String.valueOf(dataUltimaAtualizacaoId.toEpochSecond());

        if(request.checkNotModified(eTag))
            return null;

        final FormaPagamentoDTO formaPagamentoDTO = fPAssembler.toDTO(formaPagamento);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(formaPagamentoDTO);
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoDTO> atualizar(@PathVariable Long formaPagamentoId,
                                                      @RequestBody FormaPagamentoInputDTO formaPagamentoInputDTO){
        final FormaPagamento formaPagamentoAtual = formaPagamentoService.buscar(formaPagamentoId);
        fPAssembler.copyProperties(formaPagamentoInputDTO, formaPagamentoAtual);
        final FormaPagamento formaPagamento = formaPagamentoService.atualizar(formaPagamentoAtual);
        final FormaPagamentoDTO formaPagamentoDTO = fPAssembler.toDTO(formaPagamento);
        return ResponseEntity.ok(formaPagamentoDTO);
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDTO>> listar(ServletWebRequest request){
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        final OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if(dataUltimaAtualizacao != null)/*Pode vir nulo caso não haja forma de pagamento*/
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());

        if(request.checkNotModified(eTag))/*true para etag iguais*/
            return null;

        final List<FormaPagamentoDTO> formaPagamentosDTO = fPAssembler.toListDTO(formaPagamentoService.listar());
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .header(HttpHeaders.ETAG, eTag)
                .body(formaPagamentosDTO);
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long formaPagamentoId){
        formaPagamentoService.deletar(formaPagamentoId);
        return ResponseEntity.noContent().build();
    }
}
