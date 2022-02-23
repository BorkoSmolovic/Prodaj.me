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
    @NotNull(message="Can't be null")
    private long adId;
    
    @Column
    @NotNull(message="Can't be null")
    private String image;
    
    public void setAdId(Long adId){
        this.adId = adId;
    }

    public void setImage(String image){
        this.image = image;
    }
}