package fr.ewaux.commons.utilities.utils;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class StringHelperTest {

	@Test
	void generate() {
		assertEquals("",StringHelper.generate(-1));
		assertEquals("A",StringHelper.generate(0));
		assertEquals("B",StringHelper.generate(1));
		assertEquals("C",StringHelper.generate(2));
		assertEquals("Y",StringHelper.generate(24));
		assertEquals("Z",StringHelper.generate(25));
		assertEquals("AA",StringHelper.generate(26));
		assertEquals("AB",StringHelper.generate(27));
		assertEquals("ZY",StringHelper.generate(700));
		assertEquals("ZZ",StringHelper.generate(701));
		assertEquals("AAA",StringHelper.generate(702));
		assertEquals("AAB",StringHelper.generate(703));
		assertEquals("AAZ",StringHelper.generate(727));
		assertEquals("ABA",StringHelper.generate(728));
	}
}
