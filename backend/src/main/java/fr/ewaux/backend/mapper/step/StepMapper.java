package fr.ewaux.backend.mapper.step;

import fr.ewaux.backend.entity.step.StepEntity;
import fr.ewaux.backend.model.request.step.StepRequest;
import fr.ewaux.backend.model.response.step.StepResponse;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public final class StepMapper {

	public static StepResponse mapEntityToModel(final StepEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		StepResponse model = new StepResponse();
		model.setId(entity.getId());
		model.setNameEn(entity.getNameEn());
		model.setNameFr(entity.getNameFr());
		return model;
	}

	public static StepEntity mapModelToEntity(final StepRequest request) {
		if (Objects.isNull(request)) {
			return null;
		}
		StepEntity entity = new StepEntity();
		entity.setId(request.getId());
		entity.setNameEn(request.getNameEn());
		entity.setNameFr(request.getNameFr());
		return entity;
	}

	public static Set<StepResponse> mapEntitiesToModels(final List<StepEntity> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		return entities.stream().map(StepMapper::mapEntityToModel).collect(
			Collectors.toCollection(LinkedHashSet::new));
	}
}
