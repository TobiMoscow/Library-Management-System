package com.library.management;

import javax.persistence.*;

@Entity
@Table(name = "library_user")
public class User {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer id;
		
		@Column(name = "name")
		private String name;
		
		@Column(name = "email")
		private String email;
		
		@Column(name = "dob")
		private String dob;
				

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

	}