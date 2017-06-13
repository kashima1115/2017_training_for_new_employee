package gAction;

import java.util.HashMap;
import java.util.Map;

public class GItemList {
	//ガチャで出る景品リストをmapにセットし、乱数の値を基に景品を取得する
	public String selectedItem(Integer selected){
		Map<Integer,String> itemMap=new HashMap<Integer,String>();

		itemMap.put(0, "★6 始祖リリン");
		itemMap.put(1,"★5 カゲロウ：ホムラ");
		itemMap.put(2, "★4 聖王アーサー");
		itemMap.put(3, "★4 堕王エビルアーサー");
		itemMap.put(4, "★4 流水竜リヴィア");
		itemMap.put(5, "★4 精参謀長ヴィヴィアン");
		itemMap.put(6, "★4 聖常王クロウリー");
		itemMap.put(7, "★4 創醒の聖者");
		itemMap.put(8,"★4 永久竜カナン");
		itemMap.put(9, "★4 神才マクスウェル");

		String item=itemMap.get(selected);

		return item;
	}

	//ガチャで出る景品の画像のパスをmapにセットし、乱数の値を基に画像のパスを取得する
	public String selectedImage(Integer selected){

		Map<Integer,String> imageMap=new HashMap<Integer,String>();

		imageMap.put(0, "/gImage/ririn.jpg");
		imageMap.put(1, "/gImage/kagerou.jpg");
		imageMap.put(2, "/gImage/arthur.jpg");
		imageMap.put(3, "/gImage/evilarthur.jpg");
		imageMap.put(4,"/gImage/ribia.jpg" );
		imageMap.put(5, "/gImage/vivian.jpg");
		imageMap.put(6, "/gImage/kurouri.jpg");
		imageMap.put(7, "/gImage/creator.jpg");
		imageMap.put(8, "/gImage/kanan.jpg");
		imageMap.put(9, "/gImage/makusu.jpg");

		String image=imageMap.get(selected);

		return image;
	}
}
