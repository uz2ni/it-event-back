package com.itevent.iteventapi.crawler;

import com.itevent.iteventapi.common.CommonField;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Shop extends CommonField {

    @Id @GeneratedValue
    private Long id;

    private String shopName;

    private String siteUrl;

    @OneToMany(mappedBy = "shop")
    @OrderBy("createdDate")
    private List<Product> products = new ArrayList<>();
}