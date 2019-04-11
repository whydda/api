package com.example.api.util;

import com.example.api.core.DefaultParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONTokener;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class ApiUtils {

    /**
     * 파라미터 여부 체크
     * @param defaultParams
     * @param arrRequiedValue
     * @return
     */
    public static void chkParams(DefaultParams defaultParams, String... arrRequiedValue){
        for(String key : arrRequiedValue){
            if(null == defaultParams.get(key)){
                defaultParams.put(key, "");
            }
        }
    }

    /**
     * 필수값 체크
     * @param defaultParams
     * @param arrRequiedValue
     * @return
     */
    public static boolean chkValidation(DefaultParams defaultParams, String... arrRequiedValue){
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.set(true);

        defaultParams.getMap().forEach((k,v) -> {
                    for(String key : arrRequiedValue){
                        if(key.equals(k)){
                            if(String.valueOf(defaultParams.get(key)).equals("null") || String.valueOf(defaultParams.get(key)).length() == 0){
                                atomicBoolean.set(false);
                                break;
                            }
                        }
                    }
                }
        );
        return atomicBoolean.get();
    }


    /**
     * 필수값 체크
     * @param defaultParams
     * @param arrRequiedValue
     * @return
     */
    public static boolean chkSize(DefaultParams defaultParams, String... arrRequiedAndSizeValue){
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        atomicBoolean.set(true);

        defaultParams.getMap().forEach((k,v) -> {
                    for(String key : arrRequiedAndSizeValue){
                        String[] arrKey = key.split("\\|");
                        if(arrKey[0].equals(k)){
                            try{
                                if(String.valueOf(v).length() > Integer.parseInt(arrKey[1])){
                                    atomicBoolean.set(false);
                                    break;
                                }
                            }catch (Exception e){
                                log.error("데이터 파싱 오류.", e);
                            }
                        }
                    }
                }
        );

        return atomicBoolean.get();
    }


    /**
     * String의 byte수 체크
     * @param str
     * @param charactrer
     * @return
     * @throws Exception
     */
    public static int getBytesLength(String str, String charactrer) throws Exception{
        if(charactrer == null){
            return str.getBytes("UTF-8").length;
        }else{
            return str.getBytes(charactrer).length;
        }

    }

    /**
     *
     * @param s
     * @return
     * @throws JSONException
     */
    public static Object customJSONTokener(String s) throws JSONException {
        if(!StringUtils.isEmpty(s)){
            return new JSONTokener(s).nextValue();
        }else{
            return "";
        }

    }
}
