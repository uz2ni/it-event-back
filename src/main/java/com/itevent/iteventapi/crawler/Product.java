package com.itevent.iteventapi.crawler;

import com.itevent.iteventapi.common.CommonField;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Product extends CommonField {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String productId;

    private String title;

    private String price;

    private String thumbUrl;

    private String detailUrl;

    @ManyToOne
    private Shop shop;
}
