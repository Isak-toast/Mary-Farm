package com.ssafy.maryfarmuserservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Land implements Serializable {
    private String latitude;
    private String longitude;
}
