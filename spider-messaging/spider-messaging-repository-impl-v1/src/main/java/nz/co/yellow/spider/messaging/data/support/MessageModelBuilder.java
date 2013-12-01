package nz.co.yellow.spider.messaging.data.support;

import java.util.Date;

import nz.co.yellow.spider.messaging.data.MessageModel;

public class MessageModelBuilder extends EntityBuilder<MessageModel> {

	@Override
	void initProduct() {
		this.product = new MessageModel();
	}

	public MessageModelBuilder create(String content, Date sentTime,
			String status) {
		this.product = MessageModel.getBuilder(content, sentTime, status)
				.build();
		return this;
	}

	@Override
	MessageModel assembleProduct() {
		return product;
	}

}
