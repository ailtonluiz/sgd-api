package com.ailtonluiz.sgdapi.domain.service;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.exception.PaymentMethodNotFoundException;
import com.ailtonluiz.sgdapi.domain.model.Brand;
import com.ailtonluiz.sgdapi.domain.model.PaymentMethod;
import com.ailtonluiz.sgdapi.domain.repository.BrandRepository;
import com.ailtonluiz.sgdapi.domain.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterPaymentMethodService {

    private static final String MSG_PAYENT_FORM_IN_USE
            = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Transactional
    public void remove(Long paymentMethodId) {
        try {
            paymentMethodRepository.deleteById(paymentMethodId);
            paymentMethodRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new PaymentMethodNotFoundException(paymentMethodId);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_PAYENT_FORM_IN_USE, paymentMethodId));
        }
    }

    public PaymentMethod fetchOrFail(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new PaymentMethodNotFoundException(paymentMethodId));
    }
}
