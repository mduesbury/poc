package com.example.contentsearch.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.contentsearch.exceptions.InvalidParametersException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FileSearchServiceTest.Config.class)
public class FileSearchServiceTest extends Assert {

	@Autowired
	private FileSearchService fileSearchService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Configuration
	static class Config {

		@Bean
		public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
			final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
			Properties properties = new Properties();

			final File f = new File(FileSearchServiceTest.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			properties.setProperty("searchPath", f.getAbsolutePath());

			pspc.setProperties(properties);
			return pspc;
		}

		@Bean
		public FileSearchService fileSearchService() {
			return new FileSearchServiceImpl();
		}

	}

	@Test
	public void test1FileFound() {
		List<String> words = new ArrayList<String>();
		words.add("test3");

		try {
			List<String> listResult = fileSearchService.findFilesByContent(words);
			for (String result : listResult)
				System.out.println(result);
			assertEquals(1, listResult.size());
		} catch (IOException e) {
			fail("Unexpected IOException");
		}
	}

	@Test
	public void test2FilesFound() {
		List<String> words = new ArrayList<String>();
		words.add("test");

		try {
			List<String> listResult = fileSearchService.findFilesByContent(words);
			for (String result : listResult)
				System.out.println(result);
			assertEquals(2, listResult.size());
		} catch (IOException e) {
			fail("Unexpected IOException");
		}
	}

	@Test
	public void test3FilesFound() {
		List<String> words = new ArrayList<String>();
		words.add("Config");
		words.add("test");

		try {
			List<String> listResult = fileSearchService.findFilesByContent(words);
			for (String result : listResult)
				System.out.println(result);
			assertEquals(3, listResult.size());
		} catch (IOException e) {
			fail("Unexpected IOException");
		}
	}

	@Test
	public void testException() {
		List<String> words = new ArrayList<String>();
		thrown.expect(InvalidParametersException.class);

		try {
			fileSearchService.findFilesByContent(words);
		} catch (IOException e) {
			fail("Unexpected IOException");
		}
	}
}
