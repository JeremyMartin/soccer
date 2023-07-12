package fr.ewaux.backend.mapper.nation;

import fr.ewaux.backend.entity.nation.NationEntity;
import fr.ewaux.backend.mapper.club.ClubMapper;
import fr.ewaux.backend.model.request.nation.NationRequest;
import fr.ewaux.backend.model.response.nation.NationResponse;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public final class NationMapper {

	public static NationResponse mapEntityToModel(final NationEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		NationResponse model = new NationResponse();
		buildDefault(entity, model);
		return model;
	}

	public static NationResponse mapEntityToModelWithClub(final NationEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		NationResponse model = new NationResponse();
		buildDefault(entity, model);
		model.setClubs(ClubMapper.mapEntitiesToModels(entity.getClubs(), Boolean.FALSE));
		return model;
	}

	public static Set<NationResponse> mapEntitiesToModels(final List<NationEntity> entities, boolean withClubs) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		return entities.stream()
			.filter(Objects::nonNull)
			.map(withClubs ? NationMapper::mapEntityToModelWithClub : NationMapper::mapEntityToModel)
			.collect(Collectors.toSet());
	}

	public static NationEntity mapModelToEntity(final NationRequest request) {
		if (Objects.isNull(request)) {
			return null;
		}
		NationEntity entity = new NationEntity();
		entity.setId(request.getId());
		entity.setNameEn(request.getNameEn());
		entity.setNameFr(request.getNameFr());
		if (Objects.nonNull(request.getClubs())) {
			entity.setClubs(request.getClubs()
				.stream()
				.filter(Objects::nonNull)
				.map(ClubMapper::mapModelToEntity)
				.collect(Collectors.toList()));
		}
		return entity;
	}

	private static void buildDefault(final NationEntity entity, final NationResponse model) {
		model.setId(entity.getId());
		model.setNameEn(entity.getNameEn());
		model.setNameFr(entity.getNameFr());
	}

}
