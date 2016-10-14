package jp.co.iccom.sato_marin.calculate_sales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		
		
		//3.2　Mapを使用
		//支店-合計金額
		HashMap<String, Long> branchrevenue = new HashMap <String, Long>(0);
		
		
		
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
				//3桁の数字
				if(!(bran[0].matches("\\d{3}"))){
					System.out.println("支店定義ファイルのフォーマットが不正です");
					return;
				}
				//配列が2個
				if( bran.length != 2){
					System.out.println("支店定義ファイルのフォーマットが不正です");
					return;
				}
				branch.put(bran[0], bran[1]);
				
				//売上-合計　初期化
				branchrevenue.put(bran[0], 0l);
			}
			
			//確認表示
			System.out.println(branchrevenue);

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
		
		
		//3.2　Mapを使用
		//商品-合計金額
		HashMap<String, Long> productrevenue = new HashMap <String, Long>();
		
		
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
				//英数字計8桁
				if(!commod[0] .matches("^[a-zA-Z0-9]{8}$")){
					System.out.println("1商店定義ファイルのフォーマットが不正です");
					return;
				}
				//配列が2個
				if( commod.length != 2){
					System.out.println("商店定義ファイルのフォーマットが不正です");
					return;
				}
				commodity.put(commod[0], commod[1]);
				
				//商品-合計　初期化
				productrevenue.put(commod[0], 0l);
			}
			
			//確認表示
			System.out.println(productrevenue);

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
		File[] files1= file3.listFiles();
		
		//List宣言 rcd
		List<File> rcd = new ArrayList<File>();
		
		
		//List宣言 rcdname
		List<String> rcdname = new ArrayList<String>();
		
		
		//取得した一覧を表示
		for(int i = 0; i < files1.length; i++){
			System.out.println("ファイル" + (i+1) + "→" + files1[i]);
		}
		
		
		//ファイル名から.rcdを厳選
		for(int i = 0; i < files1.length; i++){
			
			//getNameメソッドを使ってfiles1[]をString型に変更
			//→matchesでファイル名.rcdを厳選
			if(files1[i].getName().matches("\\d{8}"+".rcd")){
				System.out.println(files1[i]);
				
				//List:rcdに.rcdファイルを格納
				rcd.add(files1[i]);
				
				//List:rcdnameにファイル名の拡数字のみを格納
				rcdname.add(files1[i].getName().substring(0,8));
			}
		}
		
		
		//確認表示
		System.out.println(rcd);
		System.out.println(rcdname);
		
		
		//rcdnameを使わないと↓
		//rcd.get(rcd.size() -1).getName().substring(0,8)
		//ファイルが歯抜けになっているか（1からファイルが始まる時のみ稼動）
		//（例えば10からファイル名が始まる時にどうするのかも追加したい）
		if(rcdname.size() != Integer.valueOf(rcdname.get(rcdname.size()-1))){
			System.out.println("売上ファイル名が連番になっていません");
			return;
		}
		
		
		//3.2
		//各情報の取り出し　list
		
		String branchcode = null;//支店コード
		String productcode = null;//商品コード
		long revenue1 = 0;//各-合計金額
		long brevenue = 0;//支店-合計金額
		long prevenue = 0;//商品-合計金額
		
		for (int i = 0; i < rcd.size(); i++){
			System.out.println(rcd.get(i));
		}
		
		String r;
		BufferedReader rd = null;
		
		try{
			
			//表示のためのfor
			for (int i = 0; i < rcd.size(); i++){
				rd = new BufferedReader( new FileReader (rcd.get(i)));
				
				//1ファイルずつのリストの作成
				List <String> datafile = new ArrayList<String> ();
				
				//1行ずつリストに格納
				while((r = rd.readLine()) != null){
					datafile.add(r);
				}
				
				if(datafile.size() != 3){
					System.out.println("datafileのフォーマットが不正です");
				}
				
				//確認表示
				System.out.println(datafile);
				
				//各種データを各変数に格納
				branchcode = datafile.get(0);
				System.out.println(branchcode);//確認表示
				productcode = datafile.get(1);
				revenue1 = Long.parseLong(datafile.get(2));
				
				
				//上記データと1/2の定義ファイルのmapを比較、各項目がちゃんとあるか確認
				if (!(branch.containsKey(branchcode))){
					System.out.println("該当ファイル名の支店コードが不正です");
				}
				if(!(commodity.containsKey(productcode))){
					System.out.println("該当ファイル名の商品コードが不正です");
				}
				
				
				//合計を計算
				long brankari = branchrevenue.get(branchcode);
				brevenue = brankari + revenue1;
				
				long prekari = productrevenue.get(productcode);
				prevenue = prekari + revenue1;
				
				
				if((brevenue >= 9999999999.9) || (prevenue >= 9999999999.9)){
					System.out.println("合計金額が10桁を超えました");
					return;
				}
				
				//mapに入力
				branchrevenue.put (branchcode , brevenue);
				productrevenue.put (productcode , prevenue);
				
				
				//確認表示
				System.out.println(branchrevenue);
				System.out.println(productrevenue);
				
				
			}
			//内容がnullになったらストリームを閉じる
			rd.close();
		
		
		}catch(IOException e){
			//もし何かしらで動かなかった場合の対処
			System.out.print(e);
		}
		
		
		//4.出力
		//ファイルを作る
		File newfile = new File (args[0] + "branch.out");
		
		File newfile1 = new File (args[0] + "commodity.out");
		
		
		
		
		
		
	}
}
