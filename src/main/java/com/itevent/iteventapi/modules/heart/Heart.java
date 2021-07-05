package com.itevent.iteventapi.modules.heart;

import com.itevent.iteventapi.common.CommonField;
import com.itevent.iteventapi.common.utils.ModelMapperUtils;
import com.itevent.iteventapi.modules.account.Account;
import com.itevent.iteventapi.modules.heart.dto.HeartDto;
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

    @Column(nullable = false)
    private Long targetId;

    @ManyToOne(optional = false)
    private Account account;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HeartType heartType;

    public static Heart of(HeartDto heartDto, Account account) {
        Heart heart = ModelMapperUtils.getModelMapper().map(heartDto, Heart.class);
        heart.setAccount(account);
        heart.setTargetId(heart.getTargetId());
        return heart;
    }

    public boolean isAccount(Account account) {
        if(!account.getEmail().equals(this.account.getEmail())) {
            throw new IllegalArgumentException("사용자가 동일하지 않습니다.");
        }
        return true;
    }
}
