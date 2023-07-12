package fr.ewaux.backend.mapper.club;

import fr.ewaux.backend.entity.club.ClubEntity;
import fr.ewaux.backend.mapper.nation.NationMapper;
import fr.ewaux.backend.model.request.club.ClubRequest;
import fr.ewaux.backend.model.response.club.ClubResponse;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public final class ClubMapper {

	public static ClubResponse mapEntityToModel(final ClubEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		ClubResponse model = new ClubResponse();
		model.setId(entity.getId());
		buildDefault(entity, model);
		return model;
	}

	public static ClubResponse mapEntityToModelWithNation(final ClubEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		ClubResponse model = new ClubResponse();
		buildDefault(entity, model);
		model.setNation(NationMapper.mapEntityToModel(entity.getNation()));
		return model;
	}

	public static Set<ClubResponse> mapEntitiesToModels(final List<ClubEntity> entities, final boolean withNation) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		return entities.stream()
			.filter(Objects::nonNull)
			.map(withNation ? ClubMapper::mapEntityToModelWithNation : ClubMapper::mapEntityToModel)
			.collect(Collectors.toSet());
	}

	public static ClubEntity mapModelToEntity(final ClubRequest request){
		if (Objects.isNull(request)){
			return null;
		}
		ClubEntity entity = new ClubEntity();
		entity.setId(request.getId());
		entity.setName(request.getName());
		entity.setShortName(request.getShortName());
		if (Objects.nonNull(request.getNation())){
			entity.setNation(NationMapper.mapModelToEntity(request.getNation()));
		}
		return entity;
	}

	private static void buildDefault(final ClubEntity entity, final ClubResponse model) {
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setShortName(entity.getShortName());
	}

}
