package com.krautsou.currencyexchange.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "currency")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Currency {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;
}
