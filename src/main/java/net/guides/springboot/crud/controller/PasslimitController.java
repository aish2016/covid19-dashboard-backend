package net.guides.springboot.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot.crud.exception.ResourceNotFoundException;
import net.guides.springboot.crud.model.Passlimit;
import net.guides.springboot.crud.repository.PasslimitRepository;
import net.guides.springboot.crud.service.SequenceGeneratorService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class PasslimitController {
	@Autowired
	private PasslimitRepository dailyLimitRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping("/dailyLimits")
	public List<Passlimit> getAlldailyLimits() {
		return dailyLimitRepository.findAll();
	}

	@GetMapping("/dailyLimits/{id}")
	public ResponseEntity<Passlimit> getdailyLimitById(@PathVariable(value = "id") Long dailyLimitId)
			throws ResourceNotFoundException {
		Passlimit dailyLimit = dailyLimitRepository.findById(dailyLimitId)
				.orElseThrow(() -> new ResourceNotFoundException("dailyLimit not found for this id :: " + dailyLimitId));
		return ResponseEntity.ok().body(dailyLimit);
	}

	@PostMapping("/dailyLimits")
	public Passlimit createdailyLimit(@Valid @RequestBody Passlimit dailyLimit) {
		dailyLimit.setId(sequenceGeneratorService.generateSequence(Passlimit.SEQUENCE_NAME));
		return dailyLimitRepository.save(dailyLimit);
	}

	@PutMapping("/dailyLimits/{id}")
	public ResponseEntity<Passlimit> updatedailyLimit(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Passlimit dailyLimitDetails) throws ResourceNotFoundException {
		Passlimit dailyLimit = dailyLimitRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("dailyLimit not found for this id :: " + id));

		dailyLimit.setDailyLimit(dailyLimitDetails.getDailyLimit());
		dailyLimit.setCityName(dailyLimitDetails.getCityName());
		dailyLimit.setDailyLimit(dailyLimitDetails.getDailyLimit());
		dailyLimit.setLimitDate(dailyLimitDetails.getLimitDate());
		final Passlimit updateddailyLimit = dailyLimitRepository.save(dailyLimit);
		return ResponseEntity.ok(updateddailyLimit);
	}

	@DeleteMapping("/dailyLimits/{id}")
	public Map<String, Boolean> deletedailyLimit(@PathVariable(value = "id") Long dailyLimitId)
			throws ResourceNotFoundException {
		Passlimit dailyLimit = dailyLimitRepository.findById(dailyLimitId)
				.orElseThrow(() -> new ResourceNotFoundException("dailyLimit not found for this id :: " + dailyLimitId));

		dailyLimitRepository.delete(dailyLimit);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
