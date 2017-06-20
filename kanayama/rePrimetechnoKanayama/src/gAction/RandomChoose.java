package gAction;

import java.util.Random;

public class RandomChoose {
	//乱数を返すメソッド
	public int randomNum(){
		Random random=new Random();
		int choose=random.nextInt(100);
		int keyNum=0;

		//確率1％で0、確率3％で1を返す
		if(choose%100==0){
			keyNum=0;
		}else if(choose%100>=1 && choose%100<=3){
			keyNum=1;
		}else{
		//確率12％で2から9を返す
		for(int i=2;i<=9;i++){
			if(choose%100>=12*(i-2)+4 && choose%100<=12*(i-1)+3){
				keyNum=i;
				}
			}

		}
		return keyNum;
	}
}
