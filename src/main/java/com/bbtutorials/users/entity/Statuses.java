package com.bbtutorials.users.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Entity
@Data
public class Statuses {
	
	@Id
	@Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message="Name can't be null")
    private String name;
    
}