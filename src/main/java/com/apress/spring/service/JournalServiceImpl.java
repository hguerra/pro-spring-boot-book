package com.apress.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.spring.domain.Journal;
import com.apress.spring.repository.JournalRepository;

import lombok.extern.slf4j.Slf4j;

@Service("journalService")
@Slf4j
public class JournalServiceImpl implements JournalService {

	@Autowired
	private JournalRepository repository;

	@Override
	public Journal save(Journal journal) {
		log.info("> Inserting data {}.", journal);
		return this.repository.save(journal);
	}

	@Override
	public List<Journal> findAll() {
		log.info("> Retrieving all journals.");
		return this.repository.findAll();
	}
}
