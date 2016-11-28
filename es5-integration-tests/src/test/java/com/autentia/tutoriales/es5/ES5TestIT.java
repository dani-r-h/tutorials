package com.autentia.tutoriales.es5;

import static org.hamcrest.CoreMatchers.is;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

public class ES5TestIT extends ESIntegTestCase{

	@Test
	public void shouldInitializeContextsSuccessfully() {
		assertThat(true, is(true));
	}
	
}
