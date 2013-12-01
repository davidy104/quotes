package nz.co.yellow.spider.messaging.data;

/**
 * loading strategies for Spider messages will be applied during convention
 * between DTO and model
 * 
 * @author david
 * 
 */
public enum ThreadMsgLoadStrategies {
	LOAD_PARTICIPANT, LOAD_MESSAGE, LOAD_THREAD, ALL, NONE
}
