package com.theodorol.todosimple.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name= "user")
public class User {

    public interface CreateUser{}
    public interface UpdateUser{}

    //id do user que vai ser autoincrimetar 
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name ="id", unique = true)
    private Long id; 

    //vai criar uma coluna user name, que vai ter tamanho 100 caracteres unico e não pode ter valor vazio nele
    @Column(name ="userName", length = 100, nullable = false, unique =  true)
    
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = UpdateUser.class)
    @Size(min = 2, max = 100)
    private String userName;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(min = 8, max = 60, groups = {CreateUser.class, UpdateUser.class})
    private String password; 

    public User(){
    }

    public User(Long id, String userName, String password){
        this.id = id; 
        this.userName = userName; 
        this.password = password; 

    }

    public void setId(Long id){
         this.id = id; 
    }
    public Long getId(){
        return this.id; 
    }

    public void setUserName(String UserName){
        this.userName = UserName; 
    }

    public String getUserName(){
        return this.userName; 
    
    }

    public void setPassword(String password){
        this.password = password; 
    }

    public String getPassword(){
        return this.password;     
    }

    @Override 
    public boolean equals(Object obj){
        if(obj == this){
            return true; 
        }
         
        if(!(obj instanceof User)||(obj == null)){
            return false; 
        }
        User other = (User) obj; 
        
        if(this.id == null){
            if(other.id != null){
                return false; 
            }
            else if(!(this.id.equals(other.id))){
                return false;

            }
            
        }
        return Objects.equals(this.id, other.id) &&
               Objects.equals(this.userName, other.userName) &&
               Objects.equals(this.password, other.password);
}

    @Override 
    //hashcode para o id
    public int hashCode(){
        final int prime = 31;
        int resultRun = 1; 
        resultRun = prime*resultRun+(this.id==null ? 0 : this.id.hashCode()); 
        return resultRun; 
    }
}
