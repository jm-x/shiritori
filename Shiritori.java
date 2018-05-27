// Shiritori.java を書く
import java.util.Scanner;

public class Shiritori{
	public static void main(String[] args){
		Scanner stdIn=new Scanner(System.in);

		int ret=SearchWord.read("worddict.txt");
		if(ret==1)System.exit(1);
		
		boolean level=true;
		System.out.println("4文字以上しりとりです。");
		System.out.print("難易度高めでよいですか？(y/n):");String ans=stdIn.next();
		if(ans.equals("n")||ans.equals("N"))level=false;
		
		System.out.println("あなたが先行です。好きな単語を\u001b[01;31m平仮名\u001b[00;mで入力してください");

		String nowWord=null,preWord=null;
		//1回目の処理だけ特別
		boolean fflag;//ループ抜けれるフラグ
        do{ 
			fflag=true;
			int tmp=0;
        	System.out.print("□");
			String word=stdIn.next();
            try{
            	checkWord.checkLength(word);
				tmp=1;
                checkWord.checkLast(word);
                checkWord.checkExist(word);
                checkWord.checkForego(word);//更新処理が含まれるため最後に実行
				nowWord=word;
            }catch(LoseWordError e){
                System.out.println("単語の最後が'ん'です。あなたの負けです");
                System.exit(1);
            }catch(NotValidWordError e){
				if(tmp==0){
					System.out.println("4文字未満の単語が入力されました。他の単語を入力してください。");
				}
                fflag=false;     
            }catch(NoKeywordError e){ 
                System.out.println("単語リストに存在しない単語です。他の単語を入手してください。");
                fflag=false;
            }         
        }while(!fflag);

		String[] fcomStr;
		if(level){
			fcomStr=SearchWord.isSuperSearch(nowWord.substring(nowWord.length()-1));
		}else{
			fcomStr=SearchWord.isSearch(nowWord.substring(nowWord.length()-1));
		}
        if(fcomStr!=null){
			try{
				System.out.println("■"+fcomStr[1].substring(0,fcomStr[1].length()-1)+"\u001b[01;31m"+fcomStr[1].substring(fcomStr[1].length()-1,fcomStr[1].length())+"\u001b[00m ("+fcomStr[0]+")");
				checkWord.checkLast(fcomStr[1]);
			}catch(LoseWordError e){
				System.out.println("単語の最後が'ん'でした...。あなたの勝ちです。");
				System.exit(0);
			}
            preWord=nowWord;
            nowWord=fcomStr[1];
        }else{
			System.out.println("単語を見つけることができませんでした...。");
            System.out.println("あなたの勝ちです。");
            System.exit(0);
        }



		while(true){
			boolean flag;//ループ抜けれるフラグ
			do{
				flag=true;	
				int tmp=0;
				System.out.print("□");
				String word=stdIn.next();
				try{
					checkWord.checkLength(word);//notvalid
					tmp=1;
					checkWord.checkLast(word);
					tmp=2;
					checkWord.checkConnect(word,nowWord);
					checkWord.checkExist(word);
					checkWord.checkForego(word);//更新処理が含まれるため最後に実行//notvalid
					nowWord=word;
				}catch(LoseWordError e){
					if(tmp==1){
						System.out.println("単語の最後が'ん'です。あなたの負けです。");
					}else{
						System.out.println("前の単語とつながっていません。あなたのまけです。");
					}
					System.exit(0);
				}catch(NotValidWordError e){
					if(tmp==0){
						System.out.println("４文字未満の単語が入力されました。他の単語を入力してください。");
					}else{
						System.out.println("既出の単語が入力されました。他の単語を入力してください。");
						//SearchWord.doNotUsed(word);
					}
					flag=false;	
				}catch(NoKeywordError e){
					System.out.println("単語リストに存在しない単語です。他の単語を入手してください。");
					flag=false;
				}					
			}while(!flag);
			
			String[] comStr;
			if(level){
				comStr=SearchWord.isSuperSearch(nowWord.substring(nowWord.length()-1));
			}else{
				comStr=SearchWord.isSearch(nowWord.substring(nowWord.length()-1));
			}
			if(comStr!=null){
				try{
					System.out.println("■"+comStr[1].substring(0,comStr[1].length()-1)+"\u001b[00;31m"+comStr[1].substring(comStr[1].length()-1,comStr[1].length())+"\u001b[00m ("+comStr[0]+")");
                //	System.out.println("■"+comStr.substring(0,comStr.length()-1)+"\u001b[00;31m"+comStr.substring(comStr.length()-1,comStr.lengt    h())+"\u001b[00m");
					checkWord.checkLast(comStr[1]);
              	}catch(LoseWordError e){
                  	System.out.println("単語の最後が'ん'でした...。あなたの勝ちです。");
                  	System.exit(0);
              	}
				preWord=nowWord;
				nowWord=comStr[1];			
			}else{
				System.out.println("単語を見つけることができませんでした...。");
				System.out.println("あなたの勝ちです。");
				System.exit(0);
			}
		}
	}
}
