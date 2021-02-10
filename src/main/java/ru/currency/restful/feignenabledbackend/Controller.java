package ru.currency.restful.feignenabledbackend;

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
public class Controller {
    private final GetCurrency getCurrency;
    private final GetGif getGif;
    private final String appId;
    private final String apiKey;
    private final String tagRich;
    private final String tagBroke;
    private final String sizeGif;
    public Controller(GetCurrency getCurrency,
                      GetGif getGif,
                      @Value("${application-id}") String appId,
                      @Value("${application-key}") String apiKey,
                      @Value("${tag-rich}") String tagRich,
                      @Value("${tag-broke}") String tagBroke,
                      @Value("${size-gif}") String sizeGif) {
        this.getCurrency = getCurrency;
        this.getGif = getGif;
        this.appId = appId;
        this.apiKey = apiKey;
        this.tagRich = tagRich;
        this.tagBroke = tagBroke;
        this.sizeGif = sizeGif;
    }

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
                if(s.getValue()-s.getPrevious()>=0){return "<iframe src=\""+ getGif.getGif(apiKey, tagRich).getData().get("embed_url") + "\" width=\"1500\" height=\"700\" frameBorder=\"0\" class=\"giphy-embed\" allowFullScreen>";}
                else return "<iframe src=\""+ getGif.getGif(apiKey, tagBroke).getData().get("embed_url") + sizeGif;
            }
        }
        return "Страны с таким кодом валюты не существует";
    }

}
