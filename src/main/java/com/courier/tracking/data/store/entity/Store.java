package com.courier.tracking.data.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@EqualsAndHashCode (of = "id")
@Table (name = "store")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Store {

	@Id
	@Column (name = "id")
	private String id;

	@Column (name = "name")
	private String name;

	@Column (name = "city")
	private String city;

	@Column (name = "lat")
	private Double lat;

	@Column (name = "lng")
	private Double lng;

}
