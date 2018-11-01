package top.plusy.plugtest;

import net.sf.json.JSONObject;
import top.plusy.tkxx.TK05Plug;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestPlug {
    public static void main(String args[])
    {
        String testPubVer = "Djg2MTYzODAzNDE3NDc2OFvAguQAAAAAEVRLMDVfU1dfVjMuMS1CMDE1";
        String testGetAgps = "Czg2MTYzODAzNDE3NDc2OFvFtE4AAAADAAO//UIwMTc=";
        String testGetFeets = "ODYxNjM4MDM0MTc0NzUwW65QAA0AAAABAAAAAA==";
        String testLoc = "EDg2MTYzODAzNDE3NDc2OFvAg6QAAAADAAAAAAAAAAAAAAAAPgn+AA==";

        String testBell = "{\"msgId\":3, \"orderId\":12,\"ringCount\":1}";
        String testConfig = "{\"msgId\":4, \"orderId\":12,\"rideInterval\":300}";
        String test258 = "{\"msgId\":5, \"orderId\":12}";
        String testSyncTime = "{\"msgId\":7, \"orderId\":23,\"rOrderId\":12,\"timestamp\":1524821789,\"timezone\":20}";
        String testSetDomain="{\"msgId\":9, \"orderId\":23, \"domain\": \"192.168.1.18\", \"domainLen\":12, \"domainType\":1, \"port\":80}";
        String testAgpsData="{ \"msgId\": 1, \"rOrderId\": 1, \"packs\": 11, \"index\": 11, \"dataLen\": 480, \"data\": \"AAAnAAABKl0AAH3WFKVbeSkBGieIn/klGP5IyJTVlBVAH7C0//95ES4oKez//2kdAABOTAAA5fT//4kAAAC+AAAAAAAAAINptWITA1gAAQAMAAABAAAQVwAAI5RQACpdAAAaAAACKl0AAI3gFKXpIEsB2mvMm9Umuv4fac/6sxbUHhO0//9OFiIo8Oz//3MdAACKSwAAUPT//1cAAACiAAAAAAAAAOedtWITA1gAAQANAAAAAABLq///EeDa/ypdAACc/wABKl0AAF2v7cp3w7sBk0OrgWsLiv6urIvFFbCLMyTp//9p/egnYpT//8QZAACkKQAAwp3//0r////a/f//AAAAAGFPtWITA1gAAQAOAAAAAADcUgEAD/c2ACpdAABAAAABKl0AAJ/sFKWHVfYA5zQ5qikqZwOnWr54+HXhc962//9tvQ0n1/z//8JGAAA5LwAAG/7//zAAAACo////AAAAAB02\" }";

        JSONObject jsonObject = TK05Plug.decodeFromBase64(testPubVer);
        System.out.println("INPUT: " + testPubVer);
        System.out.println("OUTPUT:" + jsonObject.toString());

        jsonObject = TK05Plug.decodeFromBase64(testGetAgps);
        System.out.println("INPUT: " + testGetAgps);
        System.out.println("OUTPUT:" + jsonObject.toString());

        jsonObject = TK05Plug.decodeFromBase64(testGetFeets);
        System.out.println("INPUT: " + testGetFeets);
        System.out.println("OUTPUT:" + jsonObject.toString());



        String base64date = TK05Plug.encodeFromJsonString(testBell);
        System.out.println("INPUT: " + testBell);
        System.out.println("OUTPUT:" + base64date);

        base64date = TK05Plug.encodeFromJsonString(testConfig);
        System.out.println("INPUT: " + testConfig);
        System.out.println("OUTPUT:" + base64date);

        base64date = TK05Plug.encodeFromJsonString(test258);
        System.out.println("INPUT: " + test258);
        System.out.println("OUTPUT:" + base64date);

        base64date = TK05Plug.encodeFromJsonString(testSyncTime);
        System.out.println("INPUT: " + testSyncTime);
        System.out.println("OUTPUT:" + base64date);

        base64date = TK05Plug.encodeFromJsonString(testSetDomain);
        System.out.println("INPUT: " + testSetDomain);
        System.out.println("OUTPUT:" + base64date);

        base64date = TK05Plug.encodeFromJsonString(testAgpsData);
        System.out.println("INPUT: " + testAgpsData);
        System.out.println("OUTPUT:" + base64date);

        Long ts = 1539686476L * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date(ts)));
    }
}
