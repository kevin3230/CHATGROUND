package com.members.model;

import java.util.List;

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
	
	public void insert(MembersVO membersVO) {
		String[] saltyAndPW = hashPW(membersVO.getMemPw());//傳入輸入的密碼,取得sha-1雜湊後的密碼與salty
		membersVO.setMemSalt(saltyAndPW[0]);//VO的salty欄位設為salty
		membersVO.setMemPw(saltyAndPW[1]);//VO的PW欄位重設為hashPW
		MembersHibernateDAO mhDAO = new MembersHibernateDAO();
		mhDAO.insert(membersVO);//VO新增進資料庫
	}
	
	public void update(MembersVO membersVO) {
		MembersHibernateDAO mhDAO = new MembersHibernateDAO();
		mhDAO.update(membersVO);
	}
	
	public void delete(Integer memNo) {
		MembersHibernateDAO mhDAO = new MembersHibernateDAO();
		mhDAO.delete(memNo);
	}
	
	public MembersVO findByPrimaryKey(Integer memNo) {
		MembersHibernateDAO mhDAO = new MembersHibernateDAO();
		MembersVO membersVO = mhDAO.findByPrimaryKey(memNo);
		return membersVO;
	}
	
	public List<MembersVO> getAll() {
		MembersHibernateDAO mhDAO = new MembersHibernateDAO();
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
		
		
	}
	
}
