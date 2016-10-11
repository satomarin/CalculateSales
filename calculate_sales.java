package jp.co.iccom.sato_marin.calculate_sales;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class calculate_sales {
	
	public static void main(String[]args) {
		//(例外がありえるからtry必要
		
		//1.1：2.1 表示
		
		//1.2　Mapを使用
		HashMap<String, String> map = new HashMap <String, String>();
		
		try{
			//ファイルを開く(支店定義）(エスケープシーケンス)(txtファイルじゃないためtxtいらない)
			//文字列の受け取り
			BufferedReader bh = new BufferedReader(new FileReader (args[0] + "\\branch.lst"));
			//BufferedReader cd = new BufferedReader(new FileReader (args[0] + "\\commodity.lst"));
			
			String b;
			//String c;
			
			//内容がnullじゃなかったら保持して表示
			while((b = bh.readLine()) != null){
				String[] bran = b.split(",", 0);
				map.put("支店コード", bran[0]);
				map.put("支店名",bran[1]);
				System.out.println(map.entrySet());
				
				
			}
			//while((c = cd.readLine()) != null){
				//String[] commod = c.split(",", 0);
				//map.put("商品コード", commod[0]);
				//map.put("商品名",commod[1]);
				//System.out.println(map.entrySet());
			//}
			
			
			//内容がnullになったらストリームを閉じる
			bh.close();
			
			//ファイルが開けない時
			//System.out.println("支店定義ファイルのフォーマットが不正です");
			
		}catch(IOException e){
			//もし何かしらで動かなかった場合の対処
			System.out.print(e);
		}
		
		System.out.println(map);
    	
	}
}
		