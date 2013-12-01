package nz.co.yellow.spider.messaging.ds.converter;

import javax.annotation.Resource;

import nz.co.yellow.spider.messaging.data.MessageModel;
import nz.co.yellow.spider.messaging.data.MessageReq;
import nz.co.yellow.spider.messaging.data.MessageResp;
import nz.co.yellow.spider.messaging.data.Participant;
import nz.co.yellow.spider.messaging.ds.APIConverter;
import nz.co.yellow.spider.messaging.ds.ConvertException;
import nz.co.yellow.spider.messaging.ds.GeneralConverter.ServiceOperation;
import nz.co.yellow.spider.messaging.util.MessageUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("messageConverter")
public class MessageConverter implements
		APIConverter<MessageReq, MessageModel, MessageResp> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageConverter.class);

	@Resource
	private ParticipantConverter participantConverter;

	@Override
	public MessageResp convertToResponse(MessageModel model,
			ServiceOperation operation, Object... additionalSourceObj)
			throws ConvertException {
		LOGGER.debug("start to covert to dto:{}", model.getMessageId());
//		ThreadMsgLoadStrategies loadStrategies = ThreadMsgLoadStrategies.NONE;
		MessageResp msgDto = MessageResp.getBuilder(model.getContent(),
				model.getStatus()).build();
		msgDto.setMessageId(model.getMessageId());
		if (model.getSentTime() != null) {
			msgDto.setSentTime(MessageUtils.dateToStr(model.getSentTime()));
		}

		if (model.getReadTime() != null) {
			msgDto.setReadTime(MessageUtils.dateToStr(model.getReadTime()));
		}

		Participant participant = participantConverter.convertTo(
				model.getCreatedBy(), operation);
		msgDto.setCreatorUserId(participant.getUserId());

//		if (additionalSourceObj != null && additionalSourceObj.length > 0) {
//			loadStrategies = (ThreadMsgLoadStrategies) additionalSourceObj[0];
//		}

		// if (loadStrategies == ThreadMsgLoadStrategies.ALL
		// || loadStrategies == ThreadMsgLoadStrategies.LOAD_PARTICIPANT) {
		// if (model.getCreatedBy() != null) {
		//
		// }
		// }

		LOGGER.debug("after convert:{}", msgDto);
		return msgDto;
	}

	@Override
	public MessageModel convertFromRequest(MessageReq request,
			ServiceOperation operation, Object... additionalSourceObj)
			throws ConvertException {
		LOGGER.debug("start to covert to model:{}", request);
		MessageModel model = null;
		try {
			model = MessageModel.getBuilder(request.getContent(),
					request.getStatus()).build();

			if (request.getSentTime() != null) {
				model.setSentTime(MessageUtils.strToDate(request.getSentTime()));
			}

			if (request.getReadTime() != null) {
				model.setReadTime(MessageUtils.strToDate(request.getReadTime()));
			}

		} catch (Exception e) {
			throw new ConvertException("Message convert to model error", e);
		}

		LOGGER.debug("after convert:{}", model);
		return model;
	}

}
