package com.dreamblitz.autointuit.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@NoArgsConstructor
@Scope("prototype")
public class Dimensions implements Hideable {
    Integer height;
    Integer width;
    Integer length;
    Integer wheelBase;
    Integer bootSpace;
}
