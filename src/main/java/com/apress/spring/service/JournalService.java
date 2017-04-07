package com.apress.spring.service;

import java.util.List;

import com.apress.spring.domain.Journal;

public interface JournalService {

	Journal save(Journal journal);

	List<Journal> findAll();
}
