package nyongnyong.pangparty.entity.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import nyongnyong.pangparty.entity.member.Member;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class MemberAuthInfo implements UserDetails, Serializable {

    @Id
    private Long memberUid;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    @JsonIgnore
    private Member member;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "member_uid"))
    private Set<Role> roles;

    @LastModifiedDate
    private LocalDateTime updateTime;

    //    @Builder
    public MemberAuthInfo(Member member, String password, LocalDateTime updateTime) {
        this.member = member;
//        this.salt = salt;
        this.password = password;
        this.updateTime = updateTime;
    }

    @Builder
    public MemberAuthInfo(Member member, String email, String password, Set<Role> roles, LocalDateTime updateTime) {
        this.member = member;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.updateTime = updateTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> rolesList = this.roles.stream().map(Enum::name).collect(Collectors.toList());
        return rolesList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
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

}
