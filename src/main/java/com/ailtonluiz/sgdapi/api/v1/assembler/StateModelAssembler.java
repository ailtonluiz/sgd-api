package com.ailtonluiz.sgdapi.api.v1.assembler;


import com.ailtonluiz.sgdapi.api.v1.controller.StatesController;
import com.ailtonluiz.sgdapi.api.v1.SgdLinks;
import com.ailtonluiz.sgdapi.api.v1.model.StateModel;
import com.ailtonluiz.sgdapi.core.security.SgdSecurity;
import com.ailtonluiz.sgdapi.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class StateModelAssembler 
		extends RepresentationModelAssemblerSupport<State, StateModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SgdLinks sgdLinks;
	
	@Autowired
	private SgdSecurity sgdSecurity;
	
	public StateModelAssembler() {
		super(StatesController.class, StateModel.class);
	}
	
	@Override
	public StateModel toModel(State state) {
		StateModel stateModel = createModelWithId(state.getId(), state);
		modelMapper.map(state, stateModel);
		
		if (sgdSecurity.canConsultStates()) {
			stateModel.add(sgdLinks.linkToStates("states"));
		}
		
		return stateModel;
	}
	
	@Override
	public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> entities) {
		CollectionModel<StateModel> collectionModel = super.toCollectionModel(entities);
		
		if (sgdSecurity.canConsultStates()) {
			collectionModel.add(sgdLinks.linkToStates());
		}
		
		return collectionModel;
	}
	
}
