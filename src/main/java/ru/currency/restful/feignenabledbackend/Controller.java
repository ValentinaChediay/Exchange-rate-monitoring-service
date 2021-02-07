package ru.currency.restful.feignenabledbackend;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.currency.restful.entity.Currency;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class Controller {
    private final GetCurrency getCurrency;
    private final GetGif getGif;

    @Value("${application-id}")
    private String appId;
    @Value("${application-key}")
    private String apiKey;
    @Value("${tag-rich}")
    private String tagRich;
    @Value("${tag-broke}")
    private String tagBroke;
    @Value("${size-gif}")
    private String sizeGif;

    @GetMapping("/")
    public String countrySelection(){
        List<String> listCharCode = new ArrayList<>(getCurrency.getBaseObjectLatest(appId).getRates().keySet());
        return "Укажите код валюты, интересующей вас страны: " + listCharCode.toString();
    }
    @GetMapping("/{charCode}")
    public String answer(@PathVariable String charCode){
        ArrayList<Currency> storeCurrencies = new ArrayList<>();
        for(Map.Entry<String, Double> m : getCurrency.getBaseObjectLatest(appId).getRates().entrySet()){
            Currency s = new Currency(m.getKey(), m.getValue(), null);
            storeCurrencies.add(s);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String datePrevious = format1.format(calendar.getTime());

        List<Double> previous = new ArrayList<>(getCurrency.getBaseObjectHistorical(datePrevious, appId).getRates().values());
        for(int i = 0; i < previous.size(); i++){
            storeCurrencies.get(i).setPrevious(previous.get(i));
        }
        for(Currency s : storeCurrencies){
            if(s.getCharCode().equals(charCode)){
                if(s.getValue()-s.getPrevious()>=0){return "<iframe src=\""+ getGif.getGifGood(apiKey, tagRich).getData().get("embed_url") + "\" width=\"1500\" height=\"700\" frameBorder=\"0\" class=\"giphy-embed\" allowFullScreen>";}
                else return "<iframe src=\""+ getGif.getGifBad(apiKey, tagBroke).getData().get("embed_url") + "\" width=\"1500\" height=\"700\" frameBorder=\"0\" class=\"giphy-embed\" allowFullScreen>";
            }
        }
        return "Страны с таким кодом валюты не существует";
    }

}
