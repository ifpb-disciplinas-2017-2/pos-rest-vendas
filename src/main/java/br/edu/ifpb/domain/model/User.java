
package br.edu.ifpb.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michelle Oliveira
 * @mail miolivc@outlook.com
 * @since 31/01/2018
 */

@Entity
@XmlRootElement
public class User implements Serializable {
    
    @Id
    @Column(length = 45)
    private String email;
    
    @Column(length = 16)
    private String password;

    public User() {
    }

    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public static User of(String email, String password) {
        return new User(email, password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
