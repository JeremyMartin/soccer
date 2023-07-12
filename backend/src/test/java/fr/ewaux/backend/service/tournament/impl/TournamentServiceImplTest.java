package fr.ewaux.backend.service.tournament.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Pair;
import org.junit.jupiter.api.Test;
import org.paukov.combinatorics3.Generator;

@Slf4j
class TournamentServiceImplTest {

	//			List<String> list = Arrays.asList("A", "B");
//	List<String> list = Arrays.asList("A", "B", "C");
	List<String> list = Arrays.asList("A", "B", "C", "D");
	//	List<String> list = Arrays.asList("A", "B", "C", "D", "E");
	List<Pair<String, String>> combinaisons = new ArrayList<>();
	List<Pair<String, String>> matchs = new ArrayList<>();

	@Test
	void count() {
		int nbG = 2;
		int r = 3 % nbG;
		log.info(String.valueOf(r));
		r = 1 % nbG;
		log.info(String.valueOf(r));
		r = 5 % nbG;
		log.info(String.valueOf(r));
		r = 4 % nbG;
		log.info(String.valueOf(r));
	}

	@Test
	void simple() {
		this.combinaisons = Generator.combination(this.list).simple(2).stream().map(l -> new Pair<>(l.get(0), l.get(1)))
			.collect(Collectors.toList());
		log.info(combinaisons.toString());
		this.addFirstCombinaison();
		if (this.combinaisons.size() != this.matchs.size()) {
			List<Pair<String, String>> rest = this.combinaisons.stream().filter(p -> !this.matchs.contains(p)).collect(Collectors.toList());
			Collections.shuffle(rest);
			this.matchs.addAll(rest);
		}
		log.info(this.matchs.toString());
		this.reverse();
	}

	private void addFirstCombinaison() {
		Set<String> already = new HashSet<>();
		for (Pair<String, String> pair : this.combinaisons) {
			if (!already.contains(pair.getFirst())) {
				already.add(pair.getFirst());
				already.add(pair.getSecond());
				this.matchs.add(pair);
			}
		}
		List<Pair<String, String>> restPair = this.combinaisons.stream().filter(p -> !this.matchs.contains(p)).collect(Collectors.toList());
		restPair.stream().filter(p -> !already.contains(p.getSecond())).forEach(p -> {
			this.matchs.add(p);
			already.add(p.getSecond());
		});
		log.info(this.matchs.toString());
	}

	private void reverse() {
		List<Pair<String, String>> reverse = this.matchs.stream().map(p -> new Pair<>(p.getSecond(), p.getFirst()))
			.collect(Collectors.toList());
		log.info(reverse.toString());
	}
}
