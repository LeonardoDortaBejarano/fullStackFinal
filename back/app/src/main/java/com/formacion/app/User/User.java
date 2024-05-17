package com.formacion.app.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.formacion.app.Auth.LoginRequest;
import com.formacion.app.Auth.RegisterRequest;
import com.formacion.app.Roadmap.Roadmap;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User implements UserDetails{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String username;
  private String password;
  private String email;
  private Date creationDate;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Roadmap> roadmaps;


  public User(){}

  

  public User(String username, String password, String email, Date creationDate, List<Roadmap> roadmaps) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.creationDate = creationDate;
    this.roadmaps = roadmaps;
  }





  public User(RegisterRequest registerRequest) {
    this.username = registerRequest.getUsername();
    this.password = registerRequest.getPassword();
    this.email =  registerRequest.getEmail();
    this.creationDate = new Date();
  }





public Integer getId() {
    return id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
    }
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


  

}
