package com.todoexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDto {

	private Long id;
	private String title;
	private String description;
	private boolean completed;
	private String status;

}
