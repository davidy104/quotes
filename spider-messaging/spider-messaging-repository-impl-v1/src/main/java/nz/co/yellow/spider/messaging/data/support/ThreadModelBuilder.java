package nz.co.yellow.spider.messaging.data.support;

import java.util.Date;

import nz.co.yellow.spider.messaging.data.MessageModel;
import nz.co.yellow.spider.messaging.data.ParticipantModel;
import nz.co.yellow.spider.messaging.data.ThreadModel;
import nz.co.yellow.spider.messaging.data.ThreadParticipantModel;

import org.springframework.stereotype.Component;

@Component
public class ThreadModelBuilder extends EntityBuilder<ThreadModel> {

	@Override
	void initProduct() {
		this.product = new ThreadModel();
	}

	public ThreadModelBuilder create(String title, Date createdTime,
			String status) {
		this.product = ThreadModel.getBuilder(title, createdTime, status)
				.build();
		return this;
	}

	public ThreadModelBuilder addMessages(MessageModel... messages) {
		for (MessageModel message : messages) {
			message.setThread(product);
			this.product.addMessage(message);
		}
		return this;
	}

	public ThreadModelBuilder addSpiderMsgParticipant(
			ParticipantModel... participants) {
		ThreadParticipantModel threadParticipant = null;

		for (ParticipantModel participant : participants) {
			threadParticipant = ThreadParticipantModel.getBuilder(product,
					participant).build();
			participant.addThreadParticipant(threadParticipant);
			product.addThreadParticipantModel(threadParticipant);
		}
		return this;
	}

	@Override
	ThreadModel assembleProduct() {
		return product;
	}

}
