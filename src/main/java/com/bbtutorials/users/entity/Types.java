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
public class Types {
	
	@Id
	@Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message="{NotNull.User.firstName}")
    private String name;
    

}
