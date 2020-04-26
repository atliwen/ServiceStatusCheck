package com.atliwen.news;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author 李文
 * @create 2020-03-06 10:37
 **/
@Component
@Slf4j
public class WeChatNews
{
    private Expression text;

    public WeChatNews() {
        String strs = "{\n" +
                "    \"msgtype\": \"news\",\n" +
                "    \"news\": {\n" +
                "       \"articles\" : [\n" +
                "           {\n" +
                "               \"title\" : \"可靠消息服务通知\",\n" +
                "               \"description\" : \"#{#name}\\n地址：#{#url}\\n#{#data}  \",\n" +
                "               \"url\" : \"#{#url}\",\n" +
                "               \"picurl\" : \"http://www.atliwen.com.cn/u/cms/www/201508/10152643lor1.jpg\"\n" +
                "           }\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        SpelExpressionParser parser = new SpelExpressionParser();
        this.text = parser.parseExpression(strs, ParserContext.TEMPLATE_EXPRESSION);
    }


    RestTemplate restTemplate = new RestTemplate();

    public void postForEntity(String webhookUrl,String name, String url, String data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity<>(getNews(name, url, data), headers);
        ResponseEntity e = this.restTemplate.postForEntity(webhookUrl, entity, String.class);
        log.debug("响应内容：" + e.toString());
    }

    public String getNews(String name, String ip, String data) {
        EvaluationContext ctx = new StandardEvaluationContext();
        //设置两个变量
        ctx.setVariable("name", name);
        ctx.setVariable("url", ip);
        ctx.setVariable("data", data);
        return text.getValue(ctx, String.class);
    }
}
