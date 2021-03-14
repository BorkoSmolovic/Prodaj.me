package com.bbtutorials.users.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Entity
@Data
public class Images {
	
	@Id
	@Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull(message="Ad can't be null")
    private long adId;
    @Column
    
    @NotNull(message="Ad can't be null")
    private Blob image;
    
}