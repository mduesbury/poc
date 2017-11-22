package com.example.contentsearch.service;

import java.io.IOException;
import java.util.List;

public interface FileSearchService {
	List<String> findFilesByContent(List<String> words) throws IOException;
}
