//NoKeywordError例外クラス

class NoKeywordError extends Exception{
	NoKeywordError(String word){
		super("辞書にない単語"+word+"が入力されました。");
	}
}
