package fr.ewaux.backend.mapper.nation;

import fr.ewaux.backend.entity.nation.NationEntity;
import fr.ewaux.backend.mapper.club.ClubMapper;
import fr.ewaux.backend.model.soccer.nation.Nation;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public final class NationMapper {

	private NationMapper() {
	}

	public static Nation mapEntityToSingleModel(final NationEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		Nation model = new Nation();
		buildDefault(entity, model);
		return model;
	}

	public static Nation mapEntityToModelWithClub(final NationEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		Nation model = new Nation();
		buildDefault(entity, model);
		model.setClubs(ClubMapper.mapEntitiesToSimpleSetModels(entity.getClubs(), Boolean.FALSE));
		return model;
	}

	public static Set<Nation> mapEntitiesToSetModels(final List<NationEntity> entities, boolean withClubs) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		return entities.stream()
			.filter(Objects::nonNull)
			.map(withClubs ? NationMapper::mapEntityToModelWithClub : NationMapper::mapEntityToSingleModel)
			.collect(Collectors.toSet());
	}

	private static void buildDefault(final NationEntity entity, final Nation model) {
		model.setId(entity.getId());
		model.setNameEn(entity.getNameEn());
		model.setNameFr(entity.getNameFr());
	}

}
