package it.epicode.BE_W6_Exam.exceptions;

import lombok.Data;

@Data
public class Error {
	private String message;
	private String details;
	private String status;
}
