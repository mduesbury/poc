package com.example.contentsearch.endpoint;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.contentsearch.exceptions.InvalidParametersException;
import com.example.contentsearch.service.FileSearchService;

@RunWith(MockitoJUnitRunner.class)
public class ContentSearchEndpointTest {

	@Mock
	private FileSearchService fileSearchService;

	@InjectMocks
	private ContentSearchEndpoint contentSearchEndpoint;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testFindFiles() throws IOException {
		List<String> words = new ArrayList<String>();
		words.add("test");
		words.add("content");

		List<String> result = new ArrayList<String>();
		words.add("/user/md/thisfile");

		when(fileSearchService.findFilesByContent(words)).thenReturn(result);

		Response response = contentSearchEndpoint.findFilesByKeyword(words);
		assertEquals(200, response.getStatus());
		assertEquals(response.getEntity(), result);
	}

	@Test
	public void testNoCriteriaSubmitted() {
		thrown.expect(InvalidParametersException.class);
		contentSearchEndpoint.findFilesByKeyword(null);
	}
}
