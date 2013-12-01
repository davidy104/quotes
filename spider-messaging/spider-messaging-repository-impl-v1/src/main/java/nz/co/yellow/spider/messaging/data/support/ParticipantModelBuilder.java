package nz.co.yellow.spider.messaging.data.support;

import nz.co.yellow.spider.messaging.data.MessageModel;
import nz.co.yellow.spider.messaging.data.ParticipantModel;

public class ParticipantModelBuilder extends EntityBuilder<ParticipantModel> {

	@Override
	void initProduct() {
		this.product = new ParticipantModel();
	}

	public ParticipantModelBuilder create(String userId) {
		this.product = ParticipantModel.getBuilder(userId).build();
		return this;
	}

	public ParticipantModelBuilder addMessages(MessageModel... messages) {
		for (MessageModel message : messages) {
			message.setCreatedBy(product);
			this.product.addMessage(message);
		}
		return this;
	}

	@Override
	ParticipantModel assembleProduct() {
		return product;
	}

}
