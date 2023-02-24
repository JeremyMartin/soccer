package fr.ewaux.backend.mapper.club;

import fr.ewaux.backend.entity.club.ClubEntity;
import fr.ewaux.backend.mapper.nation.NationMapper;
import fr.ewaux.backend.model.soccer.club.Club;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public final class ClubMapper {

	private ClubMapper() {
	}

	public static Club mapEntityToSimpleModel(final ClubEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		Club model = new Club();
		model.setId(entity.getId());
		buildDefault(entity, model);
		return model;
	}

	public static Club mapEntityToModelWithNation(final ClubEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		Club model = new Club();
		buildDefault(entity, model);
		model.setNation(NationMapper.mapEntityToSingleModel(entity.getNation()));
		return model;
	}

	public static Set<Club> mapEntitiesToSimpleSetModels(final List<ClubEntity> entities, final boolean withNation) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		return entities.stream()
			.filter(Objects::nonNull)
			.map(withNation ? ClubMapper::mapEntityToModelWithNation : ClubMapper::mapEntityToSimpleModel)
			.collect(Collectors.toSet());
	}

	private static void buildDefault(final ClubEntity entity, final Club model) {
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setShortName(entity.getShortName());
	}

}
