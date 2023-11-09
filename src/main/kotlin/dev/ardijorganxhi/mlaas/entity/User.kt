package dev.ardijorganxhi.mlaas.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Collections
import javax.persistence.*

@Entity
@Table(name = "users")
class User private constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1,

    @Column(name = "name", nullable = false)
    var name: String?,

    @Column(name = "surname", nullable = false)
    var surname: String?,

    @Column(name = "email", nullable = false)
    var email: String?,

    @Column(name = "password", nullable = false)
    var pass: String?,

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.emptyList()
    }


    override fun getPassword(): String? {
        return pass
    }

    override fun getUsername(): String? {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    data class Builder(
        var id: Int = -1,
        var name: String? = null,
        var surname: String? = null,
        var email: String? = null,
        var pass: String? = null
    ) {
        fun id(id: Int) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun surname(surname: String) = apply { this.surname = surname }
        fun email(email: String) = apply { this.email = email }
        fun pass(pass: String) = apply { this.pass = pass }
        fun build() = User(id, name, surname, email, pass)
    }

}

