package utility;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashCode {

	public static String getHashCode(String rawPW) {
		
		String hashPW = null;
		try {
			//取得SHA-1雜湊法的MessageDigest實例
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			//來源編碼utf-8轉成byte陣列傳入update計算hash值
			digest.update(rawPW.getBytes("utf8"));
			//digest()完成hash值計算,byte陣列轉成大整數後再轉成16進位變成字串
			hashPW = String.format("%040x",new BigInteger(1, digest.digest()));
//			System.out.println("digest: " + digest);
//			System.out.println("hashPW: " + hashPW);
			return hashPW;
			//catch無此演算法的參數錯誤
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("utility/PasswordHashCode/getHashCode():NoSuchAlgorithmException");
			//catch找不到此編碼的參數錯誤
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("utility/PasswordHashCode/getHashCode():UnsupportedEncodingException");
		} 
		return hashPW;
	}
	
	public static boolean verifyHashCode(String rawPW, String PW) {
		String hashPW = null;
		try {
			//取得SHA-1雜湊法的MessageDigest實例
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			//來源編碼utf-8轉成byte陣列傳入update計算hash值
			digest.update(rawPW.getBytes("utf8"));
			//digest()完成hash值計算,byte陣列轉成大整數後再轉成16進位變成字串
			hashPW = String.format("%040x",new BigInteger(1, digest.digest()));
//			System.out.println(hashPW);
//			System.out.println(PW);
			//與資料庫內的密碼欄位hash值比對是否相同,確認密碼是否正確
			if(hashPW.equals(PW)) {
				return true;
			} else {
				return false;
			}
			//catch無此演算法的參數錯誤
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("utility/PasswordHashCode/getHashCode():NoSuchAlgorithmException");
			//catch找不到此編碼的參數錯誤
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("utility/PasswordHashCode/getHashCode():UnsupportedEncodingException");
		} 
		return false;
	}
	
	
	
	
	
	//測試所有方法
	public static void main(String[] args) {
		String rawPW = "123";
		String salty = "%#*^kjas12";
		
		//測試getHashCode()
		String hashCode = getHashCode(rawPW + salty);
		System.out.println("測試getHashCode(): " + hashCode);
		
		//測試verifyHashCode()
		String rightPW = "123";
		String wrongPW = "321";
		System.out.println("測試verifyHashCode()true: " + verifyHashCode(rightPW + salty, hashCode));
		System.out.println("測試verifyHashCode()false: " + verifyHashCode(wrongPW + salty, hashCode));
		
	}

}
