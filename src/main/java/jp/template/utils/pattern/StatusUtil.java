package jp.template.utils.pattern;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * JSTL の varStatus のような実装。 
 * 
 * Jdk1.8 関数型インタフェースの利用。
 * @see <a href="https://docs.oracle.com/javase/jp/8/docs/api/java/util/function/Consumer.html">インタフェースConsumer </a>
 * 
 * @author hosomi.
 */
public class StatusUtil {

	public class Status<T> {
		
		/** status 管理する対象のオブジェクト*/
		private T current;
		
		/** 位置(0 から)*/
		private int index;
		
		/** 現在が最終か*/
		private boolean isLast;

		/**
		 * 状態を更新する。
		 * 
		 * @param current 現在の位置のオブジェクト
		 * @param index 現在の位置
		 * @param isLast 最終行判定
		 * @return 現在のステータスを管理したオブジェクト
		 */
		private Status<T> update(T current, int index, boolean isLast) {
			this.current = current;
			this.index = index;
			this.isLast = isLast;
			return this;
		}

		public T getCurrent() {
			return current;
		}

		public int getIndex() {
			return index;
		}

		public int getCount() {
			return index + 1;
		}

		public boolean isFirst() {
			return index == 0;
		}

		public boolean isLast() {
			return isLast;
		}
	}

	/**
	 * 状態を管理しつつループ処理する。
	 * 
	 * @param itr List,配列などの要素
	 * @param action ループ中に処理する具体的な処理
	 * @param <T> ループの中のオブジェクトを示す ジェネリクス型。
	 */
	public <T> void forEach(Iterable<T> itr, Consumer<Status<T>> action) {
		Status<T> sts = new Status<T>();
		int index = 0;
		Iterator<T> it = itr.iterator();
		while (it.hasNext()) {
			action.accept(sts.update(it.next(), index++, !it.hasNext()));
		}
	}
}
