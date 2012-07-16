package com.bosch.ubk.test.sample.host.internal;

import org.junit.Test;

public class ThrowsNullPointerImplTest {
	
	@Test(expected = NullPointerException.class)
	public void firstTest() {
		ThrowsNullpointerImpl testObject = new ThrowsNullpointerImpl();
		testObject.service();
	}
	
}
