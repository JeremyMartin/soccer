package fr.ewaux.backend.service.phase.impl;

import fr.ewaux.backend.entity.phase.GroupEntity;
import fr.ewaux.backend.repository.phase.GroupRepository;
import fr.ewaux.backend.service.match.MatchService;
import fr.ewaux.backend.service.phase.GroupService;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "groupService")
public class GroupServiceImpl implements GroupService {

	GroupRepository groupRepository;
	MatchService matchService;

	public GroupServiceImpl(final GroupRepository groupRepository, final MatchService matchService) {
		super();
		this.groupRepository = groupRepository;
		this.matchService = matchService;
	}

	@Transactional
	@Override
	public GroupEntity add(final GroupEntity entity) {
		return this.groupRepository.save(entity);
	}

	@Transactional
	@Override
	public List<GroupEntity> addAll(final List<GroupEntity> entities) {
		if (CollectionUtils.isEmpty(entities)) {
			return null;
		}
		for (GroupEntity entity : entities) {
			entity.setMatchs(this.matchService.addAll(entity.getMatchs()));
		}
		return this.groupRepository.saveAll(entities);
	}

	@Transactional
	@Override
	public void deleteAll(final List<GroupEntity> entities) {
		if (CollectionUtils.isNotEmpty(entities)) {
			entities.forEach(entity -> {
				this.matchService.deleteAll(entity.getMatchs());
				this.groupRepository.deleteById(entity.getId());
			});
		}
	}
}
