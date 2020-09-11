package com.cars24.solution.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class UserStore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String token;
        

    protected UserStore() {}
    public UserStore(int id, String token) {
      this.id = id;
      this.token = token;
    }

    @Override
    public String toString() {
      return String.format(
          "UserStore[id=%s, token='%s']",
          id, token);
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}



   
}