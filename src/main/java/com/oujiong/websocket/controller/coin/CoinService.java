package com.oujiong.websocket.controller.coin;

import java.util.HashMap;
import java.util.Map;

import com.oujiong.websocket.model.CoinResult;
import com.oujiong.websocket.utils.HttpUtils;
import com.oujiong.websocket.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;


/**
 * 功能描述：接口服务，调用虚拟币行情接口
 */
@Slf4j
public class CoinService {

    public static CoinResult getStockInfo() {
        String host = "http://alirm-gbdc.konpn.com";
        String path = "/query/gbdc";
        String method = "GET";
        String appcode = "056ed9cdaa674647b6c04b87fe394fcb";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        //BTC代表返回比特币相关信息，如果这里传入ETH那就代表返回以太坊信息
        querys.put("symbol", "BTC");

        try {
            //返回连接信息，如果里面带有200，说明连接接口成功
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //将response的body信息转为字符串
            String responseText = EntityUtils.toString(response.getEntity());
            //上面部分只要根据你购买的api接口说明操作就可以，下面才是你需要处理的
            //将json格式的字符串（根据一定规则）赋值给实体对象（JsonUtils是自己的一个工具类）
            CoinResult coinResult = JsonUtils.objectFromJson(responseText, CoinResult.class);
            log.info("控制台打印虚拟币当前信息=======================================");
            //TODO  因为获取交易所数据是花钱购买到 现在这里已经过期了，所以这里返回的coinResult对象为null了，这里会报空指针
            System.out.println(coinResult.toString());
            return coinResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
