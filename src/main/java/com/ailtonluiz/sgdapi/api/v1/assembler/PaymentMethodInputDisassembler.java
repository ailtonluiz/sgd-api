package com.ailtonluiz.sgdapi.api.v1.assembler;

import com.ailtonluiz.sgdapi.api.v1.model.input.PaymentMethodInput;
import com.ailtonluiz.sgdapi.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public PaymentMethod toDomainObject(PaymentMethodInput paymentMethodInput) {
		return modelMapper.map(paymentMethodInput, PaymentMethod.class);
	}
	
	public void copyToDomainObject(PaymentMethodInput paymentMethodInput, PaymentMethod paymentMethod) {
		modelMapper.map(paymentMethodInput, paymentMethod);
	}
	
}
