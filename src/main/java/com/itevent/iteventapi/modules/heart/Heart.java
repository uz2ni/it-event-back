package com.itevent.iteventapi.modules.heart;

import com.itevent.iteventapi.common.CommonField;
import com.itevent.iteventapi.modules.account.Account;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Heart extends CommonField {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private Long targetId;

    @Column(nullable = false)
    @ManyToOne
    private Account account;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HeartType heartType;

}
