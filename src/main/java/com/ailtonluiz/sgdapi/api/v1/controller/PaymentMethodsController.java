package com.ailtonluiz.sgdapi.api.v1.controller;

import com.ailtonluiz.sgdapi.api.v1.assembler.PaymentMethodInputDisassembler;
import com.ailtonluiz.sgdapi.api.v1.assembler.PaymentMethodModelAssembler;
import com.ailtonluiz.sgdapi.api.v1.model.PaymentMethodModel;
import com.ailtonluiz.sgdapi.api.v1.model.input.PaymentMethodInput;
import com.ailtonluiz.sgdapi.api.v1.openapi.controller.PaymentMethodsControllerOpenApi;
import com.ailtonluiz.sgdapi.core.security.CheckSecurity;
import com.ailtonluiz.sgdapi.domain.model.PaymentMethod;
import com.ailtonluiz.sgdapi.domain.repository.PaymentMethodRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping(path = "/v1/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentMethodsController implements PaymentMethodsControllerOpenApi {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private RegisterPaymentMethodService registerPaymentMethodService;

    @Autowired
    private PaymentMethodModelAssembler paymentMethodModelAssembler;

    @Autowired
    private PaymentMethodInputDisassembler paymentMethodInputDisassembler;

    @CheckSecurity.PaymentMethods.CanConsult
@Override
    @GetMapping
    public ResponseEntity<CollectionModel<PaymentMethodModel>> list(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime lastUpdateDate = paymentMethodRepository.getLastUpdateDate();

        if (lastUpdateDate != null) {
            eTag = String.valueOf(lastUpdateDate.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<PaymentMethod> allPaymentMethods = paymentMethodRepository.findAll();

        CollectionModel<PaymentMethodModel> formasPagamentosModel =
                paymentMethodModelAssembler.toCollectionModel(allPaymentMethods);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(formasPagamentosModel);
    }

    @CheckSecurity.PaymentMethods.CanConsult
    @Override
    @GetMapping("/{paymentMethodId}")
    public ResponseEntity<PaymentMethodModel> search(@PathVariable Long paymentMethodId,
                                                      ServletWebRequest request) {

        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";

        OffsetDateTime dataAtualizacao = paymentMethodRepository
                .getUpdateDateById(paymentMethodId);

        if (dataAtualizacao != null) {
            eTag = String.valueOf(dataAtualizacao.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        PaymentMethod paymentMethod = registerPaymentMethodService.fetchOrFail(paymentMethodId);

        PaymentMethodModel paymentMethodModel = paymentMethodModelAssembler.toModel(paymentMethod);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(paymentMethodModel);
    }

    @CheckSecurity.PaymentMethods.CanEdit
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodModel add(@RequestBody @Valid PaymentMethodInput paymentMethodInput) {
        PaymentMethod paymentMethod = paymentMethodInputDisassembler.toDomainObject(paymentMethodInput);

        paymentMethod = registerPaymentMethodService.save(paymentMethod);

        return paymentMethodModelAssembler.toModel(paymentMethod);
    }

    @CheckSecurity.PaymentMethods.CanEdit
    @Override
    @PutMapping("/{paymentMethodId}")
    public PaymentMethodModel update(@PathVariable Long paymentMethodId,
                                         @RequestBody @Valid PaymentMethodInput paymentMethodInput) {
        PaymentMethod paymentMethodAtual = registerPaymentMethodService.fetchOrFail(paymentMethodId);

        paymentMethodInputDisassembler.copyToDomainObject(paymentMethodInput, paymentMethodAtual);

        paymentMethodAtual = registerPaymentMethodService.save(paymentMethodAtual);

        return paymentMethodModelAssembler.toModel(paymentMethodAtual);
    }


    @CheckSecurity.PaymentMethods.CanEdit
    @Override
    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long paymentMethodId) {
        registerPaymentMethodService.remove(paymentMethodId);
    }

}

