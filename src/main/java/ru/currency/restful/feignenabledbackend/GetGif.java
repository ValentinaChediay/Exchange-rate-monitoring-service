package ru.currency.restful.feignenabledbackend;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.currency.restful.entity.Gif;

@FeignClient(name = "gif", url = "${url-random-gif}")
public interface GetGif {
    @RequestMapping(method = RequestMethod.GET, value = "/random")
    Gif getGif(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);
}
