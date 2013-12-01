package nz.co.yellow.spider.messaging.data.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Abstract Base repository, support basic operation and queryDsl as well.
 *
 * @author david
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends
		JpaRepository<T, ID>, QueryDslPredicateExecutor<T> {

}
