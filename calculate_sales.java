package jp.co.iccom.sato_marin.calculate_sales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
public class calculate_sales {

	public static void main(String[]args) {

		//支店定義ファイルがあるかの確認
		File file = new File(args[0]+ "\\branch.lst");
		if (!( file.exists())){
			System.out.println("支店定義ファイルが存在しません。");
			return;
		}

		//(例外がありえるからtry必要
		//1.2　Mapを使用
		HashMap<String, String> branch = new HashMap <String, String>();


		//1.1　表示
		try{
			//ファイルを開く(支店定義）(エスケープシーケンス)(txtファイルじゃないためtxtいらない)
			//文字列の受け取り
			BufferedReader bh = new BufferedReader(new FileReader (args[0] + "\\branch.lst"));

			String b;

			//内容がnullじゃなかったら1行ずつ格納　カンマでキーとかを分ける
			//ファイルのフォーマットが不正かの確認
			while((b = bh.readLine()) != null){
				String[] bran = b.split(",", 0);
				if(!(bran[0].matches("\\d{3}"))){
					System.out.println("支店定義ファイルのフォーマットが不正です");
					return;
				}
				if( bran.length != 2){
					System.out.println("支店定義ファイルのフォーマットが不正です");
					return;
				}
				branch.put(bran[0], bran[1]);
			}

			//内容がnullになったらストリームを閉じる　必ず必要
			bh.close();

		}catch(IOException e){
			//もし何かしらで動かなかった場合の対処
			System.out.print(e);
		}

		System.out.println(branch);




		//商品定義ファイルがあるかの確認
		File file1 = new File(args[0]+ "\\commodity.lst");
		if (!file1.exists()){
			System.out.println("商品定義ファイルが存在しません。");
			return;
		}

		//2.2　Mapを使用
		HashMap<String, String>commodity = new HashMap <String, String>();

		//2.1　表示
		try{
			//ファイルを開く(支店定義）(エスケープシーケンス)(txtファイルじゃないためtxtいらない)
			//文字列の受け取り
			BufferedReader cd = new BufferedReader(new FileReader (args[0] + "\\commodity.lst"));

			String c;

			//内容がnullじゃなかったら1行ずつ格納　カンマでキーとかを分ける
			//ファイルのフォーマットが不正かの確認
			while((c = cd.readLine()) != null){
				String[] commod = c.split(",", 0);
				//
				if(!commod[0] .matches("^[a-zA-Z0-9]{8}$")){
					System.out.println("1商店定義ファイルのフォーマットが不正です");
					return;
				}
				if( commod.length != 2){
					System.out.println("商店定義ファイルのフォーマットが不正です");
					return;
				}
				commodity.put(commod[0], commod[1]);
			}

			//内容がnullになったらストリームを閉じる
			cd.close();

		}catch(IOException e){
			//もし何かしらで動かなかった場合の対処
			System.out.print(e);
		}

		System.out.println(commodity);
		
		
		//3.1
		//ファイル名の取得
		File file3 = new File(args[0]);
		File files1[] = file3.listFiles();
		
		//取得した一覧を表示
		for(int i = 0; i < files1.length; i++){
			System.out.println("ファイル" + (i+1) + "→" + files1[i]);
			
		}
		
		
		
		System.out.println(files1[3]);
		
		
		
		
		
		
		
		
		
		
		
	}
}

