//NotValidWordError例外クラス

class NotValidWordError extends Exception{
	NotValidWordError(String word){
		super("不適切な単語"+word+"が入力されました。\n4文字以上で未だ使われていない単語が有効です。");		
	}
}
