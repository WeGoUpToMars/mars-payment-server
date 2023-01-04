package com.mars.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.common.constant.CommonExceptionInfo;
import java.util.Map;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public interface MultiValueMapConverter {

  static MultiValueMap<String, String> convert(ObjectMapper mapper, Object dto) {
    try {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      Map<String, String> map = mapper.convertValue(dto, new TypeReference<>() {});
      params.setAll(map);

      return params;
    } catch (Exception e) {
      throw CommonExceptionInfo.CANNOT_PARSE_URL_PARAMETER.exception();
    }
  }
}
