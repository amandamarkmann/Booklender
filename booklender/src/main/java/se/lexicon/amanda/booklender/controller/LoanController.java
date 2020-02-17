package se.lexicon.amanda.booklender.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.lexicon.amanda.booklender.dto.LoanDto;
import se.lexicon.amanda.booklender.service.LoanService;

@RestController
public class LoanController {

	private LoanService loanService;

	@Autowired
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}
	
	@GetMapping("api/loans/{loanId}")
	public ResponseEntity<Optional<LoanDto>> findById(@PathVariable ("loanId") long loanId){
	
	return ResponseEntity.ok().body(loanService.findById(loanId));
	}

	
	@GetMapping("api/loans")
	public ResponseEntity<List<LoanDto>> find
						(@RequestParam (defaultValue = "all", name = "type") String type,
						@RequestParam (required = false, name = "value") String value) {
			if(type.equalsIgnoreCase("all") && value == null) {
				return ResponseEntity.ok().body(loanService.findAll());
			}
			else if(type.equalsIgnoreCase("terminated")) {
				return ResponseEntity.ok(loanService.findByTerminated(Boolean.valueOf(value)));
			}
			else if(type.equalsIgnoreCase("userId")) {
				return ResponseEntity.ok(loanService.findByUserId(Integer.parseInt(value)));
			}
			else if(type.equalsIgnoreCase("bookId")) {
				return ResponseEntity.ok(loanService.findByBookId(Integer.parseInt(value)));
			}
			else {
				throw new IllegalArgumentException();
			}
		}
	
	
	@PostMapping("api/loans")
	public ResponseEntity<LoanDto> create(@RequestBody LoanDto loanDto) {
		
		if(loanDto == null) {
			throw new IllegalArgumentException();
		}
		
		return ResponseEntity.ok(loanService.create(loanDto));
		
		
	}
		
	@PutMapping("api/loans")
	public ResponseEntity<LoanDto> update(@RequestBody LoanDto loanDto) {
		
		if(loanDto == null) {
			throw new IllegalArgumentException();
		}
		
		
		return ResponseEntity.ok(loanService.update(loanDto));
	}
	
	
	
}
