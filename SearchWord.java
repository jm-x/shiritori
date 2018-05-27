import java.util.Scanner;
import java.io.*;

class SearchWord{
	static String[][] wList=new String[199844][2];	//[i][0]に漢字、[i][1]によみ
	static boolean[] wUse=new boolean[199844]; //falseで初期化されている
	static int read(String filename){
		try{
			Scanner scanner=new Scanner(new File(filename));
			int cnt=0;
			while(scanner.hasNextLine()){
				String line=scanner.nextLine();
				String[] tmp=line.split("[\\s]+");
				wList[cnt][0]=tmp[0];
				wList[cnt][1]=tmp[1];
				cnt++;
			}
			return 0;
		}catch(FileNotFoundException e){
			System.out.println("ファイルが存在しませんでした。");
			return 1;
		}
		
	}

	static int isExist(String word,int start){
		int ind=-1;
		for(int i=start;i<199844;i++){
			if(wList[i][1].equals(word)){
                      ind=i;
                      break;
            }
		}
		return ind;
		
	}
	
	static int isExist(String word){
		return isExist(word,0);
	}
	
	

//checkするとともに更新//isExist使用済
	static boolean isUsed(String word){
		 if(wUse[isExist(word)]){
			return true;
		}else{
			wUse[isExist(word)]=true;
			return false;
		}				
	}

//isUsed取り消し
	static void doNotUsed(String word){
		wUse[isExist(word)]=false;
	}

//特定の文字からはじまる単語を探す 見つかったらその単語 を返す else return null
	static String[] isSearch(String c){
		String[] ret=null;
		for(int i=0;i<199844;i++){
			if(wList[i][1].substring(0,1).equals(c)&&(!wUse[i])){
				wUse[i]=true;
				ret=new String[2];
				ret[0]=wList[i][0];//漢字
				ret[1]=wList[i][1];//よみ
				//ret=wList[i][1];
				break;
			}
		}
		return ret;		
	}

	static String[] isSuperSearch(String c){
		String[] ret=null;
		for(int i=0;i<199844;i++){
			if(wList[i][1].substring(0,1).equals(c)&&(!wUse[i])&&(!(wList[i][1].substring(wList[i][1].length()-1).equals("ん")))){
            	wUse[i]=true;
                ret=new String[2];
                ret[0]=wList[i][0];//漢字
                ret[1]=wList[i][1];//よみ
                break;
            }
        }
        return ret;
      }
			
}
