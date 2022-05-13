package com.example.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController	
@RequestMapping(path = "/payments")
public class PaymentController{

	@Autowired
	private final PaymentRepository paymentRepository;
    PaymentController(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

	@GetMapping
	public List<Payment> findAllPayments(){
		return paymentRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Payment> findPaymentByID(@PathVariable(value = "id") long id){
		Optional<Payment> payment = paymentRepository.findById(id);
		if (payment.isPresent()){
			return ResponseEntity.ok().body(payment.get());
		} else {
			ResponseEntity.notFound().build();
		}
		return null;
	}

	//@PostMapping
	//public Payment newPayment(@Validated @RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
										  //Payment newPayment){return paymentRepository.save(newPayment);}









	}



