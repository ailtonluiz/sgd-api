package com.ailtonluiz.sgdapi.api.v1.assembler;

import com.ailtonluiz.sgdapi.api.v1.SgdLinks;
import com.ailtonluiz.sgdapi.api.v1.controller.PaymentMethodsController;
import com.ailtonluiz.sgdapi.api.v1.model.PaymentMethodModel;
import com.ailtonluiz.sgdapi.core.security.SgdSecurity;
import com.ailtonluiz.sgdapi.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodModelAssembler
		extends RepresentationModelAssemblerSupport<PaymentMethod, PaymentMethodModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SgdLinks sgdLinks;
	
	@Autowired
	private SgdSecurity sgdSecurity;
	
	public PaymentMethodModelAssembler() {
		super(PaymentMethodsController.class, PaymentMethodModel.class);
	}
	
	@Override
	public PaymentMethodModel toModel(PaymentMethod paymentMethod) {
		PaymentMethodModel paymentMethodModel = 
				createModelWithId(paymentMethod.getId(), paymentMethod);
		
		modelMapper.map(paymentMethod, paymentMethodModel);
		
		if (sgdSecurity.canConsultPaymentMethods()) {
			paymentMethodModel.add(sgdLinks.linkToPaymentMethods("paymentMethods"));
		}
		
		return paymentMethodModel;
	}
	
	@Override
	public CollectionModel<PaymentMethodModel> toCollectionModel(Iterable<? extends PaymentMethod> entities) {
		CollectionModel<PaymentMethodModel> collectionModel = super.toCollectionModel(entities);
		
		if (sgdSecurity.canConsultPaymentMethods()) {
			collectionModel.add(sgdLinks.linkToPaymentMethods());
		}
			
		return collectionModel;
	}
	
}
