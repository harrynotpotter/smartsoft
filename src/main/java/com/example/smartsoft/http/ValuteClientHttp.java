package com.example.smartsoft.http;

import com.example.smartsoft.models.dto.ValCursDto;
import feign.Headers;
import feign.RequestLine;

@Headers("Content-Type: application/xml; charset=windows-1251")
public interface ValuteClientHttp {

    @RequestLine("GET /XML_daily.asp")
    ValCursDto getValCurs();


}
