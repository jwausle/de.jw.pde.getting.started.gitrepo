package com.bosch.ubk.test.sample.host.internal.api.impl;

import org.junit.Assert;
import org.junit.Test;

public class LongRunningLRuntimeImplTest {

	@Test
	public void hardToTest() {
		LongRunningRuntimeImplUncleaned testObj = new LongRunningRuntimeImplUncleaned();

		Assert.assertNotNull(testObj.getPath());

	}

	@Test
	public void cleanerCodeVariantIsSimplerToConfigTest() {
		String pathToAnywhare = "path/to/anywhere/";
		LongRunningLRuntimeImpl testObj = new LongRunningLRuntimeImpl(pathToAnywhare);
		
		String expectedResult = testObj.getPath();
		String actualResult = LongRunningLRuntimeImpl.concatPrefixBeforeConstantPathSuffix(pathToAnywhare);
		Assert.assertEquals(expectedResult, actualResult);
	}
}
