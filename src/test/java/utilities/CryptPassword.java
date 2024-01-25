package utilities;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.util.Base64;
import java.util.Properties;

public class CryptPassword {
    private static final String ALGORITHM = "AES";
    private static final String ENCRYPTION_KEY_PATH = "C:\\Users\\asus\\IdeaProjects\\trendyol.properties";
    private static final String CONFIG_FILE_PATH = "C:\\Users\\asus\\IdeaProjects\\com.udemyTestNG\\configuration.properties";
    private static String ENCRYPTION_KEY;
    static Properties configProperties = new Properties();

    public static String decrypted() throws Exception {
        Properties keyProperties = new Properties();
        FileInputStream keyInputStream = new FileInputStream(ENCRYPTION_KEY_PATH);
        keyProperties.load(keyInputStream);
        String encryptionKey = keyProperties.getProperty("encryption_key");
       // System.out.println("encrptkey : "+encryptionKey);
        if (encryptionKey != null) {
            setEncryptionKey(encryptionKey);
        } else {
            throw new Exception("Encryption key is null");
        }


        String realPassword = keyProperties.getProperty("passwordReal");
       // System.out.println("gerçek şifre yazmalı:"+realPassword);
        keyInputStream.close();

        String encryptedPassword = encrypt(realPassword);
       // System.out.println("encrytpten sona :"+encryptedPassword);
        FileOutputStream outputStream = new FileOutputStream(CONFIG_FILE_PATH);
        Properties properties = new Properties();
        properties.setProperty("password", encryptedPassword);
        properties.store(outputStream, null);
       // System.out.println("encrytpten sona :"+encryptedPassword);
        outputStream.close();
        properties = new Properties();
        FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH);
        properties.load(inputStream);
        inputStream.close();

        encryptedPassword = properties.getProperty("password");

        return decrypt(encryptedPassword);
    }
    public static String encrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public static String decrypt(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedValue = cipher.doFinal(Base64.getDecoder().decode(encryptedValue));
        return new String(decryptedValue);
    }

    private static Key generateKey() throws Exception {
        return new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ALGORITHM);
    }
    public static void setEncryptionKey(String key) {
        ENCRYPTION_KEY = key;
    }
}
