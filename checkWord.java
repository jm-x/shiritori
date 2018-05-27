//単語チェッククラス
//4文字未満、既出の単語、最終文字(ん)、最初の単語と後の単語がつながっているかの４つ判定
//上の条件によりそれぞれの例外クラスをthrow
class checkWord{

//1 単語の長さが４以上かどうかチェック
	static int checkLength(String word)throws NotValidWordError{
		if(word.length()<4){
			throw new NotValidWordError(word);
		}else{
			return 0;
		}
	}


//2 単語の最後が"ん"かどうかチェック
	static int checkLast(String word)throws LoseWordError{
		if(word.substring(word.length()-1).equals("ん")){
			throw new LoseWordError();
		}else{
			return 0;
		}
	}

//3 つながっているかチェック 
	static void checkConnect(String word,String preWord)throws LoseWordError{
		if(!(word.substring(0,1).equals(preWord.substring(preWord.length()-1))))throw new LoseWordError();
	}


//辞書にある単語かチェック
	static void checkExist(String word)throws NoKeywordError{
		int tmp=SearchWord.isExist(word);
		if(tmp==-1)throw new NoKeywordError(word);
	}

//既出の単語かどうか
	static void checkForego(String word)throws NotValidWordError{
		if(SearchWord.isUsed(word))throw new NotValidWordError(word);
	}


	
}
