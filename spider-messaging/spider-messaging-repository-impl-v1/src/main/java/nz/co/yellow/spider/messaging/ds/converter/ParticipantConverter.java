package nz.co.yellow.spider.messaging.ds.converter;

import javax.annotation.Resource;

import nz.co.yellow.spider.messaging.data.Participant;
import nz.co.yellow.spider.messaging.data.ParticipantModel;
import nz.co.yellow.spider.messaging.ds.ConvertException;
import nz.co.yellow.spider.messaging.ds.GeneralConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("participantConverter")
public class ParticipantConverter implements
		GeneralConverter<ParticipantModel, Participant> {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ThreadConverter.class);

	@Resource
	private MessageConverter messageConverter;

	@Override
	public ParticipantModel convertFrom(Participant participantDto,
			ServiceOperation operation, Object... additionalSourceObj)
			throws ConvertException {
		LOGGER.debug("start to convert to Model,participantDto:{}",
				participantDto);
		ParticipantModel model = ParticipantModel.getBuilder(
				participantDto.getUserId()).build();
		if (operation != ServiceOperation.CREATE) {
			model.setParticipantId(participantDto.getParticipantId());
		}
		LOGGER.debug("after convert, model:{}", model);
		return model;
	}

	@Override
	public Participant convertTo(
			ParticipantModel participantModel,
			nz.co.yellow.spider.messaging.ds.GeneralConverter.ServiceOperation operation,
			Object... additionalSourceObj) throws ConvertException {
		LOGGER.debug("start to covert SpiderMessageThreadParticipant:{}",
				participantModel.getParticipantId());
		// ThreadMsgLoadStrategies loadStrategies =
		// ThreadMsgLoadStrategies.NONE;
		//
		// if (additionalSourceObj != null && additionalSourceObj.length > 0) {
		// loadStrategies = (ThreadMsgLoadStrategies) additionalSourceObj[0];
		// }

		Participant participantDto = Participant.getBuilder(
				participantModel.getUserId()).build();
		participantDto.setParticipantId(participantModel.getParticipantId());

//		if (loadStrategies == ThreadMsgLoadStrategies.LOAD_MESSAGE
//				|| loadStrategies == ThreadMsgLoadStrategies.ALL) {
//			List<MessageModel> msgModelList = participantModel.getMessages();
//			List<Message> messagesDtos = null;
//			if (msgModelList != null && msgModelList.size() > 0) {
//				messagesDtos = new ArrayList<Message>();
//				for (MessageModel msgModel : msgModelList) {
//					Message msgDto = this.messageConverter.convertTo(msgModel,
//							null);
//					messagesDtos.add(msgDto);
//				}
//			}
//			participantDto.setMessages(messagesDtos);
//		}
		LOGGER.debug("after covert, participantDto:{}", participantDto);
		return participantDto;
	}

}
