package com.alfeno4ka.nasabot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Модель ответа апи NASA "Фото земли"
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class EarthAnswer {

    @JsonProperty("image")
    private String imageId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
}
