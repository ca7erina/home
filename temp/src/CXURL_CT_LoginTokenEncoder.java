import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import junit.framework.Assert;

public class CXURL_CT_LoginTokenEncoder
{

    private String URL = "http://access.cxindex.com/tl/";
    private int validTime = 10;
    private String key = "dgh$%^&*U*6%cvGGY&^R8HOb867rtyuFGHJK";
    private String seperator = ":";



    public String encodeLink (int type, int id, int pin) throws UnsupportedEncodingException
    {
        long timestamp = System.currentTimeMillis() / 1000;
        //long timestamp = 1464262585;
        //long timestamp = 1464259721;
        String pinHash = sha1("" + type + id + pin + timestamp + validTime + key);

        String string = "" + type + ":" + id + ":" + timestamp + ":" + validTime + ":" + pinHash;

        String key = md5(this.key);
        String crypt = "";
        int j = 0;
        int k = key.getBytes().length;

        for(int i = 0; i < string.getBytes().length; i++)
        {
            crypt += chr(ord(string.substring(i,i+1)) ^ ord(key.substring(j,j+1)));
            j++;
            if(j >= k){j = 0;}
        }

        crypt += this.seperator + dechex(crc32(md5(crypt)+ md5(this.key)));

        //System.out.println(gzdeflate("helloworld",9));
        //System.out.println(strtr("Hilla Warld","ia","eo"));
        //System.out.println(rtrim("Hello World!", "World!"));
        //System.out.println(base64_encode("helloworld"));
        //System.out.println(gzdeflate(crypt,9));
        //System.out.println(base64_encode(gzdeflate(crypt,9)));

        String url= this.URL + rtrim(strtr(base64_encode(gzdeflate(crypt,9)), "+/", "-_"), "=");
        System.out.println(url);
        return url;
    }









    //tests*****************************************************
    @Test
    public void pinHashTest()
    {
        String pinHash = sha1("" + 3 + 3 + 569896 + 1464016607 + validTime + key);
        Assert.assertEquals("0d8785a6e921856c239b0a7fbaeac425e1f110e7", pinHash);
    }

    @Test
    public void stringTest()
    {
        String pinHash = sha1("" + 3 + 3 + 569896 + 1464016607 + validTime + key);
        String string = "" + 3 + ":" + 3 + ":" + 1464016607 + ":" + validTime + ":" + pinHash;
        Assert.assertEquals("3:3:1464016607:10:0d8785a6e921856c239b0a7fbaeac425e1f110e7", string);
    }

    @Test
    public void md5Test()
    {
        String key = md5(this.key);
        Assert.assertEquals("e4f54a5d2c8949501295b393ad16e364", key);
    }

    @Test
    public void kTest() throws UnsupportedEncodingException
    {
        String key = md5(this.key);
        int k = key.getBytes().length;
        Assert.assertEquals(32, k);
    }

    @Test
    public void gzdeflateTest()
    {
        String output = "";
        String s = gzdeflate("helloworld", 9);
        for (int i = 0; i < s.length() ; i++)
        {
            output += (int)s.charAt(i);
        }

        Assert.assertEquals("2037220520120147207472027310", output);
    }

    @Test
    public void chrTest()
    {
        Assert.assertEquals('A', chr(65));
    }

    @Test
    public void ordTest()
    {
        Assert.assertEquals(104, ord("hello"));
    }




    @Test
    public void endToEndTest()
    {
        CXURL_CT_LoginTokenEncoder x = new CXURL_CT_LoginTokenEncoder();
        String url = "";
        try {
            url = x.encodeLink(3, 22,746412);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("http://access.cxindex.com/tl/C-ML5WcNZQ5gCOXi4eHhZ2Tk4OZlZ4wKDw1iCWDgDg4NZAxgDw8NCA0LYYgO4GALZGWKYAjmiuJmZrJKskxLMTQ2MwcA",
                url);
    }

    //util*******************************************************

    private String strtr(String string1, String string2, String string3) {
        return StringUtils.replaceChars(string1, string2, string3);
    }

    private String base64_encode(String str) throws UnsupportedEncodingException {
        String encoded = Base64.encodeBase64String(str.getBytes());
        return encoded;
    }

    private String gzdeflate(String str, int i) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Deflater deflater = new Deflater(1, true);
        DeflaterOutputStream out = new DeflaterOutputStream(baos, deflater);
        try
        {
            byte[] data = str.getBytes();
            out.write(data);
            out.flush();
            out.close();
        }
        catch
                (Exception e) {
            e.printStackTrace();
        }


        String s = new String(baos.toByteArray());
        return s;

    }

    public String rtrim (String str1, String str2){
        return StringUtils.strip(str1, str2);
    }

    public String dechex(Integer i){
        return Integer.toHexString(i);
    }

    //The crc32() function calculates a 32-bit CRC (cyclic redundancy checksum) for a string.
    public Integer crc32(String str){
        CRC32 crc = new CRC32();
        crc.update(str.getBytes());
        Long l= crc.getValue();
        return l.intValue();
    }


    //The ord() function returns the ASCII value of the first character of a string.
    public int ord(String s) {
        return (int)s.charAt(0);
    }


    //The chr() function returns a character from the specified ASCII value.
    public char chr(int i){
        //return  Character.toString ((char) i);
        return (char) i;
    }


    private String sha1(String password)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes());
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return sha1;
    }

    private String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


    public static String md5(String input) {
        String result = input;
        if(input != null) {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } //or "SHA-1"
            md.update(input.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while(result.length() < 32) { //40 for SHA-1
                result = "0" + result;
            }
        }
        return result;
    }

}

