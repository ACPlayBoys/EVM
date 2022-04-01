import java.io.*;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.*;
public class security {

    private static final char[] PASSWORD = "enfldsgbnlsngdlksdsgm".toCharArray();
    private static final byte[] SALT = {(byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,(byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,};
    
    static BufferedReader br;
    static BufferedWriter bw;
    static File dir=new File(System.getProperty("user.home")+"/AppData/Roaming/evm/");
        
            
            
            
          
    protected static void changePassword(String inp)
    {
        try
        {
            if(dir.exists()==false)dir.mkdir();   
            new File(dir+"/configuration.ac").createNewFile();  
            bw=new BufferedWriter(new FileWriter(dir+"/configuration.ac"));
            String tmp=encrypt(inp);
            bw.write(tmp);
            bw.close();
        }
        catch(Exception e){e.printStackTrace();}
    }
    public static String getPassword()
    {
        String pass="";
        try
        {
            if(dir.exists()==false)dir.mkdir();
            new File(dir+"/configuration.ac").createNewFile();
            br=new BufferedReader(new FileReader(dir+"/configuration.ac")); 
            bw=new BufferedWriter(new FileWriter(dir+"/configuration.ac"));
            String txt="";
            if((txt=br.readLine())==null)
            {
                new File(dir+"/configuration.ac").createNewFile();
                bw.write(encrypt("JINO"));
                bw.close();
            }      
            br=new BufferedReader(new FileReader(dir+"/configuration.ac")); 
            String text=br.readLine();
            pass=decrypt(text);
            br.close();
        }
        catch(Exception e){e.printStackTrace();}
        return pass;
    }

    private static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException 
    {        
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
    }

    private static String base64Encode(byte[] bytes) 
    {
        // NB: This class is internal, and you probably should use another impl
        return new BASE64Encoder().encode(bytes);
    }

    private static String decrypt(String property) throws GeneralSecurityException, IOException 
    {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
    }

    private static byte[] base64Decode(String property) throws IOException 
    {
        // NB: This class is internal, and you probably should use another impl
        return new BASE64Decoder().decodeBuffer(property);
    }

}