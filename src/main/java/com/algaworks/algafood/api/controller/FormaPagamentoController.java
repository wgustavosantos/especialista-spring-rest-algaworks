package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoAssembler;
import com.algaworks.algafood.api.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.api.model.inputDto.FormaPagamentoInputDTO;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.service.FormaPagamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/formapagamentos")
public class FormaPagamentoController {

    @Autowired
    public FormaPagamentoService formaPagamentoService;

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
    public ResponseEntity<FormaPagamentoDTO> buscar(@PathVariable Long formaPagamentoId){
        final FormaPagamento formaPagamento = formaPagamentoService.buscar(formaPagamentoId);
        final FormaPagamentoDTO formaPagamentoDTO = fPAssembler.toDTO(formaPagamento);
        return ResponseEntity.ok(formaPagamentoDTO);
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
    public ResponseEntity<List<FormaPagamentoDTO>> listar(){
        final List<FormaPagamentoDTO> formaPagamentosDTO = fPAssembler.toListDTO(formaPagamentoService.listar());
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(formaPagamentosDTO);
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long formaPagamentoId){
        formaPagamentoService.deletar(formaPagamentoId);
        return ResponseEntity.noContent().build();
    }
}
