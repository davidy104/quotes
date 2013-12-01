package nz.co.yellow.spider.messaging.data.predicate;

import nz.co.yellow.spider.messaging.data.QThreadModel;

import com.mysema.query.types.Predicate;

public class ThreadPredicates {

	public static Predicate findByTitle(final String title) {
		QThreadModel thread = QThreadModel.threadModel;
		return thread.title.eq(title);
	}

}
