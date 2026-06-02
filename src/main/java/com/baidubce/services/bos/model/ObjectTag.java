package com.baidubce.services.bos.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ObjectTag {

    @JsonAlias({"tagInfo"})
    Map<String, String> tagInfo = new HashMap<>();

}
