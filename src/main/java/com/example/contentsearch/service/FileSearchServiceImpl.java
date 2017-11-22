package com.example.contentsearch.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.contentsearch.exceptions.InvalidParametersException;

@Service
public class FileSearchServiceImpl implements FileSearchService {

	@Value("${searchPath}")
	private String searchPath;

	@Override
	public List<String> findFilesByContent(List<String> words) throws IOException {
		if (words == null || words.isEmpty())
			throw new InvalidParametersException("Invalid parameters");

		List<String> foundFiles = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();
		for (String word : words) {
			sb.append(word + "|");
		}
		final Pattern pattern = Pattern.compile(sb.substring(0, sb.length() - 1));

		Files.walk(Paths.get(searchPath))
			.filter(p -> {
				try (Scanner e = new Scanner(p)) {
					return e.findWithinHorizon(pattern, 0) != null;
				} catch (IOException e) {
					return false;
				}
			})
			.forEach(a -> foundFiles.add(a.toString()));

		return foundFiles;
	}
}
