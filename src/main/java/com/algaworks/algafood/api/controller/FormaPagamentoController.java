package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoAssembler;
import com.algaworks.algafood.api.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.api.model.inputDto.FormaPagamentoInputDTO;
import com.algaworks.algafood.api.openapi.controller.FormaPagamentoControllerOpenApi;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.FormaPagamentoService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/formapagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Formas de pagamento")
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    public FormaPagamentoService formaPagamentoService;

    @Autowired
    public FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public FormaPagamentoAssembler fPAssembler;

    @Override
    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> adicionar(@RequestBody @Valid FormaPagamentoInputDTO formaPagamentoInputDTO){
        final FormaPagamento formaPagamentoAtual = formaPagamentoService.salvar(fPAssembler.DTOtoDomainObject(formaPagamentoInputDTO));
        final FormaPagamentoDTO formaPagamentoDTO = fPAssembler.toModel(formaPagamentoAtual);

        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoDTO);
    }

    @Override
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

        final FormaPagamentoDTO formaPagamentoDTO = fPAssembler.toModel(formaPagamento);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(formaPagamentoDTO);
    }

    @Override
    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoDTO> atualizar(@PathVariable Long formaPagamentoId,
                                                       @RequestBody FormaPagamentoInputDTO formaPagamentoInputDTO){
        final FormaPagamento formaPagamentoAtual = formaPagamentoService.buscar(formaPagamentoId);
        fPAssembler.copyProperties(formaPagamentoInputDTO, formaPagamentoAtual);
        final FormaPagamento formaPagamento = formaPagamentoService.atualizar(formaPagamentoAtual);
        final FormaPagamentoDTO formaPagamentoDTO = fPAssembler.toModel(formaPagamento);
        return ResponseEntity.ok(formaPagamentoDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(ServletWebRequest request){
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        final OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if(dataUltimaAtualizacao != null)/*Pode vir nulo caso não haja forma de pagamento*/
            eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());

        if(request.checkNotModified(eTag))/*true para etag iguais*/
            return null;

        final CollectionModel<FormaPagamentoDTO> formaPagamentosDTO = fPAssembler.toCollectionModel(formaPagamentoService.listar());
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .header(HttpHeaders.ETAG, eTag)
                .body(formaPagamentosDTO);
    }

    @Override
    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long formaPagamentoId){
        formaPagamentoService.deletar(formaPagamentoId);
        return ResponseEntity.noContent().build();
    }
}
