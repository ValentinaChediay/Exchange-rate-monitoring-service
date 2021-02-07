package ru.currency.restful.feignenabledbackend;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.currency.restful.entity.BaseObject;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@FeignClient(name = "currency", url = "${url-currency}")
public interface GetCurrency {
    @RequestMapping(method = RequestMethod.GET, value = "/latest.json", consumes = APPLICATION_JSON_VALUE)
    BaseObject getBaseObjectLatest(@RequestParam("app_id") String appId);
    @RequestMapping(method = RequestMethod.GET, value = "/historical/{datePrevious}.json", consumes = APPLICATION_JSON_VALUE)
    BaseObject getBaseObjectHistorical(@PathVariable("datePrevious") String datePrevious, @RequestParam("app_id") String appId);
}
