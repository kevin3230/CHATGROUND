package com.members.model;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MembersService {
	//specialCharacter有28個字元
	private static final String[] specialCharacter = {"`", "~", "!", "@", "#", "$", "%", "^", "&", "*"
										,"(", ")", "-", "_", "+", "=", "[", "]", "\\", "'"
										, "\"", "/", ".", "<", ">", "?", ";", ":"};
	//numbers有10個字元
	private static final String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	//letters有52個字元
	private static final String[] letters = {"A", "a", "B", "b", "C", "c", "D", "d", "E", "e", "F", "f"
								, "G", "g", "H", "h", "I", "i", "J", "j", "K", "k", "L", "l"
								, "M", "m", "N", "n", "O", "o", "P", "p", "Q", "q", "R", "r", "S", "s"
								, "T", "t", "U", "u", "V", "v", "W", "w", "X", "x", "Y", "y", "Z", "z"};
	
	public boolean insert(MembersVO membersVO) {
		String[] saltyAndPW = hashPW(membersVO.getMemPw());//傳入輸入的密碼,取得sha-1雜湊後的密碼與salty
		membersVO.setMemSalt(saltyAndPW[0]);//VO的salty欄位設為salty
		membersVO.setMemPw(saltyAndPW[1]);//VO的PW欄位重設為hashPW
		//取得ApplicationContext實體
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		//建立DAO物件
		MembersDAO_interface mhDAO = (MembersDAO_interface)context.getBean("membersDAO");
		if(mhDAO.findByMemAcc(membersVO.getMemAcc()).isEmpty()) {//如果資料庫內帳號沒有重複才新增會員
			mhDAO.insert(membersVO);//VO新增進資料庫
			return true;
		}else {
			return false;
		}
	}
	
	public void update(MembersVO membersVO) {
		//取得ApplicationContext實體
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		//建立DAO物件
		MembersDAO_interface mhDAO = (MembersDAO_interface)context.getBean("membersDAO");
		mhDAO.update(membersVO);
	}
	
	public void delete(Integer memNo) {
		//取得ApplicationContext實體
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		//建立DAO物件
		MembersDAO_interface mhDAO = (MembersDAO_interface)context.getBean("membersDAO");
		mhDAO.delete(memNo);
	}
	
	public MembersVO findByPrimaryKey(Integer memNo) {
		//取得ApplicationContext實體
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		//建立DAO物件
		MembersDAO_interface mhDAO = (MembersDAO_interface)context.getBean("membersDAO");
		MembersVO membersVO = mhDAO.findByPrimaryKey(memNo);
		return membersVO;
	}
	
	public List<MembersVO> findByMemAcc(String memAcc) {
		//取得ApplicationContext實體
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		//建立DAO物件
		MembersDAO_interface mhDAO = (MembersDAO_interface)context.getBean("membersDAO");
		List<MembersVO> list = mhDAO.findByMemAcc(memAcc);
		return list;
	}
	
	public List<MembersVO> getAll() {
		//取得ApplicationContext實體
		ApplicationContext context = new ClassPathXmlApplicationContext("beans-config.xml");
		//建立DAO物件
		MembersDAO_interface mhDAO = (MembersDAO_interface)context.getBean("membersDAO");
		return mhDAO.getAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//傳入密碼,加鹽後作sha-1雜湊,並將salty與hashCode存入String[] saltyAndPW,回傳saltyAndPW
	public static String[] hashPW(String rawPW) {
		//saltyAndPW[0]是salty, saltyAndPW[1]是hashPW
		String[] saltyAndPW = new String[2];
		String hashPW = null;
		StringBuilder salty = new StringBuilder("");
		//salty有5個字元
		for(int i = 1; i < 21; i++) {
			//5面骰,特殊字元權重3/5,數字權重1/5,字母權重1/5
			int dice = (int)Math.floor(Math.random()*5);
			if(dice <= 2) {
				String letter = specialCharacter[(int)Math.floor(Math.random()*28)];
				salty.append(letter);
			}else if(dice == 3) {
				String letter = numbers[(int)Math.floor(Math.random()*10)];
				salty.append(letter);
			}else if(dice == 4) {
				String letter = letters[(int)Math.floor(Math.random()*52)];
				salty.append(letter);
			}
		}
		saltyAndPW[0] = salty.toString();
		hashPW = utility.PasswordHashCode.getHashCode(rawPW + salty);
		saltyAndPW[1] = hashPW;
//		System.out.println("saltyAndPW[0]:" + saltyAndPW[0]);
//		System.out.println("saltyAndPW[1]:" + saltyAndPW[1]);
		
		return saltyAndPW;
	}
	
	//傳入membersVO與嘗試的密碼,回傳正確與否
	public static boolean verifyPW(MembersVO membersVO, String attemptPW) {
		boolean result = false;
		String PW = membersVO.getMemPw();//從資料庫取得hash過的密碼
		String rawPW = attemptPW + membersVO.getMemSalt();//取得加鹽後的嘗試的密碼
		result = utility.PasswordHashCode.verifyHashCode(rawPW, PW);//傳入加鹽後的嘗試的密碼與資料庫hash過的密碼,result儲存正確與否的布林值
		if(result == true) {
			return true;
		}else {
			return false;
		}
	}
	
	//測試AOP
//	public void test(MembersVO membersVO) {
//		System.out.println("test method");
//		System.out.println(membersVO.getMemAcc());
//	}
	
	public static void main(String[] args) {
		
		//測試hashPW(String rawPW)
//		for(String str: hashPW("")) {
//			System.out.println(str);
//		}
		
		//測試verifyPW(MembersVO membersVO, String attemptPW)
//		MembersVO membersVO = new MembersVO();
//		String setPW = "1";//原始輸入的密碼
//		String attemptPW = "1";//訪客嘗試的密碼
//		String[] saltyAndPW = hashPW(setPW);
//		membersVO.setMemSalt(saltyAndPW[0]);
//		membersVO.setMemPw(saltyAndPW[1]);
//		System.out.println(verifyPW(membersVO, attemptPW));
		
		//測試getAll()
//		MembersService memSVC = new MembersService();
//		List<MembersVO> list = memSVC.getAll();
//		for(MembersVO amembersVO : list) {
//			System.out.println(amembersVO.getMemAcc());
//			System.out.println(amembersVO.getMemPw());
//			System.out.println(amembersVO.getMemEmail());
//			System.out.println(amembersVO.getMemRegdate());
//		}
		
	}
	
}
